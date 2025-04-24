package account;

import java.util.List;

import account.util.Colors;

public class Execution {

	public static void main(String[] args) {
		Menu Mn = new Menu();
		
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


        Mn.showMenu(menuPrincipal, Colors.BLACK_BACKGROUND, Colors.BLUE_BRIGHT);

	}

}
