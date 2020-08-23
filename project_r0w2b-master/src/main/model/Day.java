package model;

import java.util.*;

// represents the events planned on a day
public class Day {
    private List<Activity> planForDay;
    private String city;
    private int date;
    private String hotel;
    private int dayPosition;

    // MODIFIES: this
    // EFFECTS: creates an object of Day type
    public Day(String c, int d, String h) {
        planForDay = new ArrayList<>();
        city = c;
        date = d;
        hotel = h;
    }


    // EFFECTS: returns the list of activities on the day
    public List<Activity> getPlanForDay() {
        return planForDay;
    }

    // EFFECTS: converts the information of the day into a string
    public String toString() {
        String output = date + ":\n";
        for (Activity a : planForDay) {
            output = output + a + "\n";
        }
        return output;
    }

    // MODIFIES: this
    // EFFECTS: adds an activity do on the day
    public void addActivity(Activity a) {
        planForDay.add(a);
    }

    // MODIFIES: this
    // EFFECTS: removes an activity if previously scheduled on the day and returns true
    //          returns false otherwise
    public boolean removeActivity(String name) {
        for (int i = 0; i < planForDay.size(); i++) {
            if (planForDay.get(i).getActivity().equals(name)) {
                planForDay.remove(i);
                return true;
            }
        }
        return false;
    }

    // EFFECTS: returns true if the 2 activities are the same
    //          returns false otherwise
    public boolean isActivity(String name) {
        for (Activity a : planForDay) {
            if (a.getActivity().equals(name)) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: returns the name of the city
    public String getCity() {
        return city;
    }

    // EFFECTS: returns the date
    public int getDate() {
        return date;
    }

    // EFFECTS: changes name of the hotel for the night
    public String getHotel() {
        return hotel;
    }

    // MODIFIES: this
    // EFFECTS: changes the city of the day
    public void setCity(String c) {
        city = c;
    }

    // MODIFIES: this
    // EFFECTS: changes the date of the day
    public void setDate(int d) {
        date = d;
    }

    // MODIFIES: this
    // EFFECTS: changes the hotel of the day
    public void setHotel(String h) {
        hotel = h;
    }

    // EFFECTS: returns the number of activities on this day
    public int getNumberActivities() {
        return planForDay.size();
    }

    // EFFECTS: returns true if 2 days are identical
    //                  false otherwise
    public boolean sameDay(Day other) {
        for (int i = 0; i < planForDay.size(); i++) {
            if (!planForDay.get(i).sameActivity(other.planForDay.get(i))) {
                return false;
            }
        }
        return true;
    }
}

