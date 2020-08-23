package ui.editmenu;

import ui.PlaySoundGUI;
import ui.Traveller;
import ui.TravellerGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represents a frame accessed when you want to mark an activity as completed in an itinerary
public class MarkAsCompletedGUI extends PlaySoundGUI {
    private JPanel markCompletedPanel;
    private JTextField dayTextField;
    private JTextField nameTextField;
    private JButton doneButton;
    private JButton returnToMainMenuButton;
    private Traveller traveller;
    private int trip;

    // REQUIRES: valid trip index from list of itineraries
    // MODIFIES: this
    // EFFECTS: creates a MarkAsCompletedGUI object
    public MarkAsCompletedGUI(int trip) {
        JFrame frame = initializeEverything(trip);
        returnMainMenuListener(frame);
        doneButtonListener(trip);
    }

    // MODIFIES: this
    // EFFECTS: marks specified activity as completed
    private void doneButtonListener(int trip) {
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int day = Integer.parseInt(dayTextField.getText());
                String name = nameTextField.getText();
                traveller.markAsCompleted(trip, day, name);
                JOptionPane.showMessageDialog(null, "Activity Successfully Marked As Complete!");
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: closes current frame and opens the main menu again
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
    // EFFECTS: initializes the necessary fields of the class
    private JFrame initializeEverything(int trip) {
        this.traveller = Traveller.getInstance();
        JFrame frame = new JFrame("Mark Activity as Completed");
        frame.setContentPane(this.markCompletedPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        this.trip = trip;
        return frame;
    }
}
