package br.com.introcdc.textstream.components.text;
/*
 * Written by IntroCDC, Bruno Co�lho at 24/11/2021 - 02:02
 */

import br.com.introcdc.textstream.components.screen.ScreenComponents;
import br.com.introcdc.textstream.text.Text;

public interface TextComponents {

    /**
     * Show info in Search Screen
     */
    static void printInfo(Text text) {
        ScreenComponents.print(" > ID: " + text.getId() + " | " + text.getTitle() + " | Por " + text.getCreatorAccount().getName());
        ScreenComponents.print("    G�nero: " + text.getType() + " | Publicado em: " + text.getCreated() + " | " + text.getText().size() + " linhas");
        ScreenComponents.print("    " + text.getViews() + " visualiza��es | " + text.getCommentList().size() + " coment�rios");
        ScreenComponents.print("    " + text.getLike().size() + " likes | " + text.getDislike().size() + " dislikes");
        ScreenComponents.print();
    }

}
