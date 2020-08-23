package model;

import exceptions.InvalidTimeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ActivityTest {
    private Activity act;

    @BeforeEach
    void runBefore() {
        try {
            act = new Activity("skydiving", "100 Something street", 200);
        } catch (InvalidTimeException e) {
            fail();
        }
    }

    @Test
    void testConstructorValid() {
        Activity a = null;
        try {
            a = new Activity("skydiving", "100 Something street", 200);
        } catch (InvalidTimeException e) {
            fail();
        }
        assertEquals("100 Something street", a.getLocation());
        assertEquals("skydiving", a.getActivity());
        assertEquals(200, a.getTime());
        assertFalse(a.isItDone());
    }

    @Test
    void testConstructorInvalidTooHigh() {
        Activity a = null;
        try {
            a = new Activity("skydiving", "100 Something street", 2500);
            fail();
        } catch (InvalidTimeException e) {
        }
        assertNull(a);
    }

    @Test
    void testConstructorInvalidTooLow() {
        Activity a = null;
        try {
            a = new Activity("skydiving", "100 Something street", -80);
            fail();
        } catch (InvalidTimeException e) {
        }
        assertNull(a);
    }

    @Test
    void testGetLocation() {
        assertEquals("100 Something street", act.getLocation());
    }

    @Test
    void testGetActivity() {
        assertEquals("skydiving", act.getActivity());

    }

    @Test
    void testGetTime() {
        assertEquals(200, act.getTime());

    }

    @Test
    void testMarkCompleted() {
        act.markCompleted();
        assertTrue(act.isItDone());

    }

    @Test
    void testIsItDone() {
        assertFalse(act.isItDone());
        act.markCompleted();
        assertTrue(act.isItDone());

    }

    @Test
    void testSetLocation() {
        act.setLocation("200 Another Street");
        assertEquals("200 Another Street", act.getLocation());

    }

    @Test
    void testSetActivityName() {
        act.setActivityName("Snowboarding");
        assertEquals("Snowboarding", act.getActivity());
    }

    @Test
    void testSetTimeValid() {
        try {
            act.setTime(800);
        } catch (InvalidTimeException e) {
            fail();
        }
        assertEquals(800, act.getTime());
    }

    @Test
    void testSetTimeInvalidTooHigh() {
        try {
            act.setTime(2600);
            fail();
        } catch (InvalidTimeException e) {
        }
        assertEquals(200, act.getTime());
    }

    @Test
    void testSetTimeInvalidTooLow() {
        try {
            act.setTime(-99);
            fail();
        } catch (InvalidTimeException e) {
        }
        assertEquals(200, act.getTime());
    }

    @Test
    void testToString() {
        String activ = "Activity: " + act.getActivity();
        String tim = ", Time: " + act.getTime();
        String loca = ", Location: " + act.getLocation();
        assertEquals(activ + tim + loca, act.toString());
    }

    @Test
    void testSameActivity() {
        Activity newAct = null;
        try {
            newAct = new Activity("skydiving", "100 Something street", 200);
        } catch (InvalidTimeException e) {
            fail();
        }
        assertTrue(act.sameActivity(newAct));
    }

    @Test
    void testNotSameActivityName() {
        Activity newAct = null;
        try {
            newAct = new Activity("food tour", "100 Something street", 200);
        } catch (InvalidTimeException e) {
            fail();
        }
        assertFalse(act.sameActivity(newAct));
    }

    @Test
    void testNotSameActivityAddress() {
        Activity newAct = null;
        try {
            newAct = new Activity("skydiving", "55 other street", 200);
        } catch (InvalidTimeException e) {
            fail();
        }
        assertFalse(act.sameActivity(newAct));
    }

    @Test
    void testNotSameActivityTime() {
        Activity newAct = null;
        try {
            newAct = new Activity("skydiving", "100 Something street", 1700);
        } catch (InvalidTimeException e) {
            fail();
        }
        assertFalse(act.sameActivity(newAct));
    }

    @Test
    void testNotSameActivityAny() {
        Activity newAct = null;
        try {
            newAct = new Activity("food tour", "55 other street", 1700);
        } catch (InvalidTimeException e) {
            fail();
        }
        assertFalse(act.sameActivity(newAct));
    }

    @Test
    void testNotSameActivitySameName() {
        Activity newAct = null;
        try {
            newAct = new Activity("food tour", "55 other street", 200);
        } catch (InvalidTimeException e) {
            fail();
        }
        assertFalse(act.sameActivity(newAct));
    }

    @Test
    void testNotSameActivitySameLocation() {
        Activity newAct = null;
        try {
            newAct = new Activity("skydiving", "100 Something street", 1700);
        } catch (InvalidTimeException e) {
            fail();
        }
        assertFalse(act.sameActivity(newAct));
    }

    @Test
    void testNotSameActivitySameTime() {
        Activity newAct = null;
        try {
            newAct = new Activity("skydiving", "55 other street", 200);
        } catch (InvalidTimeException e) {
            fail();
        }
        assertFalse(act.sameActivity(newAct));
    }

}
