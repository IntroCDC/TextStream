package br.com.introcdc.textstream.screen.list;
/*
 * Written by IntroCDC, Bruno Coêlho at 24/11/2021 - 04:45
 */

import br.com.introcdc.textstream.components.loader.LoaderComponents;
import br.com.introcdc.textstream.components.screen.ScreenComponents;
import br.com.introcdc.textstream.screen.TextScreen;

public class PaymentScreen extends TextScreen {

    public static PaymentScreen SCREEN = new PaymentScreen();

    @Override
    public void draw() {
        if (LoaderComponents.LOGGED_ACCOUNT.isEnabled()) {
            MainScreen.SCREEN.draw();
            return;
        }

        ScreenComponents.clearConsole();
        ScreenComponents.print("  _____                                       _");
        ScreenComponents.print(" |  __ \\                                     | |");
        ScreenComponents.print(" | |__) |_ _  __ _  __ _ _ __ ___   ___ _ __ | |_ ___");
        ScreenComponents.print(" |  ___/ _` |/ _` |/ _` | '_ ` _ \\ / _ \\ '_ \\| __/ _ \\");
        ScreenComponents.print(" | |  | (_| | (_| | (_| | | | | | |  __/ | | | || (_) |");
        ScreenComponents.print(" |_|   \\__,_|\\__, |\\__,_|_| |_| |_|\\___|_| |_|\\__\\___/");
        ScreenComponents.print("              __/ |");
        ScreenComponents.print("             |___/");

        ScreenComponents.print();
        ScreenComponents.print();

        ScreenComponents.print("    Você não possui o plano mensal do programa!");
        ScreenComponents.print("    Adquira agora por apenas R$ 15.00 por mês!");

        ScreenComponents.print();

        ScreenComponents.print("    Aceitamos: Cartão de Crédito(CC) ou Débito(CD), cartão pré-pago(CPP), Boleto, TED ou PIX!");
        ScreenComponents.print("    Para selecionar cartão, digite a sigla logo em seguida do tipo do cartão...");

        ScreenComponents.print();
        ScreenComponents.print();

        ScreenComponents.delay(LoaderComponents.BETWEEN_TEXTS);
        ScreenComponents.print("Digite a forma de pagamento: ", true, false, true, false);

        int delay = LoaderComponents.EXTRA_DELAY;
        String payment = input().toLowerCase();
        ScreenComponents.print(" ", true, false, true, true);

        boolean accepted;
        switch (payment) {
            case "cc", "cd", "cpp" -> {
                ScreenComponents.print("Número do cartão: ", true, false, true, false);
                ScreenComponents.delay(delay);
                ScreenComponents.print(randomNumber(4) + " " + randomNumber(4) + " " + randomNumber(4) + " " + randomNumber(4),
                        true, false, true, true);
                ScreenComponents.print(" ", true, false, true, true);
                ScreenComponents.print("Validade: ", true, false, true, false);
                ScreenComponents.delay(delay);
                ScreenComponents.print((ScreenComponents.RANDOM.nextInt(12) + 1) + "/" + (2025 + ScreenComponents.RANDOM.nextInt(75)),
                        true, false, true, true);
                ScreenComponents.print(" ", true, false, true, true);
                ScreenComponents.print("Digito de serungança: ", true, false, true, false);
                ScreenComponents.delay(delay);
                ScreenComponents.print(randomNumber(3), true, false, true, true);
                ScreenComponents.print(" ", true, false, true, true);
                ScreenComponents.print(" ", true, false, true, true);
                ScreenComponents.print(" ", true, false, true, true);
                ScreenComponents.print("    Entrando em contato com o banco...", true, false, true, true);
                ScreenComponents.print("    Aprovando compra...", true, false, true, true);
                ScreenComponents.print(" ", true, false, true, true);
                ScreenComponents.print(" ", true, false, true, true);
                ScreenComponents.print(" ", true, false, true, true);
                if (ScreenComponents.RANDOM.nextBoolean()) {
                    ScreenComponents.print("Compra não aprovada", true, true, true, true);
                    accepted = false;
                } else {
                    ScreenComponents.print("Compra aprovada!", true, false, true, true);
                    accepted = true;
                }
            }
            case "boleto" -> {
                ScreenComponents.print("Gerando boleto...", true, false, true, true);
                ScreenComponents.print("Código do boleto: " + ScreenComponents.RANDOM.nextInt(1000000),
                        true, false, true, true);
                ScreenComponents.print(" ", true, false, true, true);
                ScreenComponents.print(" ", true, false, true, true);
                ScreenComponents.print(" ", true, false, true, true);
                ScreenComponents.print("    ~ Você vai no centro para pagar o boleto ~",
                        true, false, true, true);
                ScreenComponents.print(" ", true, false, true, true);
                ScreenComponents.print(" ", true, false, true, true);
                ScreenComponents.print(" ", true, false, true, true);
                if (ScreenComponents.RANDOM.nextBoolean()) {
                    ScreenComponents.print("    ~ Você não tinha dinheiro suficiente para pagar",
                            true, true, true, true);
                    accepted = false;
                } else if (ScreenComponents.RANDOM.nextBoolean()) {
                    ScreenComponents.print("    ~ Você esqueceu o código do boleto",
                            true, true, true, true);
                    accepted = false;
                } else if (ScreenComponents.RANDOM.nextBoolean()) {
                    ScreenComponents.print("    ~ Você foi assaltado e não tem mais dinheiro para pagar o boleto",
                            true, true, true, true);
                    accepted = false;
                } else {
                    ScreenComponents.print("    ~ Você pagou o boleto e voltou pra casa ~",
                            true, false, true, true);

                    ScreenComponents.print(" ", true, false, true, true);
                    ScreenComponents.print(" ", true, false, true, true);
                    ScreenComponents.print(" ", true, false, true, true);

                    ScreenComponents.print("Compra aprovada!", true, false, true, true);
                    accepted = true;
                }
            }
            case "ted" -> {
                ScreenComponents.print("Compra não aprovada", true, true, true, true);
                ScreenComponents.print(" ", true, false, true, true);
                ScreenComponents.print(" ", true, false, true, true);
                ScreenComponents.print(" ", true, false, true, true);
                ScreenComponents.print("Quem é que usa transferência hoje tendo pix?", true, false, true, true);
                accepted = false;
            }
            case "pix" -> {
                ScreenComponents.print("    ~ Você pega seu celular e acessa o aplicativo do banco ~",
                        true, false, true, true);
                ScreenComponents.print(" ", true, false, true, true);
                ScreenComponents.print(" ", true, false, true, true);
                ScreenComponents.print(" ", true, false, true, true);
                ScreenComponents.print("    ~ Faz a transferência para a chave pix +55 85 98125-4679 ~ (to aceitando transferência, boa noite)",
                        true, false, true, true);
                ScreenComponents.clearConsole();
                ScreenComponents.print(" ", true, false, true, true);
                ScreenComponents.print(" ", true, false, true, true);
                ScreenComponents.print(" ", true, false, true, true);
                if (ScreenComponents.RANDOM.nextBoolean()) {
                    ScreenComponents.print("Compra não aprovada", true, true, true, true);
                    accepted = false;
                } else {
                    ScreenComponents.print("Compra aprovada!", true, false, true, true);
                    accepted = true;
                }
            }
            default -> {
                draw();
                return;
            }
        }

        if (accepted) {
            ScreenComponents.delay(delay * 5);
            LoaderComponents.LOGGED_ACCOUNT.setExpires(System.currentTimeMillis() + LoaderComponents.PAYMENT_TIME);
            MainScreen.SCREEN.draw();
        } else {
            ScreenComponents.delay(delay * 3);
            draw();
        }

    }

    public static String randomNumber(int size) {
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < size; i++) {
            number.append(ScreenComponents.RANDOM.nextInt(10));
        }
        return number.toString();
    }

}
