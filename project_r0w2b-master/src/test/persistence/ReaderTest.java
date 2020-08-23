package persistence;

import exceptions.InvalidTimeException;
import model.Activity;
import model.Itinerary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReaderTest {
    private Reader reader;

    @BeforeEach
    void runBefore() {
        try {
            reader = new Reader(new File("./data/test1.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    void testConstructor() {
        try {
            Reader r = new Reader(new File("./data/test1.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    void testGetData() {
        List<Itinerary> savedTrips = new ArrayList<>();
        savedTrips = reader.getData();

        List<Itinerary> newTrips = new ArrayList<>();
        newTrips.add(new Itinerary(7, "Spain"));
        try {
            newTrips.get(0).getItinerary().get(1).addActivity(new Activity("food tour", "madrid", 1300));
        } catch (InvalidTimeException e) {
            fail("Exception should not have ben thrown.");
        }


        for (Itinerary i : savedTrips) {
            int count = 0;
            assertTrue(i.sameItinerary(newTrips.get(count)));
            count++;
        }


    }

}
