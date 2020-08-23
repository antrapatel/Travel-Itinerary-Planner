package model;

import exceptions.InvalidTimeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DayTest {
    private Day day;
    private Activity a;
    private Activity b;

    @BeforeEach
    void runBefore() {
        day = new Day("Barcelona", 122420, "Best Western");
        try {
            a = new Activity("skydiving", "100 Something street", 200);
            b = new Activity("food tour", "780 Yay blvd", 1700);
        } catch (InvalidTimeException e1) {
            fail();
        }
    }

    @Test
    void testConstructor() {
        Day d = new Day("Barcelona", 122420, "Best Western");

    }

    @Test
    public void testAddActivity() {
        day.addActivity(a);
        assertEquals(1, day.getNumberActivities());
    }

    @Test
    void testAddMultipleActivities() {
        day.addActivity(a);
        day.addActivity(b);
        assertEquals(2, day.getNumberActivities());
    }

    @Test
    void testRemoveActivity() {
        day.addActivity(a);
        day.addActivity(b);

        assertTrue(day.removeActivity("food tour"));
        assertFalse(day.isActivity("food tour"));

    }

    @Test
    void testIsActivity() {
        day.addActivity(a);
        day.addActivity(b);
        assertTrue(day.isActivity("food tour"));
        assertTrue(day.isActivity("skydiving"));
    }

    @Test
    void testIsNotActivity() {
        day.addActivity(a);
        day.addActivity(b);
        assertFalse(day.isActivity("le sagrada familia"));
    }

    @Test
    void testRemoveActivityNotValid() {
        day.addActivity(a);
        day.addActivity(b);
        assertFalse(day.removeActivity("la sagrada familia"));
        assertEquals(2, day.getNumberActivities());

    }

    @Test
    void testGetCity() {
        assertEquals("Barcelona", day.getCity());
    }

    @Test
    void testGetDate() {
        assertEquals(122420, day.getDate());
    }

    @Test
    void testGetHotel() {
        assertEquals("Best Western", day.getHotel());
    }

    @Test
    void testSetCity() {
        day.setCity("Madrid");
        assertEquals("Madrid", day.getCity());
    }

    @Test
    void testSetDate() {
        day.setDate(122520);
        assertEquals(122520, day.getDate());
    }

    @Test
    void testSetHotel() {
        day.setHotel("Fairmount");
        assertEquals("Fairmount", day.getHotel());
    }

    @Test
    void testToString() {
        day.addActivity(a);
        day.addActivity(b);

        assertEquals("122420:\n" +
                "Activity: skydiving, Time: 200, Location: 100 Something street\n" +
                "Activity: food tour, Time: 1700, Location: 780 Yay blvd\n", day.toString());
    }

    @Test
    void testNumberOfActivities() {
        assertEquals(0, day.getNumberActivities());
        day.addActivity(a);
        assertEquals(1, day.getNumberActivities());
        day.addActivity(b);
        assertEquals(2, day.getNumberActivities());

    }

    @Test
    void testGetList() {
        day.addActivity(a);
        day.addActivity(b);
        assertEquals(2, day.getPlanForDay().size());
        assertEquals("skydiving", day.getPlanForDay().get(0).getActivity());
        assertEquals("food tour", day.getPlanForDay().get(1).getActivity());
    }

    @Test
    void testSameDay() {
        day.addActivity(a);
        day.addActivity(b);

        Day otherDay = new Day("Barcelona", 122420, "Best Western");
        otherDay.addActivity(a);
        otherDay.addActivity(b);

        assertTrue(day.sameDay(otherDay));
    }

    @Test
    void testNotSameDayDifferentActivities() {
        day.addActivity(b);

        Day otherDay = new Day("Barcelona", 122420, "Best Western");
        otherDay.addActivity(a);


        assertFalse(day.sameDay(otherDay));
    }


}
