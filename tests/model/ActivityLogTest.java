package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ActivityLogTest {
    private ActivityLog activityLog;

    @BeforeEach
    void beforeEach() {
        activityLog = new ActivityLog("valid");
    }
    @Test
    public void createActivityLogWithoutDescriptionThrows(){
        assertThrows(IllegalArgumentException.class, ActivityLog::new);
        assertThrows(IllegalArgumentException.class, () ->
                new ActivityLog("   "));
    }

    @Test
    void getDescription() {
        assertNotNull(activityLog.getDescription());
        assertEquals("valid", activityLog.getDescription());
    }

    @Test
    void getTimestamp() {
        LocalDateTime result = activityLog.getTimestamp();
        assertNotNull(result);
        assertTrue((activityLog.getTimestamp() != null));
    }

    @Test
    void viewInfo() {
        assertNotNull(activityLog.viewInfo());
    }
}