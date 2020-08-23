package ui;

import ui.mainmenu.CreateNewGUI;
import ui.mainmenu.DisplayExistingGUI;
import ui.mainmenu.EditExistingGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represents the main menu that is displayed when you run the application
public class TravellerGUI extends PlaySoundGUI {
    private Traveller traveller;
    private JButton createNewTripItineraryButton;
    private JPanel mainMenuPanel;
    private JButton editExistingTripItineraryButton;
    private JButton displayAnExistingItineraryButton;
    private JButton saveItinerariesButton;
    private JButton loadPreviousItinerariesButton;
    private JButton quitButton;
    private JFrame frame;

    // MODIFIES: this
    // EFFECTS: creates an instance of a TravellerGUI object
    public TravellerGUI(int i) {
        whichTraveller(i);
        initializeEverything();
        saveListener();
        createListener();
        editListener();
        displayListener();
        loadListener();
        quitListener();
    }

    // MODIFIES: this
    // EFFECTS: closes the application
    private void quitListener() {
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    // EFFECTS: loads previously saved  itineraries
    private void loadListener() {
        loadPreviousItinerariesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("robotblip.wav"); //audio clip found at http://soundbible.com/1682-Robot-Blip.html
                traveller.loadItinerary();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: closes current frame and opens DisplayExistingGUI panel
    private void displayListener() {
        displayAnExistingItineraryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("robotblip.wav"); //audio clip found at http://soundbible.com/1682-Robot-Blip.html
                frame.dispose();
                new DisplayExistingGUI();
                frame.setVisible(false);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: closes current frame and opens EdditExistingGUI panel
    private void editListener() {
        editExistingTripItineraryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("robotblip.wav"); //audio clip found at http://soundbible.com/1682-Robot-Blip.html
                frame.dispose();
                new EditExistingGUI();
                frame.setVisible(false);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: closes current frame and opens CreateNewGUI panel
    private void createListener() {
        createNewTripItineraryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("robotblip.wav"); //audio clip found at http://soundbible.com/1682-Robot-Blip.html
                frame.dispose();
                new CreateNewGUI();
                frame.setVisible(false);
            }
        });
    }

    // EFFECTS: saves itineraries for further use
    private void saveListener() {
        saveItinerariesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("robotblip.wav"); //audio clip found at http://soundbible.com/1682-Robot-Blip.html
                traveller.saveItinerary();
                JOptionPane.showMessageDialog(null, "Itineraries saved to file " + traveller.ITINERARY_FILE);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: initializes all fields necessary
    private void initializeEverything() {
        frame = new JFrame("Main Menu");
        frame.setContentPane(this.mainMenuPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: decides which traveller to initialize
    //          either a new traveller when the program first starts or one passed from another frame
    private void whichTraveller(int i) {
        traveller = Traveller.getInstance();
        if (i == -1) {
            traveller.loadItinerary();
        }
    }
}