package br.com.introcdc.textstream.screen;
/*
 * Written by IntroCDC, Bruno Co�lho at 23/11/2021 - 22:41
 */

import br.com.introcdc.textstream.components.loader.LoaderComponents;

public class TextScreen {

    public void draw() {
    }

    public String input() {
        return LoaderComponents.SCANNER.nextLine();
    }

}
