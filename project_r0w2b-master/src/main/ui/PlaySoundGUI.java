package ui;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

// abstract class that provides the functionality to play a sound in its sub classes
public abstract class PlaySoundGUI {

    // EFFECTS: plays sound passed
    public void playSound(String soundName) {
        // code found from http://suavesnippets.blogspot.com/2011/06/add-sound-on-jbutton-click-in-java.html
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
}
