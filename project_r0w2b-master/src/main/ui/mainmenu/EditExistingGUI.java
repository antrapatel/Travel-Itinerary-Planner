package ui.mainmenu;

import ui.PlaySoundGUI;
import ui.Traveller;
import ui.TravellerGUI;
import ui.editmenu.AddActivityGUI;
import ui.editmenu.MarkAsCompletedGUI;
import ui.editmenu.RemoveActivityGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//represents a frame accessed when you want to edit an itinerary
public class EditExistingGUI extends PlaySoundGUI {
    private JButton addActivityButton;
    private JPanel editExistingPanel;
    private JButton removeActivityButton;
    private JButton markActivityAsCompletedButton;
    private JButton returnToMainMenuButton;
    private JTextField tripTextField;
    private Traveller traveller;

    // MODIFIES: this
    // EFFECTS: creates and instance of the EditExistingGUI object
    public EditExistingGUI() {
        JFrame frame = initializeEverything();
        addActivityListener(frame);
        removeActivityListener(frame);
        markCompletedListener(frame);
        returnMainMenuListener(frame);
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
    // EFFECTS: switches to markCompletedGUI frame and closes this one
    private void markCompletedListener(JFrame frame) {
        markActivityAsCompletedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                String tripName = tripTextField.getText();
                int trip = traveller.getTripInt(tripName);
                new MarkAsCompletedGUI(trip);
                frame.setVisible(false);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: switches to removeActivityGUI frame and closes this one
    private void removeActivityListener(JFrame frame) {
        removeActivityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                String tripName = tripTextField.getText();
                int trip = traveller.getTripInt(tripName);
                new RemoveActivityGUI(trip);
                frame.setVisible(false);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: switches to addActivityGUI frame and closes this one
    private void addActivityListener(JFrame frame) {
        addActivityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                String tripName = tripTextField.getText();
                int trip = traveller.getTripInt(tripName);
                new AddActivityGUI(trip);
                frame.setVisible(false);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: initialises necessary fields of this class
    private JFrame initializeEverything() {
        this.traveller = Traveller.getInstance();
        JFrame frame = new JFrame("Edit Menu");
        frame.setContentPane(this.editExistingPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        return frame;
    }
}
