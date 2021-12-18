package br.com.introcdc.textstream.screen.list;
/*
 * Written by IntroCDC, Bruno Coêlho at 24/11/2021 - 02:46
 */

import br.com.introcdc.textstream.components.loader.LoaderComponents;
import br.com.introcdc.textstream.components.login.LoginComponents;
import br.com.introcdc.textstream.components.screen.ScreenComponents;
import br.com.introcdc.textstream.screen.TextScreen;
import br.com.introcdc.textstream.text.Text;
import br.com.introcdc.textstream.text.comment.TextComment;

public class ReadScreen extends TextScreen {

    public static Text SELECTED;
    public static ReadScreen SCREEN = new ReadScreen();

    @Override
    public void draw() {
        ScreenComponents.clearConsole();
        ScreenComponents.print("  _      _");
        ScreenComponents.print(" | |    (_)");
        ScreenComponents.print(" | |     ___   ___ __ ___");
        ScreenComponents.print(" | |    | \\ \\ / / '__/ _ \\");
        ScreenComponents.print(" | |____| |\\ V /| | | (_) |");
        ScreenComponents.print(" |______|_| \\_/ |_|  \\___/");

        ScreenComponents.print();
        ScreenComponents.print();
        SELECTED.addView();
        SELECTED.addSeem(LoaderComponents.LOGGED_ACCOUNT.getUsername());
        ScreenComponents.print("    " + SELECTED.getTitle(), true, false, true, true);
        ScreenComponents.print("        Por " + SELECTED.getCreatorAccount().getName(),
                true, false, true, true);
        ScreenComponents.delay(LoaderComponents.BETWEEN_TEXTS);
        ScreenComponents.print(" ", true, false, true, true);
        ScreenComponents.print("        " + SELECTED.getViews() + " visualizações - " + SELECTED.getCommentList().size() + " comentários");
        ScreenComponents.print("        " + SELECTED.getLike().size() + " likes - " + SELECTED.getDislike().size() + " dislikes");
        ScreenComponents.delay(LoaderComponents.BETWEEN_TEXTS);


        ScreenComponents.print(" ", true, false, true, true);
        ScreenComponents.print(" ", true, false, true, true);

        for (String line : SELECTED.getText()) {
            ScreenComponents.print("> " + line, true, false, true, true);
        }

        ScreenComponents.print(" ", true, false, true, true);
        ScreenComponents.print(" ", true, false, true, true);

        ScreenComponents.print("        " + SELECTED.getCreatorAccount().getName() + ", " + SELECTED.getCreated(),
                true, false, true, true);
        ScreenComponents.delay(LoaderComponents.BETWEEN_TEXTS);

        ScreenComponents.print();
        ScreenComponents.print();

        for (; ; ) {
            ScreenComponents.print();
            ScreenComponents.print();

            ScreenComponents.print("    " + SELECTED.getViews() + " visualizações - " + SELECTED.getCommentList().size() + " comentários");
            ScreenComponents.print("    " + SELECTED.getLike().size() + " likes - " + SELECTED.getDislike().size() + " dislikes");
            if (SELECTED.getLike().contains(LoaderComponents.LOGGED_ACCOUNT.getUsername())) {
                ScreenComponents.print("    Este livro possui o seu like!");
            }
            if (SELECTED.getDislike().contains(LoaderComponents.LOGGED_ACCOUNT.getUsername())) {
                ScreenComponents.print("    Este livro possui o seu dislike!");
            }

            ScreenComponents.print();

            ScreenComponents.print("    Para ler os comentários, digite \"COMENTARIOS\"!");
            ScreenComponents.print("    Para comentar, digite \"COMENTAR\"!");
            ScreenComponents.print("    Para dar like ou dislike, digite \"LIKE\" ou \"DISLIKE\"!");
            ScreenComponents.print("    Para voltar, digite \"SAIR\"!");

            ScreenComponents.print();
            ScreenComponents.print();

            ScreenComponents.print("> ", true, false, true, false);
            String input = input();

            ScreenComponents.print();
            ScreenComponents.print();
            if (input.equalsIgnoreCase("comentarios")) {
                ScreenComponents.print(" ", true, false, true, true);
                ScreenComponents.print(" ", true, false, true, true);

                for (TextComment textComment : SELECTED.getCommentList()) {
                    ScreenComponents.print("    \"" + textComment.getComment() + "\"", true, false, true, true);
                    ScreenComponents.print("        ~ " + textComment.getCreatorAccount().getName() +
                                    (textComment.getCreator().equals(SELECTED.getCreator()) ? " (Autor do livro)" : "") + " - " + textComment.getWhen(),
                            true, false, true, true);
                    ScreenComponents.print(" ", true, false, true, true);
                    ScreenComponents.delay(LoaderComponents.EXTRA_DELAY);
                }
            } else if (input.equalsIgnoreCase("comentar")) {
                ScreenComponents.print("Digite seu comentário: ", true, false, true, false);
                String comment = input();
                if (comment.isEmpty()) {
                    ScreenComponents.print("Você não digitou nada", true, true, true, true);
                    continue;
                }

                TextComment textComment = new TextComment(LoaderComponents.LOGGED_ACCOUNT.getUsername(), LoginComponents.currentDate(), comment);
                SELECTED.addComment(textComment);
                ScreenComponents.print("Comentário adicionado!", true, false, true, true);
            } else if (input.equalsIgnoreCase("like")) {
                if (SELECTED.addLike(LoaderComponents.LOGGED_ACCOUNT.getUsername())) {
                    ScreenComponents.print("Você deixou o seu like neste livro!", true, false, true, true);
                } else {
                    ScreenComponents.print("Você tirou o seu like deste livro!", true, false, true, true);
                }
            } else if (input.equalsIgnoreCase("dislike")) {
                if (SELECTED.addDislike(LoaderComponents.LOGGED_ACCOUNT.getUsername())) {
                    ScreenComponents.print("Você deixou o seu dislike neste livro!", true, false, true, true);
                } else {
                    ScreenComponents.print("Você tirou o seu dislike deste livro!", true, false, true, true);
                }
            } else if (input.equalsIgnoreCase("sair")) {
                SearchScreen.SCREEN.draw();
                break;
            }
        }
    }

}
