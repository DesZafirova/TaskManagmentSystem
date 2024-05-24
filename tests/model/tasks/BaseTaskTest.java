package model.tasks;

import model.ActivityLog;
import model.CommentImpl;
import model.contracts.Comment;
import model.contracts.tasks.Task;
import model.enums.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BaseTaskTest {
    private static final String VALID_TITLE_LENGTH = "x".repeat(12);
    private static final String INVALID_TITLE_LENGTH = "x".repeat(5);
    private static final String VALID_DESCRIPTION_LENGTH = "x".repeat(18);
    private static final String INVALID_DESCRIPTION_LENGTH = "x".repeat(5);
    public Task task;

    @BeforeEach
    void setUp() {
        task = new FeedbackImpl(1, "valid title", "valid description", 8);

    }

    @Test
    void getComments() {
        assertEquals(0, task.getComments().size());
        Comment comment = new CommentImpl("author", "content");
        task.addComment(comment);
        assertNotNull(task.getComments());
        assertThrows(UnsupportedOperationException.class,
                () -> task.getComments().add(comment));
    }

    @Test
    void getHistory() {
        List<ActivityLog> activityLog = task.getHistory();
        assertNotNull(activityLog);
        assertEquals(1, activityLog.size());
    }

    @Test
    void addHistoryLog() {
        assertEquals(1, task.getHistory().size());
        task.addHistoryLog("testLog");
        assertEquals(2, task.getHistory().size());
        assertEquals("testLog", task.getHistory().get(1).getDescription());
    }

    @Test
    void displayHistory() {
        assertNotNull(task.displayHistory());
        assertEquals(1, task.displayHistory().split("%s".formatted(System.lineSeparator())).length);
    }

    @Test
    void getID() {
        assertEquals(1, task.getID());
        Task testTask = StoryImpl.createStory(2, "title for bug", "title for description", "Medium", "Small");
        assertEquals(2, testTask.getID());

    }

    @Test
    void getTitle() {
        String result = task.getTitle();
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals("valid title", result)
        );
    }

    @Test
    void getDescription() {
        String result = task.getDescription();
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals("valid description", result)
        );
    }

    @Test
    void getStatus() {
        String result = task.getStatus().name();
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals("NEW", result)
        );
    }

    @Test
    void addComment() {
        assertEquals(0, task.getComments().size());
        Comment comment = new CommentImpl("author", "content");
        task.addComment(comment);
        assertNotNull(task.getComments());
        assertEquals(1, task.getComments().size());

    }

    @Test
    void setTitle() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> task.setTitle(INVALID_TITLE_LENGTH));
        assertEquals("Title length must be between 10 and 100 characters.", exception.getMessage());
        assertDoesNotThrow(() -> task.setTitle(VALID_TITLE_LENGTH));
        task.setTitle("some title");
        assertEquals("some title", task.getTitle());
    }

    @Test
    void setDescription() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> task.setDescription(INVALID_DESCRIPTION_LENGTH));
        assertEquals("Description length must be between 10 and 500 characters.", exception.getMessage());
        assertDoesNotThrow(() -> task.setDescription(VALID_DESCRIPTION_LENGTH));
        task.setDescription("some description");
        assertEquals("some description", task.getDescription());
    }

    @Test
    void setEnumValue() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> task.setStatus("InProgress"));
        assertEquals("Provided invalid Status.", exception.getMessage());
        task.setStatus("Scheduled");
        String log = task.getHistory().get(1).getDescription();
        assertEquals(Status.SCHEDULED, task.getStatus());
        assertEquals("Changed Status from NEW to SCHEDULED", log);
    }
}