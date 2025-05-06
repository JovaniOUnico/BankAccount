package account;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import account.util.Colors;
import account.controller.AccountController;
import account.model.Account;
import account.model.CheckingAccount;
import account.model.SavingsAccount;

public class Execution {

	private static Scanner read;
	private static AccountController accControl;

	public static void main(String[] args) {

		read = new Scanner(System.in);
		
		accControl = new AccountController();

		Menu Mn = new Menu();

		int opt = 0;

        List<String> menuPrincipal = List.of(
                "Criar Conta",
                "Listar todas as Contas",
                "Buscar Conta por Número",
                "Atualizar Dados da Conta",
                "Apagar Conta",
                "Sacar",
                "Depositar",
                "Transferir valores entre Contas",
                "Sair"
        );

        testAccounts();

        do {        	
        	Mn.showMenu(menuPrincipal, Colors.BLACK_BACKGROUND, Colors.BLUE_BRIGHT);

        	opt = read.nextInt();

        	control(opt);

        } while (opt != 9);
        
        read.close();

	}
	
	public static void control(int opt) {

		switch (opt) {
			case 1 -> showCreateAccount();
			case 2 -> showAccountList();
			case 3 -> searchAccount();
			case 4 -> updateAccount();
			case 5 -> deleteAccount();
			case 6 -> drawFromAccount();
			case 7 -> depositFromAccount();
			case 8 -> System.out.println("Transferir valores entre Contas");
			case 9 -> showExit();
			default -> System.out.println("Opção inválida tente novamente");
		}

		if (opt != 9) {			
			keyPress();
		}
	}

	public static void showCreateAccount() {

		Menu Mn = new Menu();

		int opt = 0;

        List<String> menuSecundario = List.of(
            "Confirmar criação de Conta",
            "Voltar"
        );

        Mn.showMenu(menuSecundario, Colors.BLACK_BACKGROUND, Colors.BLUE_BRIGHT);
		opt = read.nextInt();

    	if (opt != 2) {
    		int numero, agencia, tipo;
        	String titular;
        	float saldo;

        	read.nextLine();
			titular = getValueString("Digite o nome do titular:");
			numero  = accControl.getNumId();
			agencia = getValueInt("Digite o numero da agencia:");
			tipo    = getValueInt("Digite o tipo da conta:\n[ 1 - corrente | 2 - poupança]", 1, 2);

			Account acc = null;
			if (tipo == 1) {
				float limit = getValueInt("Digite o Limite da conta");

				acc = new CheckingAccount(numero, agencia, titular, limit);
			} else if (tipo == 2) {
				read.nextLine();
				String aniversary = getValueString("Digite a data de aniversario");

				acc = new SavingsAccount(numero, agencia, titular, aniversary);
			}

			saldo = getValueFloat("Digite o saldo atual");

			try {
	    		acc.deposit(saldo);
	    		accControl.register(acc);
	    		
	    		System.out.println("Conta adicionada com sucesso");
			} catch (Exception ex) {
			    System.err.println("Erro ao criar a conta");

			    read.nextLine();
			}

    	}
	}

	public static void showAccountList() {
		NumberFormat nfMoeda = NumberFormat.getCurrencyInstance();

		System.out.println("++++++++++++++++++++");
		for (Account acc : accControl.getList()) {
			String type = acc.getClass().getName();

			System.out.print("Titular: " + acc.getTitular() + " ");
			System.out.print("| Tipo : " + ((type.equalsIgnoreCase("account.model.CheckingAccount") == true) ? "Conta Corrente" : "Conta Poupança") + " ");

			try {
				if (type.equalsIgnoreCase("account.model.CheckingAccount") == true) {	
					System.out.print("| Limite: " + nfMoeda.format(((CheckingAccount) acc).getLimit()) + " ");
				} else {
					System.out.print("| Aniversário: " + ((SavingsAccount) acc).getAniversary() + " ");
				}
			} catch (Exception ex) {
			    System.err.println("Erro ao acessar dados");

			    read.nextLine();
			}
	
			System.out.println("| Saldo : " + nfMoeda.format(acc.getSaldo()));
		}
		System.out.println("++++++++++++++++++++");
	}

