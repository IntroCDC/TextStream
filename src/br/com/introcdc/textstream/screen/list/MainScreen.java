package br.com.introcdc.textstream.screen.list;
/*
 * Written by IntroCDC, Bruno Coêlho at 24/11/2021 - 02:06
 */

import br.com.introcdc.textstream.components.loader.LoaderComponents;
import br.com.introcdc.textstream.components.screen.ScreenComponents;
import br.com.introcdc.textstream.screen.TextScreen;
import br.com.introcdc.textstream.text.Text;

import java.util.ArrayList;
import java.util.List;

public class MainScreen extends TextScreen {

    public static MainScreen SCREEN = new MainScreen();

    @Override
    public void draw() {
        ScreenComponents.clearConsole();
        ScreenComponents.print("  _______        _    _____ _");
        ScreenComponents.print(" |__   __|      | |  / ____| |");
        ScreenComponents.print("    | | _____  _| |_| (___ | |_ _ __ ___  __ _ _ __ ___");
        ScreenComponents.print("    | |/ _ \\ \\/ / __|\\___ \\| __| '__/ _ \\/ _` | '_ ` _ \\");
        ScreenComponents.print("    | |  __/>  <| |_ ____) | |_| | |  __/ (_| | | | | | |");
        ScreenComponents.print("    |_|\\___/_/\\_\\\\__|_____/ \\__|_|  \\___|\\__,_|_| |_| |_|");

        ScreenComponents.print();
        ScreenComponents.print();

        ScreenComponents.print("    Para procurar livros, digite \"PROCURAR\"");
        ScreenComponents.print("    Para publicar um livro, digite \"PUBLICAR\"");
        ScreenComponents.print("    Para sair, digite \"SAIR\"");

        ScreenComponents.print();

        long views = 0;
        long posts = 0;
        long comments = 0;
        long likes = 0;
        long dislikes = 0;
        List<String> reached = new ArrayList<>();

        for (Text text : Text.getTextList()) {
            if (!text.getCreator().equals(LoaderComponents.LOGGED_ACCOUNT.getUsername())) {
                continue;
            }

            comments += text.getCommentList().size();
            views += text.getViews();
            posts++;

            for (String username : text.getSeem()) {
                if (reached.contains(username)) {
                    continue;
                }
                reached.add(username);
            }
        }

        ScreenComponents.print("    Olá, " + LoaderComponents.LOGGED_ACCOUNT.getName() + "!");
        ScreenComponents.print("    Você possui " + posts + " postagens com " + views + " visualizações e " + comments + " comentários!");
        ScreenComponents.print("    Nas suas postagens no total possui " + likes + " likes, " + dislikes + " dislikes e alcançou " + reached.size() + " pessoas!");

        ScreenComponents.print();
        ScreenComponents.print();

        for (; ; ) {
            ScreenComponents.print("> ", true, false, true, false);
            String command = input();

            if (command.equalsIgnoreCase("procurar")) {
                SearchScreen.SEARCH = null;
                SearchScreen.SCREEN.draw();
                break;
            } else if (command.equalsIgnoreCase("publicar")) {
                PublishScreen.SCREEN.draw();
                break;
            } else if (command.equalsIgnoreCase("sair")) {
                ScreenComponents.print("Deslogando de sua conta", true, true, true, true);
                LoginScreen.SCREEN.draw();
                break;
            }
        }
    }

}
