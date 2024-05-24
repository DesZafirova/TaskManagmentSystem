package model;

import model.contracts.tasks.Task;
import model.tasks.StoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EntityImplTest {
    private EntityImpl entity;
    private Task task;


    @BeforeEach
    void beforeEach() {
        this.entity = new MemberImpl("valid");
        task = StoryImpl.createStory(1, "some title", "some description", "Medium", "Medium");
        entity.addTask(task);

    }

    @Test
    void getTasks() {
        List<Task> tasks = entity.getTasks();
        assertEquals(1, tasks.size());
        assertNotNull(tasks);
    }

    @Test
    void getActivityHistory() {
        List<ActivityLog> history = entity.getActivityHistory();
        assertNotNull(history);
        assertEquals(1, history.size());
    }

    @Test
    void getName() {
        String name = entity.getName();
        assertNotNull(name);
        assertEquals("valid", name);
    }

    @Test
    void setName() {
        entity.setName("a".repeat(5));
        assertEquals("a".repeat(5), entity.getName());
        assertThrows(IllegalArgumentException.class, () -> entity.setName("a"));
        assertThrows(IllegalArgumentException.class, () -> entity.setName("a".repeat(30)));
    }

    @Test
    void getHistory() {
        List<ActivityLog> activityLog = entity.getHistory();
        assertNotNull(activityLog);
        assertEquals(1, activityLog.size());
    }

    @Test
    void addHistoryLog() {
        assertEquals(1, entity.getActivityHistory().size());
        entity.addHistoryLog("testLog");
        assertEquals(2, entity.getActivityHistory().size());
        assertEquals("testLog", entity.getActivityHistory().get(1).getDescription());

    }

    @Test
    void addTask() {
        assertEquals(1, entity.getTasks().size());
        entity.removeTask(task);
        assertEquals(0, entity.getTasks().size());
        entity.addTask(task);
        assertEquals(1, entity.getTasks().size());
    }

    @Test
    void removeTask() {
        assertEquals(1, entity.getTasks().size());
        entity.removeTask(task);
        assertEquals(0, entity.getTasks().size());
        assertThrows(IllegalArgumentException.class, () -> entity.removeTask(task));

    }

    @Test
    void displayAllTasks() {
        String result = entity.displayAllTasks();
        assertNotNull(result);
        assertEquals(2, result.split("%s".formatted(System.lineSeparator())).length);
        entity.removeTask(task);
        result = entity.displayAllTasks();
        assertEquals("%s %s is empty.%n".formatted(entity.getRealName(), entity.getName()), result);
    }

    @Test
    void displayHistory() {
        assertNotNull(entity.displayHistory());
        assertEquals(1, entity.displayHistory().split("%s".formatted(System.lineSeparator())).length);
    }

    @Test
    void testToString() {
        assertNotNull(entity.toString());
        assertNotNull(entity.toString(), entity.toString());
        assertEquals(6, entity.toString().split("%s".formatted(System.lineSeparator())).length);
    }
}