	public static void searchAccount() {
		NumberFormat nfMoeda = NumberFormat.getCurrencyInstance();
		
		Menu Mn = new Menu();

		int opt = 0;

        List<String> menuSecundario = List.of(
            "Pesquisar Por Número",
            "Pesquisar Por Nome",
            "Voltar"
        );

        Mn.showMenu(menuSecundario, Colors.BLACK_BACKGROUND, Colors.BLUE_BRIGHT);
		opt = read.nextInt();

		Optional<Account> accRes = selectAccount(opt);

		if (opt == 1 || opt == 2) {
			if (accRes.isPresent()) {
				Account acc = accRes.get();

				System.out.println("++++++++++++++++++++");

				String type = acc.getClass().getName();

				System.out.print("Titular: " + acc.getTitular() + " ");
				System.out.print("| Tipo : " + ((type.equalsIgnoreCase("account.model.CheckingAccount") == true) ? "Conta Corrente" : "Conta Poupança") + " ");

				try {
					if (type.equalsIgnoreCase("account.model.CheckingAccount") == true) {	
						System.out.print("| Limite: " + nfMoeda.format(((CheckingAccount) acc).getLimit()) + " ");
					} else {
						System.out.print("| Aniversário: " + ((SavingsAccount) acc).getAniversary() + " ");
					}
				} catch (Exception ex) {
				    System.err.println("Erro ao acessar dados");

				    read.nextLine();
				}
		
				System.out.println("| Saldo : " + nfMoeda.format(acc.getSaldo()));

				System.out.println("++++++++++++++++++++");
			} else {
				System.out.println("++++++++++++++++++++");
				System.out.println("|Conta não encontrada|");
				System.out.println("++++++++++++++++++++");
			}
		}
	}
	
	public static void updateAccount() {
		
		Menu Mn = new Menu();

		int opt = 0;

        List<String> menuSecundario = List.of(
            "Pesquisar Por Número",
            "Pesquisar Por Nome",
            "Voltar"
        );

        Mn.showMenu(menuSecundario, Colors.BLACK_BACKGROUND, Colors.BLUE_BRIGHT);
		opt = getValueInt("", 1, 3);

		Optional<Account> accRes = selectAccount(opt);

		if (opt == 1 || opt == 2) {
			if (accRes.isPresent()) {
				Account acc = accRes.get();

	    		int numero, agencia, tipo;
	        	String titular;

	        	read.nextLine();
	        	numero  = acc.getNumero();
				titular = getValueString("Digite o nome do titular:");
				agencia = getValueInt("Digite o numero da agencia:");
				tipo    = getValueInt("Digite o tipo da conta:\n[ 1 - corrente | 2 - poupança]", 1, 2);

				if (tipo == 1) {
					float limit = getValueInt("Digite o Limite da conta");

					acc = new CheckingAccount(numero, agencia, titular, limit);
				} else if (tipo == 2) {
					read.nextLine();
					String aniversary = getValueString("Digite a data de aniversario");

					acc = new SavingsAccount(numero, agencia, titular, aniversary);
				}

				try {
		    		accControl.update(acc);
		    		
		    		System.out.println("Conta editada com sucesso");
				} catch (Exception ex) {
				    System.err.println("Erro ao editar a conta");

				    read.nextLine();
				}

			} else {
				System.out.println("++++++++++++++++++++");
				System.out.println("|Conta não encontrada|");
				System.out.println("++++++++++++++++++++");
			}
		}

	}
	
	public static void deleteAccount() {
		
		Menu Mn = new Menu();

		int opt = 0;

        List<String> menuSecundario = List.of(
            "Pesquisar Por Número",
            "Pesquisar Por Nome",
            "Voltar"
        );

        Mn.showMenu(menuSecundario, Colors.BLACK_BACKGROUND, Colors.BLUE_BRIGHT);
		opt = read.nextInt();

		Optional<Account> accRes = selectAccount(opt);

		if (opt == 1 || opt == 2) {
			if (accRes.isPresent()) {
				Account acc = accRes.get();

				accControl.remove(acc);
				
				System.out.println("++++++++++++++++++++");
				System.out.println("|Conta removida com sucesso|");
				System.out.println("++++++++++++++++++++");
			} else {
				System.out.println("++++++++++++++++++++");
				System.out.println("|Conta não encontrada|");
				System.out.println("++++++++++++++++++++");
			}
		}

	}
	
	public static void drawFromAccount() {
		
		Menu Mn = new Menu();

		int opt = 0;

        List<String> menuSecundario = List.of(
            "Pesquisar Por Número",
            "Pesquisar Por Nome",
            "Voltar"
        );

        Mn.showMenu(menuSecundario, Colors.BLACK_BACKGROUND, Colors.BLUE_BRIGHT);
		opt = read.nextInt();

		Optional<Account> accRes = selectAccount(opt);

		if (opt == 1 || opt == 2) {
			if (accRes.isPresent()) {
				Account acc = accRes.get();

				float value;
				
				value = getValueFloat("Digite o valor para sacar");
				
				if (accControl.draw(acc.getNumero(), value)) {
					System.out.println("++++++++++++++++++++");
					System.out.println("|Valor sacado com sucesso|");
					System.out.println("++++++++++++++++++++");
				} else {
					System.out.println("++++++++++++++++++++");
					System.out.println("|Não foi possível sacar o valor|");
					System.out.println("++++++++++++++++++++");
				}

			} else {
				System.out.println("++++++++++++++++++++");
				System.out.println("|Conta não encontrada|");
				System.out.println("++++++++++++++++++++");
			}
		}

	}

