package br.com.introcdc.textstream.components.screen;
/*
 * Written by IntroCDC, Bruno Coêlho at 23/11/2021 - 23:06
 */

import br.com.introcdc.textstream.components.loader.LoaderComponents;
import br.com.introcdc.textstream.components.sound.SoundComponents;
import br.com.introcdc.textstream.sounds.SoundEnum;

import java.util.Random;

public interface ScreenComponents {

    Random RANDOM = new Random();

    static void clearConsole() {
        for (int i = 0; i < 100; i++) {
            ScreenComponents.print(" ");
        }
    }

    static void delay(int milliSeconds) {
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    static void print() {
        print(" ");
    }

    static void print(String message) {
        print(message, false, false, false, true);
    }

    /**
     * Normal print and animated with sound print
     */
    static void print(String message, boolean animation, boolean loading, boolean sound, boolean line) {
        if (!animation && !loading) {
            if (line) {
                System.out.println(message);
            } else {
                System.out.print(message);
            }
            return;
        }
        if (animation) {
            for (char c : message.toCharArray()) {
                ScreenComponents.delay(LoaderComponents.TYPE_SPEED + RANDOM.nextInt(LoaderComponents.TYPE_SPEED_RANDOM));
                System.out.print(c);
                if (sound) {
                    typeSound(c == ' ' ? 11 : RANDOM.nextInt(11));
                }
            }
        } else {
            System.out.print(message);
        }
        boolean justSpace = message.equalsIgnoreCase(" ");

        if (!loading) {
            if (!justSpace) {
                ScreenComponents.delay(LoaderComponents.LOADING_DELAY);
            }
            if (line) {
                System.out.println();
            }
            return;
        }
        for (int i = 1; i <= 5; i++) {
            ScreenComponents.delay(LoaderComponents.LOADING_DELAY);
            System.out.print(".");
            if (sound) {
                typeSound(1);
            }
            if (i == 5 && line) {
                ScreenComponents.delay(LoaderComponents.LOADING_DELAY);
                System.out.println();
            }
        }
    }

    static void typeSound(int id) {
        SoundComponents.playSound(SoundEnum.getSound(id).getFile());
    }

}
