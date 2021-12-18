package br.com.introcdc.textstream.screen.list;
/*
 * Written by IntroCDC, Bruno Coêlho at 24/11/2021 - 01:42
 */

import br.com.introcdc.textstream.account.Account;
import br.com.introcdc.textstream.components.login.LoginComponents;
import br.com.introcdc.textstream.components.screen.ScreenComponents;
import br.com.introcdc.textstream.screen.TextScreen;

public class RegisterScreen extends TextScreen {

    public static RegisterScreen SCREEN = new RegisterScreen();

    @Override
    public void draw() {
        ScreenComponents.clearConsole();
        ScreenComponents.print("  _____            _     _ ");
        ScreenComponents.print(" |  __ \\          (_)   | |");
        ScreenComponents.print(" | |__) |___  __ _ _ ___| |_ _ __ ___  ");
        ScreenComponents.print(" |  _  // _ \\/ _` | / __| __| '__/ _ \\ ");
        ScreenComponents.print(" | | \\ \\  __/ (_| | \\__ \\ |_| | | (_) |");
        ScreenComponents.print(" |_|  \\_\\___|\\__, |_|___/\\__|_|  \\___/ ");
        ScreenComponents.print("              __/ |                    ");
        ScreenComponents.print("             |___/                     ");

        ScreenComponents.print();
        ScreenComponents.print();

        ScreenComponents.print("    Preencha as seguintes informações para registrar-se...");

        ScreenComponents.print();
        ScreenComponents.print();

        boolean register = false;
        do {
            ScreenComponents.print("Usuário: ", true, false, true, false);
            String username = input().toLowerCase();
            if (Account.get(username) != null) {
                ScreenComponents.print("Usuário já registrado...", true, false, true, true);
                continue;
            }
            if (username.contains(" ")) {
                ScreenComponents.print("Usuário inválido, o usuário não pode conter espaço!", true, false, true, true);
                continue;
            }

            ScreenComponents.print("E-Mail: ", true, false, true, false);
            String email = input();
            if (Account.getByEmail(email) != null) {
                ScreenComponents.print("E-Mail já registrado...", true, false, true, true);
                continue;
            }

            ScreenComponents.print("Nome completo: ", true, false, true, false);
            String name = input();
            if (Account.getByName(name) != null) {
                ScreenComponents.print("Nome já registrado...", true, false, true, true);
                continue;
            }

            String finalPassword = null;
            do {
                ScreenComponents.print("Senha: ", true, false, true, false);
                String password = input();
                ScreenComponents.print("Senha (confirmar): ", true, false, true, false);
                if (!input().equals(password)) {
                    ScreenComponents.print("Senhas diferentes...", true, false, true, true);
                    continue;
                }
                finalPassword = LoginComponents.hashSha512(password);
            } while (finalPassword == null);
            Account account = new Account(username, finalPassword, name, email);
            account.save();
            account.loadInCache();
            register = true;
            ScreenComponents.print("Conta registrada!", true, false, true, true);
            ScreenComponents.print("Retornando a tela de login", true, true, true, true);
        } while (!register);

        LoginScreen.SCREEN.draw();
    }

}
