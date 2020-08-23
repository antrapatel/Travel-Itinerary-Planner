package ui.editmenu;

import ui.PlaySoundGUI;
import ui.Traveller;
import ui.TravellerGUI;

import exceptions.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represents a new frame to be displayed when you want to add an activity to an itinerary
public class AddActivityGUI extends PlaySoundGUI {
    private JPanel addActivityPanel;
    private JTextField dayTextField;
    private JTextField nameTextField;
    private JTextField locationTextField;
    private JTextField timeTextField;
    private JButton doneButton;
    private JButton returnToMainMenuButton;
    private Traveller traveller;
    private int trip;

    // REQUIRES: valid trip index in list of itineraries in traveller class
    // MODIFIES: this
    // EFFECTS: creates an AddActivityGUI object
    public AddActivityGUI(int trip) {
        JFrame frame = initializeEverything(trip);
        returnMainMenuListener(frame);
        doneButtonListener(trip);
    }

    // MODIFIES: this
    // EFFECTS: helper method to intialize all field of JFrame and some fields
    private JFrame initializeEverything(int trip) {
        this.traveller = Traveller.getInstance();
        JFrame frame = new JFrame("Add New Activity");
        frame.setContentPane(this.addActivityPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        this.trip = trip;
        return frame;
    }

    // MODIFIES: this
    // EFFECTS: changes visible frame back to the main menu
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
    // EFFECTS: adds activity to given itinerary when button is pressed
    private void doneButtonListener(int trip) {
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int day = Integer.parseInt(dayTextField.getText());
                String name = nameTextField.getText();
                String location = locationTextField.getText();
                int time = Integer.parseInt(timeTextField.getText());
                try {
                    traveller.addActivity(trip, day, name, location, time);
                    JOptionPane.showMessageDialog(null, "Activity Successfully Added!");
                } catch (InvalidTimeException e1) {
                    JOptionPane.showMessageDialog(null, "Invalid Time Entered. Activity not added.");
                } catch (InvalidDayException e2) {
                    JOptionPane.showMessageDialog(null, "Invalid day entered.");
                }
            }
        });
    }
}
