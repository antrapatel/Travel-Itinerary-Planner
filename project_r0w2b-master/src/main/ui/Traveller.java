package ui;

import exceptions.InvalidDayException;
import exceptions.InvalidTimeException;
import model.Activity;
import model.Itinerary;
import model.Day;

import persistence.Reader;
import persistence.Writer;

import java.io.File;
import java.io.IOException;
import java.util.*;

// represents a person who can create itineraries to travel with
public class Traveller {
    public List<Itinerary> trips;
    public Scanner input;
    public static final String ITINERARY_FILE = "./data/itineraries.txt";
    private static Traveller instance = null;


    //MODIFIES: this
    //EFFECTS: runs the traveller application and initializes the parameters
    private Traveller() {
        trips = new ArrayList<>();
        input = new Scanner(System.in);
        //runTraveller();
    }

    // MODIFIES: this
    // EFFECTS: provides the one instance of traveller object to other classes
    public static Traveller getInstance() {
        if (instance == null) {
            instance = new Traveller();
        }
        return instance;
    }

    // EFFECTS: returns the list if itineraries field
    public List<Itinerary> getTripsList() {
        return trips;
    }

    // MODIFIES: this
    // EFFECTS: collects user input and processes it
//    public void runTraveller() {
//        // while loop idea taken from TellerApp
//        boolean keepGoing = true;
//        String command = null;
//        input = new Scanner(System.in);
//
//        loadItinerary();
//
//        while (keepGoing) {
//            displayMenu();
//            command = input.next();
//            command = command.toLowerCase();
//
//            if (command.equals("q")) {
//                keepGoing = false;
//            } else {
//                processCommand(command);
//            }
//        }
//    }

    // MODIFIES: this
    // EFFECTS: processes user command
//    public void processCommand(String command) {
//        if (command.equals("n")) {
//            createNewItinerary();
//        } else if (command.equals("e")) {
//            editExistingItinerary();
//        } else if (command.equals("d")) {
//            displayItinerary();
//        } else if (command.equals("s")) {
//            saveItinerary();
//        } else if (command.equals("l")) {
//            loadItinerary();
//        } else {
//            System.out.println("Selection not valid\n");
//        }
//    }

    // EFFECTS: loads previous itineraries from data file
    public void loadItinerary() {
        try {
            Reader reader = new Reader(new File(ITINERARY_FILE));
            trips = reader.getData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: saves itineraries to a data file
    public void saveItinerary() {
        try {
            Writer writer = new Writer(new File(ITINERARY_FILE));
            writer.save(trips);
            writer.close();
            //System.out.println("Itineraries saved to file " + ITINERARY_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: displays wanted itinerary
    public String displayItinerary(String input) {
//        System.out.print("What is the name of the trip?: ");
//        input.nextLine();
        String name = input;

        for (int i = 0; i < trips.size(); i++) {
            if (trips.get(i).getName().equals(name)) {
                return trips.get(i).toString();

            }
        }
        return "Trip not found.";
    }

    // REQUIRES: existing itinerary passed to input
    // MODIFIES: this
    // EFFECTS: processes user input
    public void editExistingItinerary() {
        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);

        int index = getTripInt("");

        keepGoing = isInvalid(index);
        while (keepGoing) {
            displayEditMenu(index);
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("m")) {
                keepGoing = false;
            } else {
                processEditCommand(command, index);
            }
        }
    }

    // EFFECTS: returns the index of the trip in the list of itineraries if valid name is passed
    //          returns -1 otherwise
    public int getTripInt(String name) {
        //System.out.print("Which itinerary would you like to edit?: ");
        //String name = input.nextLine();

        int index = -1;
        for (int i = 0; i < trips.size(); i++) {
            if (trips.get(i).getName().equals(name)) {
                index = i;
            }
        }
        return index;
    }

    // EFFECTS: returns true if trip index is valid
    public boolean isInvalid(int index) {
        if (index == -1) {
            System.out.print("Not a valid trip. Going back to main menu.\n");
            return false;
        }
        return true;
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    public void processEditCommand(String command, int i) {
        if (command.equals("a")) {
            //addActivity(i);
        } else if (command.equals("r")) {
            //removeActivity(i);
        } else if (command.equals("c")) {
            //markAsCompleted(i);
        } else {
            System.out.println("Selection not valid\n");
        }
    }

    // REQUIRES: valid index of itinerary passed
    // MODIFIES: this
    // EFFECTS: marks wanted activity as completed
    public void markAsCompleted(int i, int day, String name) {
//        System.out.print("What day was the activity on? : ");
//        int day = input.nextInt();
//
//        System.out.print("What was the name of the activity?: ");
//        input.nextLine();
//        String name = input.nextLine();

        Day d = trips.get(i).getItinerary().get(day - 1);

        for (int index = 0; index < d.getPlanForDay().size(); index++) {
            if (d.getPlanForDay().get(i).getActivity().equals(name)) {
                d.getPlanForDay().get(i).markCompleted();
                //System.out.println("Activity marked as completed successfully");
                return;
            }
        }
    }

    // REQUIRES: valid index of itinerary passed
    // MODIFIES: this
    // EFFECTS: removes activity is found in day
    //          returns to menu otherwise
    public boolean removeActivity(int i, int day, String name) {
//        System.out.print("What day was the activity on? : ");
//        int day = input.nextInt();
//
//        System.out.print("What was the name of the activity?: ");
//        input.nextLine();
//        String name = input.nextLine();

        if (trips.get(i).getItinerary().get(day - 1).removeActivity(name)) {
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES: valid index of itinerary passed
    // MODIFIES: this
    // EFFECTS: adds activity to wanted day
    public void addActivity(int i, int d, String n, String l, int t) throws InvalidTimeException, InvalidDayException {
//        System.out.print("What day do you want to add the activity on? : ");
//        int day = input.nextInt();

        if ((d > trips.get(i).getItinerary().size()) || (d < 1)) {
            throw new InvalidDayException();
        } else {
            trips.get(i).getItinerary().get(d - 1).addActivity(new Activity(n, l, t));
        }

//        System.out.print("What is the name of the activity?: ");
//        input.nextLine();
//        String name = input.nextLine();
//
//        System.out.print("What is the location of the activity?: ");
//        String location = input.nextLine();
//
//        System.out.print("What is the time of the activity?: ");
//        int time = input.nextInt();

//        if ((time > 2359) || (time < 0)) {
//            System.out.println("Invalid time. You will be returned to the menu.");
//            return;
//        }



        //System.out.println("Activity added successfully!");
    }

    // EFFECTS: displays menu of options to edit itinerary to user
    public void displayEditMenu(int i) {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> Add activity");
        System.out.println("\tr -> Remove Activity");
        System.out.println("\tc -> Mark Activity as completed");
        System.out.println("\tm -> Return to main menu");
    }

    // MODIFIES: this
    // EFFECTS: creates a new trip itinerary
    public void createNewItinerary(String name, int days) {
//        System.out.print("Enter name of new trip: ");
//        input.nextLine();
//        String name = input.nextLine();

//        System.out.print("Enter length of trip: ");
//        int days = input.nextInt();

        trips.add(new Itinerary(days, name));

//        System.out.print("Trip created successfully!\n");
    }

    // EFFECTS: displays menu of options to user
    public void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tn -> Create new trip itinerary");
        System.out.println("\te -> Edit Existing trip itinerary");
        System.out.println("\td -> Display an existing itinerary");
        System.out.println("\ts -> Save itineraries");
        System.out.println("\tl -> Load previous itineraries");
        System.out.println("\tq -> Quit");
    }
}
