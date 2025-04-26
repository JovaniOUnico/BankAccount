package account;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import account.util.Colors;
import account.model.Account;

public class Execution {

	private static List<Account> AccountList = new ArrayList<Account>();
	private static Scanner read;

	public static void main(String[] args) {

		read = new Scanner(System.in);

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

		keyPress();
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

			titular = getValueString("Digite o nome do titular:");
			numero  = getValueInt("Digite o numero da conta:");
			agencia = getValueInt("Digite o numero da agencia:");
			tipo    = getValueInt("Digite o tipo da conta:\n[ 1 - corrente | 2 - poupança]");
			saldo   = getValueFloat("Digite o saldo atual");

    		Account acc = new Account(numero, agencia, tipo, titular);
    		acc.deposit(saldo);
    		
    		setAccount(acc);

    		System.out.println("Conta adicionada com sucesso");
    	}
	}

	public static void showAccountList() {
		NumberFormat nfMoeda = NumberFormat.getCurrencyInstance();

		System.out.println("++++++++++++++++++++");
		for (Account acc : AccountList) {
			System.out.println("Titular: " + acc.getTitular() + " | Saldo : " + nfMoeda.format(acc.getSaldo()));
		}
		System.out.println("++++++++++++++++++++");
	}

	public static void showExit() {
		about();
	}

	public static void setAccount(Account acc) {
		AccountList.add(acc);
	}
	
	public static void testAccounts() {

		setAccount(new Account(1234, 322, 1, "Lucy Ane"));
		AccountList.get(0).deposit(3333);
		showAccountList();

		setAccount(new Account(1237, 322, 1, "Julio Cesar"));
		AccountList.get(1).deposit(1000);
		showAccountList();

		setAccount(new Account(1238, 322, 1, "Mia Malkova"));
		AccountList.get(2).deposit(6969);
		showAccountList();
		AccountList.get(2).draw(500);
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
