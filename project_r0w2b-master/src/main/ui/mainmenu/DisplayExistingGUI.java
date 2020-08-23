package ui.mainmenu;

import ui.PlaySoundGUI;
import ui.Traveller;
import ui.TravellerGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represents a frame accessed when you want to display an existing itinerary
public class DisplayExistingGUI extends PlaySoundGUI {
    private JPanel displayExistingPanel;
    private JLabel nameOfTripLabel;
    private JTextField nameOfTripTextField;
    private JButton returnToMainMenuButton;
    private Traveller traveller;

    // MODIFIES: this
    // EFFECTS: creates an instance of the DisplayExistingGUI object
    public DisplayExistingGUI() {
        JFrame frame = initializeEverything();
        nameTextFieldListener();
        returnMainMenuListener(frame);
    }

    // MODIFIES: this
    // EFFECTS: initializes all necessary fields of the class
    private JFrame initializeEverything() {
        this.traveller = Traveller.getInstance();
        JFrame frame = new JFrame("Display Existing Itineraries");
        //setTitle("Display Existing Itineraries");
        frame.setContentPane(this.displayExistingPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        return frame;
    }

    // MODIFIES: this
    // EFFECTS: displays the itinerary for the provided name of trip
    private void nameTextFieldListener() {
        nameOfTripTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = nameOfTripTextField.getText();
                String message = traveller.displayItinerary(input);
                JOptionPane.showMessageDialog(null, message);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: closes current frame and goes back ot main menu
    private void returnMainMenuListener(JFrame frame) {
        returnToMainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("robotblip2.wav"); // audio clip found at http://soundbible.com/1669-Robot-Blip-2.html
                frame.dispose();
                new TravellerGUI(1);
                frame.setVisible(false);
            }
        });
    }
}
