package br.com.introcdc.textstream.components.sound;
/*
 * Written by IntroCDC, Bruno Coêlho at 24/11/2021 - 00:27
 */

import br.com.introcdc.textstream.TextMain;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public interface SoundComponents {

    /**
     * Play wav audio
     */
    static void playSound(final String path) {
        try {
            URL url = TextMain.class.getClassLoader().getResource(path);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
