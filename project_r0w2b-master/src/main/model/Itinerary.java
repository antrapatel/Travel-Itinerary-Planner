package model;

import java.util.*;

// represents the plan of the entire trip
public class Itinerary {
    List<Day> trip;
    String name;

    //MODIFIES: this
    //EFFECTS: creates a new itinerary
    public Itinerary(int days, String n) {
        trip = new ArrayList<>();
        name = n;
        for (int i = 1; i <= days; i++) {
            trip.add(new Day("", i, ""));
        }
    }

    //EFFECTS: returns the list of days in the itinerary
    public List<Day> getItinerary() {
        return trip;
    }

    //EFFECTS: returns the name of the trip
    public String getName() {
        return name;
    }

    //EFFECTS: returns a written version of the itinerary
    public String toString() {
        String output = name + ":\n";
        for (Day d : trip) {
            output = output + d;
        }
        return output;
    }

    //EFFECTS: returns the number of days in the trip
    public int getNumberOfDays() {
        return trip.size();
    }

    //MODIFIES: this
    //EFFECTS: adds num days to trip
    public void addDays(int num) {
        for (int i = 1; i <= num; i++) {
            trip.add(new Day("", 0, ""));
        }
    }

    // EFFECTS: returns true if 2 itineraries are identical
    //                  false otherwise
    public boolean sameItinerary(Itinerary other) {
        for (int i = 0; i < trip.size(); i++) {
            if ((!trip.get(i).sameDay(other.trip.get(i))) || (!name.equals(other.name))) {
                return false;
            }
        }
        return true;
    }
}
