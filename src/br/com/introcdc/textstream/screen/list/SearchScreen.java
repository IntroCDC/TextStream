package br.com.introcdc.textstream.screen.list;
/*
 * Written by IntroCDC, Bruno Coêlho at 24/11/2021 - 02:30
 */

import br.com.introcdc.textstream.components.loader.LoaderComponents;
import br.com.introcdc.textstream.components.login.LoginComponents;
import br.com.introcdc.textstream.components.screen.ScreenComponents;
import br.com.introcdc.textstream.components.text.TextComponents;
import br.com.introcdc.textstream.screen.TextScreen;
import br.com.introcdc.textstream.text.Text;

import java.util.List;

public class SearchScreen extends TextScreen {

    public static String SEARCH = null;
    public static SearchScreen SCREEN = new SearchScreen();

    @Override
    public void draw() {
        ScreenComponents.clearConsole();
        ScreenComponents.print("  _____                                          _");
        ScreenComponents.print(" |  __ \\                                        | |");
        ScreenComponents.print(" | |__) | __ ___   ___ _   _ _ __ __ _ _ __   __| | ___");
        ScreenComponents.print(" |  ___/ '__/ _ \\ / __| | | | '__/ _` | '_ \\ / _` |/ _ \\");
        ScreenComponents.print(" | |   | | | (_) | (__| |_| | | | (_| | | | | (_| | (_) |  _ _ _");
        ScreenComponents.print(" |_|   |_|  \\___/ \\___|\\__,_|_|  \\__,_|_| |_|\\__,_|\\___/  (_|_|_)");

        ScreenComponents.print();
        ScreenComponents.print("    Para ler um livro, digite o ID do livro!");
        ScreenComponents.print("    Para voltar pro menu principal, digite \"SAIR\"!");
        ScreenComponents.print();
        ScreenComponents.print();

        boolean search = SEARCH != null && !SEARCH.isEmpty();
        if (Text.getTextList().isEmpty()) {
            ScreenComponents.print("Não possui nenhum livro publicado!");
        } else {
            List<Text> result;
            if (!search) {
                result = Text.getTextList();
            } else {
                result = Text.search(SEARCH);
            }

            if (search) {
                ScreenComponents.print("    Procurando por: \"" + SEARCH + "\"...");
                ScreenComponents.print();
                ScreenComponents.print();
            }

            if (result.isEmpty()) {
                ScreenComponents.print("Nenhum resultado...");
            } else {
                int size = result.size();
                for (int i = 0; i < Math.min(size, LoaderComponents.MAX_RESULTS); i++) {
                    TextComponents.printInfo(result.get(i));
                }
            }
        }

        ScreenComponents.print();
        ScreenComponents.print();
        ScreenComponents.print("> ", true, false, true, false);
        String searchInput = input();
        if (searchInput.equalsIgnoreCase("sair")) {
            MainScreen.SCREEN.draw();
        } else {
            Text text;
            if (LoginComponents.isInt(searchInput) && (text = Text.byId(Integer.parseInt(searchInput))) != null) {
                ReadScreen.SELECTED = text;
                ReadScreen.SCREEN.draw();
            } else {
                SEARCH = searchInput;
                draw();
            }
        }

    }

}
