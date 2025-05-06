package account;

import java.io.IOException;
import java.util.List;

public class Menu {

	private static final int LARG = 60;
    public void showMenu(List<String> options, String bgColor, String Color) {
        String Aux = bgColor + Color;
        
        System.out.println(Aux + "*".repeat(LARG));

        String titulo = "BANCO DO BRASIL COM Z";
        int espacosTitulo = (LARG - titulo.length()) / 2;
        System.out.println(Aux + " ".repeat(espacosTitulo) + titulo + " ".repeat(espacosTitulo + (titulo.length() % 2)));

        System.out.println(Aux + "*".repeat(LARG));

        if (options != null && !options.isEmpty()) {
            for (int i = 0; i < options.size(); i++) {
                String opcao = (i + 1) + " - " + options.get(i);
                int espacosOpcao = LARG - opcao.length() - 3;// - 3 para as bordas
                System.out.println(Aux + "| " + opcao + " ".repeat(Math.max(0, espacosOpcao)) + "|");
            }
        } else {
            String mensagem = "Nenhuma opção de menu fornecida.";
            int espacosMensagem = (LARG - mensagem.length()) / 2;
            System.out.println(Aux + "|" + " ".repeat(espacosMensagem) + mensagem + " ".repeat(espacosMensagem + (mensagem.length() % 2)) + "|");
        }

        System.out.println(Aux + "*".repeat(LARG));
        System.out.println(Aux + "Entre com a opção desejada: ");
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
