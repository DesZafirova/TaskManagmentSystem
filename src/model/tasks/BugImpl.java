package model.tasks;

import exceptions.TaskHasNoAssigneeException;
import jdk.jfr.Enabled;
import model.contracts.Member;
import model.contracts.tasks.AssignableTask;
import model.contracts.tasks.Task;
import model.contracts.utils.Assignable;
import model.contracts.tasks.Bug;
import model.enums.Priority;
import model.enums.Status;
import model.enums.BugSeverity;

import utils.ValidationHelpers;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class BugImpl extends BaseTask implements Bug, Assignable {
    public static final String PROVIDED_INVALID = "Provided invalid %s.";
    public static final String CREATED_MESSAGE = "Bug with id:%d was created.";

    private Priority priority;
    private BugSeverity severity;
    private final List<String> stepsToReproduce;
    private Member assignee;

    protected BugImpl(int id, String title, String description, Member assignee,
                      String priority, String severity, List<String> stepsToReproduce) {
        super(id, title, description, Status.ACTIVE);
        this.setPriority(priority);
        this.setSeverity(severity);
        this.assignee = assignee;

        this.stepsToReproduce = new ArrayList<>(stepsToReproduce);
        addHistoryLog(String.format(CREATED_MESSAGE, getID()));
    }

    public static BugImpl createBug(int i, String title, String description, Member assignee,
                                    String priority, String severity, String stepsToReproduce) {
        validateTitle(title);
        validateDescription(description);
        ValidationHelpers.validateEnum(priority, Priority.class);
        ValidationHelpers.validateEnum(severity, BugSeverity.class);
        List<String> steps = Arrays.stream(stepsToReproduce.split(", ")).toList();
        BugImpl bug = new BugImpl(i, title, description, assignee, priority, severity, steps);
        System.out.printf(String.format(TASK_CREATED, bug.getRealName(), bug.getID()));
        return bug;
    }

    public static BugImpl createBug(int i, String title, String description,
                                    String priority, String severity, String stepsToReproduce) {
        return createBug(i, title, description, null, priority, severity, stepsToReproduce);
    }


    @Override
    public Priority getPriority() {
        return this.priority;
    }

    @Override
    public BugSeverity getSeverity() {
        return this.severity;
    }

    @Override
    public List<String> getStepsToReproduce() {
        return List.copyOf(stepsToReproduce);
    }

    @Override
    public void changeSeverity(String value) {
        setSeverity(value);
    }

    @Override
    public void changePriority(String value) {
        setPriority(value);
    }

    @Override
    public void setStatus(String status) {
        this.status = setEnumValue(Status.getBugStatusList(), this.status, status, "Status", PROVIDED_INVALID);
    }

    private void setPriority(String priority) {
        this.priority = setEnumValue(Priority.values(), this.priority, priority, "Priority", PROVIDED_INVALID);
    }


    private void setSeverity(String severity) {
        this.severity = setEnumValue(BugSeverity.values(), this.severity, severity, "Severity", PROVIDED_INVALID);
    }


    @Override
    public String toString() {
        String assignee = (hasAssignee()) ? getAssignee().getName() : "Unassigned";
        return "ID#%d | Type: %s | Status: %s | Priority: %s | Severity: %s | Assignee: %s%nTitle: %s%n%n"
                .formatted(getID(), getRealName(), getStatus(), getPriority(), getSeverity(), assignee, getTitle());
    }

    @Override
    public void setAssignee(Member assignee) {
        this.assignee = assignee;
    }

    @Override
    public boolean hasAssignee() {
        return this.assignee != null;
    }

    @Override
    public Member getAssignee() {
        if (this.assignee == null) {
            throw new TaskHasNoAssigneeException(NOT_ASSIGNED.formatted(getRealName(), this.id));
        }
        return this.assignee;
    }

    @Override
    public int compareTo(Task o) {
        Bug compared = (Bug) o;
        int titleComparison = this.title.compareTo(compared.getTitle());
        if (titleComparison != 0) {
            return titleComparison;
        }
        int priorityComparison = this.priority.compareTo(compared.getPriority());
        if (priorityComparison != 0) {
            return priorityComparison;
        }
        return this.severity.compareTo(compared.getSeverity());
        }
    }

