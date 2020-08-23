package persistence;

import exceptions.InvalidTimeException;
import model.Activity;
import model.Itinerary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.Traveller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WriterTest {
    private Writer writer;

    @BeforeEach
    void runBefore() {
        try {
            writer = new Writer(new File("./data/test2.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    void testConstructor() {
        try {
            Writer w = new Writer(new File("./data/test2.txt"));
            w.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Test
    void testSave() {
        List<Itinerary> newTrips = new ArrayList<>();
        newTrips.add(new Itinerary(7, "Spain"));
        try {
            newTrips.get(0).getItinerary().get(1).addActivity(new Activity("food tour", "madrid", 1300));
        } catch (InvalidTimeException e) {
            fail();
        }

        writer.save(newTrips);
        writer.close();

        try {
            Reader reader = new Reader(new File("./data/test2.txt"));

            List<Itinerary> readTrips = reader.getData();

            for (Itinerary i : newTrips) {
                int count = 0;
                assertTrue(i.sameItinerary(readTrips.get(count)));
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    void testClose() {
        writer.close();
    }
}

