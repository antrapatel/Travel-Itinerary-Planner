package ui.mainmenu;

import ui.PlaySoundGUI;
import ui.Traveller;
import ui.TravellerGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represents a frame accessed when you want to create a new itinerary
public class CreateNewGUI extends PlaySoundGUI {
    private JPanel createNewPanel;
    private JTextField tripNameTextField;
    private JTextField durationOfTripTextField;
    private JButton doneButton;
    private JLabel enterTripNameLabel;
    private JLabel enterDurationOfTripLabel;
    private JButton returnToMainMenuButton;
    private Traveller traveller;

    // MODIFIES: this
    // EFFECTS: creates a new CreateNewGUI object
    public CreateNewGUI() {
        JFrame frame = initializeEverything();
        returnMainMenuListener(frame);
        doneButtonListener();
    }

    // MODIFIES: this
    // EFFECTS: creates a new itinerary
    private void doneButtonListener() {
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = tripNameTextField.getText();
                String durationString = durationOfTripTextField.getText();
                int duration = Integer.parseInt(durationString);
                traveller.createNewItinerary(name, duration);
                JOptionPane.showMessageDialog(null, "Trip Successfully Created!");
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: closes this frame and opens the main menu
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

    // MODIFIES: this
    // EFFECTS: helper method that initializes necessary fields in the class
    private JFrame initializeEverything() {
        this.traveller = Traveller.getInstance();
        JFrame frame = new JFrame("Create New Itinerary");
        frame.setContentPane(this.createNewPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        return frame;
    }
}
