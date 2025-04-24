package account;

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
                int espacosOpcao = LARG - opcao.length() - 3; // -2 para as bordas
                System.out.println(Aux + "| " + opcao + " ".repeat(Math.max(0, espacosOpcao)) + "|");
            }
        } else {
            String mensagem = "Nenhuma opção de menu fornecida.";
            int espacosMensagem = (LARG - mensagem.length()) / 2;
            System.out.println(Aux + "|" + " ".repeat(espacosMensagem) + mensagem + " ".repeat(espacosMensagem + (mensagem.length() % 2)) + "|");
        }

        System.out.println(Aux + "*".repeat(LARG));
        System.out.print(Aux + "Entre com a opção desejada: ");
    }
}
