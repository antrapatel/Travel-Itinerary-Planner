package model;

import exceptions.InvalidTimeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItineraryTest {
    private Itinerary itin;
    private Activity a;
    private Activity b;
    private Activity c;

    @BeforeEach
    void runBefore() {
        itin = new Itinerary(10, "Spain");
        try {
            a = new Activity("restaurant", "45 wow circle", 830);
            b = new Activity("food tour", "780 Yay blvd", 1700);
            c = new Activity("skydiving", "100 Something street", 200);
        } catch (InvalidTimeException e1) {
            fail("Exception should have not been thrown.");
        }
    }

    @Test
    void testConstructor() {
        Itinerary i = new Itinerary(10, "Spain");
        assertEquals(10, i.getNumberOfDays());
        assertEquals("Spain", i.getName());
    }

    @Test
    void testGetName() {
        assertEquals("Spain", itin.getName());
    }

    @Test
    void testToString() {

        itin.getItinerary().get(1).addActivity(a);
        itin.getItinerary().get(1).addActivity(b);
        itin.getItinerary().get(2).addActivity(c);

        assertEquals("Spain:\n" +
                "1:\n" +
                "2:\n" +
                "Activity: restaurant, Time: 830, Location: 45 wow circle\n" +
                "Activity: food tour, Time: 1700, Location: 780 Yay blvd\n" +
                "3:\n" +
                "Activity: skydiving, Time: 200, Location: 100 Something street\n" +
                "4:\n" +
                "5:\n" +
                "6:\n" +
                "7:\n" +
                "8:\n" +
                "9:\n" +
                "10:\n", itin.toString());
    }

    @Test
    void testGetNumberOfDays() {
        assertEquals(10, itin.getNumberOfDays());
    }

    @Test
    void testAddDays() {
        assertEquals(10, itin.getNumberOfDays());
        itin.addDays(3);
        assertEquals(13, itin.getNumberOfDays());
    }

    @Test
    void testSameItinerary() {
        Itinerary newItin = new Itinerary(10, "Spain");

        newItin.getItinerary().get(1).addActivity(a);
        newItin.getItinerary().get(1).addActivity(b);
        newItin.getItinerary().get(2).addActivity(c);


        itin.getItinerary().get(1).addActivity(a);
        itin.getItinerary().get(1).addActivity(b);
        itin.getItinerary().get(2).addActivity(c);

        assertTrue(itin.sameItinerary(newItin));

    }

    @Test
    void testNotSameItineraryDifferentActivities() {
        Itinerary newItin = new Itinerary(10, "Spain");

        newItin.getItinerary().get(1).addActivity(a);
        newItin.getItinerary().get(2).addActivity(c);


        itin.getItinerary().get(1).addActivity(b);
        itin.getItinerary().get(2).addActivity(c);

        assertFalse(itin.sameItinerary(newItin));

    }

    @Test
    void testNotSameItineraryDifferentName() {
        Itinerary newItin = new Itinerary(10, "France");

        newItin.getItinerary().get(1).addActivity(a);
        newItin.getItinerary().get(1).addActivity(b);
        newItin.getItinerary().get(2).addActivity(c);

        itin.getItinerary().get(1).addActivity(a);
        itin.getItinerary().get(1).addActivity(b);
        itin.getItinerary().get(2).addActivity(c);

        assertFalse(itin.sameItinerary(newItin));
    }

}