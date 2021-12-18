package br.com.introcdc.textstream.components.loader;
/*
 * Written by IntroCDC, Bruno Coêlho at 24/11/2021 - 06:19
 */

import br.com.introcdc.textstream.account.Account;
import br.com.introcdc.textstream.components.database.DatabaseComponents;
import br.com.introcdc.textstream.components.screen.ScreenComponents;
import br.com.introcdc.textstream.screen.list.LoginScreen;

import java.util.Base64;
import java.util.Scanner;

public class LoaderComponents {

    /**
     * Search && Payment
     */
    public static long PAYMENT_TIME = 2_678_400_000L;
    public static int MAX_RESULTS = 10;

    /**
     * Delay Settings
     */
    public static int SPEED_DIVIDED = 2;
    public static int TYPE_SPEED = 150 / SPEED_DIVIDED;
    public static int TYPE_SPEED_RANDOM = 150 / SPEED_DIVIDED;
    public static int LOADING_DELAY = 500 / SPEED_DIVIDED;
    public static int BETWEEN_TEXTS = 1000 / SPEED_DIVIDED;
    public static int EXTRA_DELAY = 500 / SPEED_DIVIDED;

    /**
     * Account && Scanner
     */
    public static Account LOGGED_ACCOUNT = null;
    public static Scanner SCANNER;

    /**
     * Cipher Password
     */
    public static String password() {
        return new String(Base64.getDecoder().decode("MTAyOTM4NDc1NjQ3MzgyOQ=="));
    }

    /**
     * Starter Method
     */
    public static void start() {
        ScreenComponents.print("Carregando TextStream v1.0", true, true, true, true);
        ScreenComponents.print("");

        DatabaseComponents.loadAccounts();
        ScreenComponents.print("OK - Contas Carregadas");
        DatabaseComponents.loadTexts();
        ScreenComponents.print("OK - Livros Carregados");

        ScreenComponents.print("");
        ScreenComponents.print("Carregando login", true, true, true, true);
        ScreenComponents.delay(500);

        SCANNER = new Scanner(System.in);
        LoginScreen.SCREEN.draw();
    }

}
