package model;

import exceptions.InvalidTimeException;

// represent an activity for the traveller  to do on their trip
public class Activity {
    private String address;
    private String name;
    private int time;
    private boolean done;

    // REQUIRES: non zero length address and activity name
    //           valid 24 hours time written as an integer
    // MODIFIES: this
    // EFFECTS: address is set to a, name is set to n, time is set to t, done is set to false
    public Activity(String n, String a, int t) throws InvalidTimeException {
        address = a;
        name = n;
        if ((t > 2359) || (t < 0)) {
            throw new InvalidTimeException();
        } else {
            time = t;
        }
        done = false;
    }

    // EFFECTS: returns the specifications of the activity
    public String toString() {
        return "Activity: " + getActivity() + ", Time: " + getTime() + ", Location: " + getLocation();
    }

    // EFFECTS: returns the address of the activity
    public String getLocation() {
        return address;
    }

    // EFFECTS: returns the name of the activity
    public String getActivity() {
        return name;
    }


    // EFFECTS: returns the time of the activity
    public int getTime() {
        return time;
    }

    // MODIFIES: this
    // EFFECTS: changes the value of done to true
    public void markCompleted() {
        done = true;
    }

    // EFFECTS: returns true if the activity is completed, false if not completed
    public boolean isItDone() {
        return done;
    }

    // REQUIRES: non zero string input
    // MODIFIES: this
    // EFFECTS: changes address of the activity
    public void setLocation(String a) {
        address = a;
    }

    // REQUIRES: non zero string input
    // MODIFIES: this
    // EFFECTS: changes the name of the activity
    public void setActivityName(String n) {
        name = n;
    }

    // REQUIRES: valid 24 hours time put as an integer
    // MODIFIES: this
    // EFFECTS: changes time of the activity
    public void setTime(int t) throws InvalidTimeException {
        if ((t > 2359) || (t < 0)) {
            throw new InvalidTimeException();
        } else {
            time = t;
        }
    }

    // EFFECTS: returns true if 2 activities are identical
    //                  false otherwise
    public boolean sameActivity(Activity other) {
        if ((address.equals(other.address)) && (name.equals(other.name)) && (time == other.time)) {
            return true;
        } else {
            return false;
        }
    }
}
