package model.tasks;

import model.enums.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FeedbackImplTest {

    public static final String VALID_TITLE = "Valid title";
    public static final String VALID_DESCRIPTION = "Valid description";
    private FeedbackImpl feedback;

    @BeforeEach
    void setUp() {

        feedback = FeedbackImpl.createFeedback(1, VALID_TITLE, VALID_DESCRIPTION, 4);
    }

    @Test
    void createFeedback() {
        assertNotNull(feedback);
        assertThrows(IllegalArgumentException.class, () -> FeedbackImpl.createFeedback(1, VALID_TITLE, VALID_DESCRIPTION, 11));
    }

    @Test
    void setStatus() {
        assertEquals(Status.NEW, feedback.getStatus());
        feedback.setStatus(Status.DONE.toString());
        assertEquals(Status.DONE, feedback.getStatus());
    }

    @Test
    void getRating() {
        assertEquals(4, feedback.getRating());
    }

    @Test
    void changeRating() {
        assertEquals(4, feedback.getRating());
        feedback.changeRating(9);
        assertEquals(9, feedback.getRating());
        assertThrows(IllegalArgumentException.class, () -> feedback.changeRating(11));
    }

    @Test
    void testToString() {
        assertNotNull(feedback.toString());
    }

    @Test
    void compareTo() {
        FeedbackImpl testfeedback = FeedbackImpl.createFeedback(1, VALID_TITLE, VALID_DESCRIPTION, 4);
        assertEquals(0, feedback.compareTo(testfeedback));
        testfeedback.changeRating(9);
        assertEquals(1, feedback.compareTo(testfeedback));
        testfeedback.changeRating(3);
        assertEquals(-1, feedback.compareTo(testfeedback));
        testfeedback.setTitle("A valid title");
        assertTrue(feedback.compareTo(testfeedback) > 0);
    }
}