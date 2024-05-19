package model.contracts.utils;

import model.contracts.Entity;
import model.contracts.tasks.Task;

import java.util.List;

public interface Taskable {
    List<Task> getTasks();
    void addTask (Task task);
    <T extends Task> void removeTask(T task);
    String displayAllTasks();

}