	public static void depositFromAccount() {
		
		Menu Mn = new Menu();

		int opt = 0;

        List<String> menuSecundario = List.of(
            "Pesquisar Por Número",
            "Pesquisar Por Nome",
            "Voltar"
        );

        Mn.showMenu(menuSecundario, Colors.BLACK_BACKGROUND, Colors.BLUE_BRIGHT);
		opt = read.nextInt();

		Optional<Account> accRes = selectAccount(opt);

		if (opt == 1 || opt == 2) {
			if (accRes.isPresent()) {
				Account acc = accRes.get();

				float value;
				
				value = getValueFloat("Digite o valor para depositar");
				
				if (accControl.deposit(acc.getNumero(), value)) {
					System.out.println("++++++++++++++++++++");
					System.out.println("|Valor depositado com sucesso|");
					System.out.println("++++++++++++++++++++");
				} else {
					System.out.println("++++++++++++++++++++");
					System.out.println("|Não foi possível depositar o valor|");
					System.out.println("++++++++++++++++++++");
				}
			} else {
				System.out.println("++++++++++++++++++++");
				System.out.println("|Conta não encontrada|");
				System.out.println("++++++++++++++++++++");
			}
		}

	}

	public static void showExit() {
		about();
	}

	public static void testAccounts() {

		accControl.register(new CheckingAccount(accControl.getNumId(), 322, "Lucy Ane", 2000));
		accControl.deposit(1, 3333);
		showAccountList();

		accControl.register(new CheckingAccount(accControl.getNumId(), 322, "Julio Cesar", 3000));
		accControl.deposit(2, 1000);
		showAccountList();

		accControl.register(new SavingsAccount(accControl.getNumId(), 322, "Mia Malkova", "27-04-2025"));
		accControl.deposit(3, 6969);
		showAccountList();
		accControl.draw(3, 500);
		showAccountList();

	}
	
	public static int getValueInt(String msg) {
    	boolean validator = true;
    	int value = 0;

    	while (validator) {
        	System.out.println(msg);

			try {
				value = read.nextInt();

				validator = false;
			} catch (Exception e) {
			    System.err.println("Erro ao digitar o valor tente novamente!");

			    read.nextLine();
				continue;
			}
		}
    	
    	return value;
	}
	
	public static int getValueInt(String msg, int min, int max) {
    	boolean validator = true;
    	int value = 0;

    	while (validator) {
        	System.out.println(msg);

			try {
				value = read.nextInt();

				if (value >= min && value <= max) {					
					validator = false;
				} else {
					throw new Exception("Value is invalid");
				}

			} catch (Exception e) {
			    System.err.println("Erro ao digitar o valor tente novamente!");

			    read.nextLine();
				continue;
			}
		}
    	
    	return value;
	}

	public static float getValueFloat(String msg) {
    	boolean validator = true;
    	float value = 0;

    	while (validator) {
        	System.out.println(msg);

			try {
				value = read.nextFloat();

				validator = false;
			} catch (Exception e) {
			    System.err.println("Erro ao digitar o valor tente novamente!");

			    read.nextLine();
				continue;
			}
		}
    	
    	return value;
	}
	
	public static String getValueString(String msg) {
    	boolean validator = true;
    	String value = "";

    	while (validator) {
        	System.out.println(msg);

			try {
				value = read.nextLine();
				//read.nextLine();

				validator = false;
			} catch (Exception e) {
			    System.err.println("Erro ao digitar o valor tente novamente!");

			    read.nextLine();
				continue;
			}
		}
    	
    	return value;
	}
	
	public static Optional<Account> selectAccount(int opt) {

		switch (opt) {
			case 1:
	    		int numero;
	
	        	read.nextLine();
				numero = getValueInt("Digite o numero da conta:");
	
				return accControl.searchByNumber(numero);
			case 2:
				String name;
	        	read.nextLine();
				name = getValueString("Digite o nome do Titular:");

				return accControl.searchByName(name);
			case 3:
				System.out.println("Voltando ao Menu");
				break;
		}
		
		return null;
	}

	public static void about() {
		System.out.println("\n*********************************************************");
		System.out.println("Projeto Desenvolvido por: ");
		System.out.println("Jovani Almeida de Souza");
		System.out.println("https://github.com/JovaniOUnico/BankAccount");
		System.out.println("*********************************************************");
	}

	public static void keyPress() {

		try {

			System.out.println("\n\nPressione Enter para Continuar...");
			System.in.read();

		} catch (IOException e) {

			System.out.println("Você pressionou uma tecla diferente de enter!");

		}
	}

}
