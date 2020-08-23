package ui.editmenu;

import ui.PlaySoundGUI;
import ui.Traveller;
import ui.TravellerGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represents a frame accessed when you want to remove an activity from a itinerary
public class RemoveActivityGUI extends PlaySoundGUI {
    private JPanel removeActivityPanel;
    private JTextField dayTextField;
    private JTextField nameTextField;
    private JButton doneButton;
    private JButton returnToMainMenuButton;
    private Traveller traveller;
    private int trip;

    // REQUIRES: valid trip index in list of itineraries in traveller
    // MODIFIES: this
    // EFFECTS: creates a RemoveActivityGUI object
    public RemoveActivityGUI(int trip) {
        JFrame frame = initializeEverything(trip);
        returnMainMenuListener(frame);
        doneButtonListener(trip);
    }

    // MODIFIES: this
    // EFFECTS: removes specified activity from day in itinerary
    private void doneButtonListener(int trip) {
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int day = Integer.parseInt(dayTextField.getText());
                String name = nameTextField.getText();
                if (traveller.removeActivity(trip, day, name)) {
                    JOptionPane.showMessageDialog(null, "Activity Removed Successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Activity not found");
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: closes current frame and opens main menu
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
    // EFFECTS: initializes necessary fields of the class
    private JFrame initializeEverything(int trip) {
        this.traveller = Traveller.getInstance();
        JFrame frame = new JFrame("Remove Activity");
        frame.setContentPane(this.removeActivityPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        this.trip = trip;
        return frame;
    }
}
