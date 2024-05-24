package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActivityHistoryTest {
    private ActivityHistory activity;


    @BeforeEach
    void beforeEach() {
        activity = new ActivityHistory();
    activity.addEventToHistory("activity ");
    }

    @Test
    void addEventToHistory() {
        assertEquals(1, activity.getHistory().size());
        activity.addEventToHistory("activity 2");
        assertEquals(2, activity.getHistory().size());
    }

    @Test
    void removeEventFromHistory() {
        assertEquals(1, activity.getHistory().size());
        ActivityLog log = activity.getHistory().get(0);
        activity.removeEventFromHistory(log);
        assertEquals(0, activity.getHistory().size());

    }

    @Test
    void listHistory() {
        String result = activity.listHistory();
        assertNotNull(result);
        ActivityLog log = activity.getHistory().get(0);
        activity.removeEventFromHistory(log);
        assertEquals("No activities recorded.", activity.listHistory());
    }

    @Test
    void getHistory() {
        assertNotNull(activity.getHistory());
        ActivityLog log = new ActivityLog("test");
        assertThrows(UnsupportedOperationException.class,() ->
                activity.getHistory().add(log));
    }
}