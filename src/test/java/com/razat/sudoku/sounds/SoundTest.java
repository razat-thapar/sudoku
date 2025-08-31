package com.razat.sudoku.sounds;

import javax.sound.sampled.*;
import java.io.File;

import static com.razat.sudoku.configs.SoundConfig.ERROR_SOUND;

public class SoundTest {
    public static void main(String[] args) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(SoundTest.class.getResource(ERROR_SOUND));
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            Thread.sleep(2000); // Wait for sound to finish
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

