package com.razat.sudoku.sounds;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class SoundPlayer {
    public static boolean isMuted = false;

    public static void playSound(String resourcePath) {
        if (isMuted) return;

        try {
            URL soundURL = SoundPlayer.class.getResource(resourcePath);
            if (soundURL == null) {
                System.err.println("Sound file not found: " + resourcePath);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundURL);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}


