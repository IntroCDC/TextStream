package br.com.introcdc.textstream.screen.list;
/*
 * Written by IntroCDC, Bruno Coêlho at 23/11/2021 - 23:06
 */

import br.com.introcdc.textstream.account.Account;
import br.com.introcdc.textstream.components.loader.LoaderComponents;
import br.com.introcdc.textstream.components.screen.ScreenComponents;
import br.com.introcdc.textstream.screen.TextScreen;

public class LoginScreen extends TextScreen {

    public static LoginScreen SCREEN = new LoginScreen();

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

        ScreenComponents.print("    Serviço de stream de livros, publique e leia livros de diversos usuários");

        ScreenComponents.print();
        ScreenComponents.print();

        do {
            ScreenComponents.delay(LoaderComponents.BETWEEN_TEXTS);

            ScreenComponents.print("    Para logar-se, digite \"LOGIN\"!");
            ScreenComponents.print("    Para registrar-se, digite \"REGISTRAR\"!");
            ScreenComponents.print("    Para sair, digite \"SAIR\"!");
            ScreenComponents.print();

            ScreenComponents.print("> ", true, false, true, false);
            String command = input();
            if (command.equalsIgnoreCase("registrar")) {
                RegisterScreen.SCREEN.draw();
                return;
            }
            if (command.equalsIgnoreCase("sair")) {
                ScreenComponents.print("Adeus", true, true, true, true);
                System.exit(0);
                break;
            }
            if (!command.equalsIgnoreCase("login")) {
                continue;
            }

            ScreenComponents.print("Digite seu usuário: ", true, false, true, false);
            String username = input();
            Account account = Account.get(username);
            if (account == null) {
                ScreenComponents.print("Usuário não encontrado...", true, false, true, true);
                continue;
            }
            ScreenComponents.print("Olá " + username + ", digite sua senha: ", true, false, true, false);
            String password = input();
            if (!account.auth(password)) {
                ScreenComponents.print("Senha incorreta...", true, false, true, true);
                continue;
            }
            ScreenComponents.print("Seja bem-vindo de volta, " + account.getName() + "!", true, false, true, true);
            ScreenComponents.print("Indo para a tela principal", true, true, true, true);
            ScreenComponents.delay(LoaderComponents.EXTRA_DELAY);

            LoaderComponents.LOGGED_ACCOUNT = account;
        } while (LoaderComponents.LOGGED_ACCOUNT == null);

        PaymentScreen.SCREEN.draw();
    }

}
