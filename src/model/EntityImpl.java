package model;


import model.contracts.Entity;
import model.contracts.tasks.Task;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.junit.jupiter.api.Test;
import utils.contracts.PrintableName;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.List;

public abstract class EntityImpl implements Entity, PrintableName {
    public static final String REMOVED_TASK = "Task with ID: %s was removed from %s - %s.";
    public static final String TASK_NOT_EXIST = "No task with ID: %s present in %s - %s";

    protected String name;
    protected List<Task> tasks;
    protected ActivityHistory activityHistory;

    protected EntityImpl(String name) {
        this.setName(name);
        this.tasks = new ArrayList<>();
        this.activityHistory = new ActivityHistory();
    }
    public List<Task> getTasks() {
        return List.copyOf(tasks);
    }

    public List<ActivityLog> getActivityHistory() {
        return activityHistory.getHistory();
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        validateName(name);
        this.name = name;
    }
    protected abstract void validateName(String name);
    @Override
    public List<ActivityLog> getHistory() {
        return activityHistory.getHistory();
    }
    @Override
    public void addHistoryLog(String event) {
        activityHistory.addEventToHistory(event);
    }
    @Override
    public void addTask(Task task) {
        tasks.add(task);
        this.activityHistory.addEventToHistory("Task with ID: %d was added.".formatted(task.getID()));
    }
    @Override
    public void removeTask(Task task) {
        if (tasks.contains(task)) {
            tasks.remove(task);
            activityHistory.addEventToHistory(REMOVED_TASK.formatted(
                    task.getID(),
                    getRealName(),
                    this.name));
            return;
        }
        throw new IllegalArgumentException(TASK_NOT_EXIST.formatted(
                task.getID(),
                getRealName(),
                this.name));
    }
    @Override
    public String displayAllTasks() {
        if(tasks.isEmpty()){
            System.out.printf("%s %s is empty.%n",getRealName(), name);
            return String.format("%s %s is empty.%n",getRealName(), name);
        }
        StringBuilder sb = new StringBuilder();
        tasks.forEach(t -> sb.append(t.toString()).append(System.lineSeparator()));
        System.out.print(sb);
        return sb.toString();
    }
    @Override
    public String displayHistory() {
        return activityHistory.listHistory();
    }@Override
    public String toString() {
        return """
                %s: %s
                
                =================
                Tasks: 
                %s
                
                =================
                History: 
                %s
                =================
                """.formatted(getRealName(),
                this.name,
                displayAllTasks().trim(),
                displayHistory());
    }

}
