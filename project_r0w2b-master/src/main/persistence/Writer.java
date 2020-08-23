package persistence;

import com.google.gson.Gson;
import model.Itinerary;

import java.io.*;
import java.util.List;

// A writer that can write itinerary data to a file
public class Writer {
    private PrintWriter printWriter;
    private Gson gson;
    private String jsonString;

    // REQUIRES: valid file path
    // MODIFIES: this
    // EFFECTS: creates a gson object and a printwriter object
    public Writer(File file) throws IOException {
        gson = new Gson();
        printWriter = new PrintWriter(file, "UTF-8");
    }

    // MODIFIES: this
    // EFFECTS: creates json string and saves it to the data file
    public void save(List<Itinerary> trips) {
        jsonString = gson.toJson(trips);
        printWriter.print(jsonString);
    }

    // MODIFIES: this
    // EFFECTS: close print writer
    public void close() {
        printWriter.close();
    }
}
