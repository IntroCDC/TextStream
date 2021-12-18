package br.com.introcdc.textstream.screen.list;
/*
 * Written by IntroCDC, Bruno Coêlho at 24/11/2021 - 03:10
 */

import br.com.introcdc.textstream.components.loader.LoaderComponents;
import br.com.introcdc.textstream.components.login.LoginComponents;
import br.com.introcdc.textstream.components.screen.ScreenComponents;
import br.com.introcdc.textstream.screen.TextScreen;
import br.com.introcdc.textstream.text.Text;

import java.util.ArrayList;
import java.util.List;

public class PublishScreen extends TextScreen {

    public static PublishScreen SCREEN = new PublishScreen();

    @Override
    public void draw() {
        ScreenComponents.clearConsole();
        ScreenComponents.print("  _____       _     _ _");
        ScreenComponents.print(" |  __ \\     | |   | (_)");
        ScreenComponents.print(" | |__) |   _| |__ | |_  ___ __ _ _ __");
        ScreenComponents.print(" |  ___/ | | | '_ \\| | |/ __/ _` | '__|");
        ScreenComponents.print(" | |   | |_| | |_) | | | (_| (_| | |");
        ScreenComponents.print(" |_|    \\__,_|_.__/|_|_|\\___\\__,_|_|");

        ScreenComponents.print();
        ScreenComponents.print();

        ScreenComponents.print("    Página de publicação de livros");
        ScreenComponents.print("    Quando você começar a digitar o livro,");
        ScreenComponents.print("    Para parar e publicar, digite \".PUBLICAR\"");
        ScreenComponents.print("    Para parar e deletar, digite \".DELETAR\"");
        ScreenComponents.print("    LEMBRANDO: Pule as linhas manualmente...");
        ScreenComponents.print("    Para apagar e redigitar a linha anterior, digite \".VOLTAR\"");


        ScreenComponents.print();
        ScreenComponents.print();

        ScreenComponents.print("Título do livro: ", true, false, true, false);
        String title = input();
        ScreenComponents.print("Gênero do livro: ", true, false, true, false);
        String type = input();

        ScreenComponents.print();
        ScreenComponents.print();
        List<String> lines = new ArrayList<>();

        for (; ; ) {
            ScreenComponents.print("> ", false, false, true, false);
            String line = input();
            if (line.equalsIgnoreCase(".voltar")) {
                if (lines.isEmpty()) {
                    ScreenComponents.print("Não possui nenhuma linha antes dessa!", true, false, true, true);
                    continue;
                }
                lines.remove(lines.size() - 1);
                ScreenComponents.print("Linha anterior deletada!", true, false, true, true);
                continue;
            }
            if (line.equalsIgnoreCase(".publicar")) {
                int id = 0;
                while (Text.byId(id) != null) {
                    id++;
                }
                List<String> seem = new ArrayList<>();
                seem.add(LoaderComponents.LOGGED_ACCOUNT.getUsername());
                Text text = new Text(id, title, LoaderComponents.LOGGED_ACCOUNT.getUsername(), type,
                        LoginComponents.currentDate(), 0, lines, new ArrayList<>(), new ArrayList<>(), seem, new ArrayList<>());
                text.save();
                ScreenComponents.print("Parabéns!", true, false, true, true);
                ScreenComponents.print("Seu livro foi publicado com sucesso!", true, false, true, true);
                ScreenComponents.print("Voltando para o menu principal", true, true, true, true);
                MainScreen.SCREEN.draw();
                break;
            }
            if (line.equalsIgnoreCase(".deletar")) {
                ScreenComponents.print();
                ScreenComponents.print();
                ScreenComponents.print("Publicação deletada, voltando para o menu principal", true, true, true, true);
                MainScreen.SCREEN.draw();
                break;
            }
            lines.add(line);
        }

    }

}
