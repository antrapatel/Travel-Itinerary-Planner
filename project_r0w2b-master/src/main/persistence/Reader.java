package persistence;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.Itinerary;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

// A reader that can read itinerary data from a file
public class Reader {
    private List<String> tripsList;
    private Gson gson;
    private String tripsString;

    // REQUIRES: valid file path
    // MODIFIES: this
    // EFFECTS: creates a list of strings from the information in the data file
    public Reader(File file) throws IOException {
        tripsList = Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of itineraries after deserializing json string
    public List<Itinerary> getData() {
        tripsString = tripsList.get(0);
        gson = new Gson();
        Type type = new TypeToken<ArrayList<Itinerary>>() {
        }.getType();
        return gson.fromJson(tripsString, type);
    }
}
