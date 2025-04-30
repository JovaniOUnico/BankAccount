package account;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Scanner;

import account.util.Colors;
import account.comtroller.AccountController;
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
			case 3 -> System.out.println("Buscar Conta por Número");
			case 4 -> System.out.println("Atualizar Dados da Conta");
			case 5 -> System.out.println("Apagar Conta");
			case 6 -> System.out.println("Sacar");
			case 7 -> System.out.println("Depositar");
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

		accControl.register(new SavingsAccount(accControl.getNumId(), 322, "Mia Malkova", "27/04/2025"));
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
				read.nextLine();

				validator = false;
			} catch (Exception e) {
			    System.err.println("Erro ao digitar o valor tente novamente!");

			    read.nextLine();
				continue;
			}
		}
    	
    	return value;
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

			System.out.println(Colors.RESET + "\n\nPressione Enter para Continuar...");
			System.in.read();

		} catch (IOException e) {

			System.out.println("Você pressionou uma tecla diferente de enter!");

		}
	}

}
