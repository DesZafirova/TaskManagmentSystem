package model.tasks;

import exceptions.TaskHasNoAssigneeException;
import model.MemberImpl;
import model.contracts.Member;
import model.contracts.tasks.Bug;
import model.contracts.tasks.Task;
import model.enums.BugSeverity;
import model.enums.Priority;
import model.enums.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.print.attribute.standard.Severity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BugImplTest {
    private static final String VALID_TITLE_LENGTH = "x".repeat(10);
    private static final String INVALID_TITLE_LENGTH = "x".repeat(5);
    private static final String VALID_DESCRIPTION_LENGTH = "x".repeat(10);
    private static final String INVALID_DESCRIPTION_LENGTH = "x".repeat(5);
    private BugImpl task;
    private Member member;


    @BeforeEach
    void beforeEach() {
        task = BugImpl.createBug(1, VALID_TITLE_LENGTH, VALID_DESCRIPTION_LENGTH, "HIGH", "MAJOR", "step, step2, step3");
        member = new MemberImpl("testMember");
        task.setAssignee(member);
    }

    @Test
    void createBug() {

    }

    @Test
    void testCreateBug() {
    }

    @Test
    void getPriority() {
        Priority result = task.getPriority();
        assertNotNull(result.name());
        assertEquals(Priority.HIGH, result);
    }

    @Test
    void getSeverity() {
        BugSeverity result = task.getSeverity();
        assertNotNull(result.name());
        assertEquals(BugSeverity.MAJOR, result);
    }

    @Test
    void getStepsToReproduce() {
        List<String> steps = task.getStepsToReproduce();
        assertNotNull(steps);
        assertEquals("step, step2, step3", String.join(", ", steps));
    }

    @Test
    void changeSeverity() {
        assertNotNull(task.getSeverity());
        assertEquals(BugSeverity.MAJOR, task.getSeverity());
        task.changeSeverity("CRITICAL");
        assertEquals(BugSeverity.CRITICAL, task.getSeverity());
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> task.changeSeverity("INVALID"));
        assertEquals("Provided invalid Severity.", ex.getMessage());
    }

    @Test
    void changePriority() {
        assertNotNull(task.getPriority());
        assertEquals(Priority.HIGH, task.getPriority());
        task.changePriority("MEDIUM");
        assertEquals(Priority.MEDIUM, task.getPriority());
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> task.changePriority("INVALID"));
        assertEquals("Provided invalid Priority.", ex.getMessage());
    }

    @Test
    void setStatus() {
        assertNotNull(task.getStatus());
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> task.setStatus("INVALID"));
        assertEquals("Provided invalid Status.", ex.getMessage());
        task.setStatus("DONE");
        String log = task.getHistory().get(1).getDescription();
        assertEquals(Status.DONE, task.getStatus());
        assertEquals("Changed Status from ACTIVE to DONE", log);
    }

    @Test
    void testToString() {
        String result = "ID#%d | Type: %s | Status: %s | Priority: %s | Severity: %s | Assignee: %s%nTitle: %s%n%n"
                .formatted(task.getID(), task.getRealName(),
                        task.getStatus(), task.getPriority(),
                        task.getSeverity(), task.hasAssignee() ? task.getAssignee().getName() : "Unassigned",
                        task.getTitle());
        assertNotNull(task.toString());
        assertEquals(result, task.toString());
    }

    @Test
    void setAssignee() {
        assertNotNull(member.getName());
        assertEquals("testMember", member.getName());
        Member newMember = new MemberImpl("newMember");
        task.setAssignee(newMember);
        assertEquals("testMember", member.getName());

    }

    @Test
    void hasAssignee() {
        assertTrue(task.hasAssignee());
        BugImpl bug = BugImpl.createBug(2, VALID_TITLE_LENGTH, VALID_DESCRIPTION_LENGTH, "HIGH", "MINOR", "steps, steps");
        assertFalse(bug.hasAssignee());
    }

    @Test
    void getAssignee() {
        BugImpl bug = BugImpl.createBug(2, VALID_TITLE_LENGTH, VALID_DESCRIPTION_LENGTH, "HIGH", "MINOR", "steps, steps");

        TaskHasNoAssigneeException ex = assertThrows(TaskHasNoAssigneeException.class,
                () -> bug.getAssignee());
        assertEquals("%s with ID#%d is not currently assigned to any member.%n".formatted(bug.getRealName(), bug.getID()), ex.getMessage());
        assertDoesNotThrow(() -> task.getAssignee());

    }

    @Test
    void compareTo() {
        BugImpl bug1 = BugImpl.createBug(2, "Title ACDER", "Description A", "HIGH", "CRITICAL", "step, to, reproduce");
        BugImpl bug2 = BugImpl.createBug(3, "Title BCDER", "Description B", "MEDIUM", "MAJOR", "step, to, reproduce");
        BugImpl bug6 = BugImpl.createBug(7, "Title ACDER", "Description F", "HIGH", "CRITICAL", "step, to, reproduce");
        BugImpl bug3 = BugImpl.createBug(4, "Title ABEDH", "Description C", "LOW", "MINOR", "step, to, reproduce");
        BugImpl bug4 = BugImpl.createBug(5, "Title ACDER", "Description D", "MEDIUM", "CRITICAL", "step, to, reproduce");
        BugImpl bug5 = BugImpl.createBug(6, "Title ACDER", "Description E", "HIGH", "MAJOR", "step, to, reproduce");

        assertAll(
                () -> assertEquals(-1, bug1.compareTo(bug2)),
                () -> assertEquals(1, bug2.compareTo(bug1)),

                () -> assertEquals(-1, bug1.compareTo(bug4)),
                () -> assertEquals(1, bug4.compareTo(bug1)),

                () -> assertEquals(-1, bug1.compareTo(bug5)),
                () -> assertEquals(1, bug5.compareTo(bug1)),
                () -> assertEquals(0, bug1.compareTo(bug6))
                );

    }

}