package model.tasks;

import model.ActivityHistory;
import model.ActivityLog;
import model.contracts.Comment;
import model.contracts.tasks.Task;
import model.enums.Status;
import utils.contracts.PrintableName;
import utils.ValidationHelpers;


import java.util.ArrayList;
import java.util.List;

public abstract class BaseTask implements Task, PrintableName {

    private static final int TITLE_MIN_LENGTH = 10;
    private static final int TITLE_MAX_LENGTH = 100;
    private static final String TITLE_ERROR_MESSAGE = String.format("Title length must be between %d and %d characters.",
            TITLE_MIN_LENGTH, TITLE_MAX_LENGTH);
    private static final int DESCRIPTION_MIN_LENGTH = 10;
    private static final int DESCRIPTION_MAX_LENGTH = 500;
    private static final String DESCRIPTION_ERROR_MESSAGE = String.format("Description length must be between %d and %d characters.",
            DESCRIPTION_MIN_LENGTH, DESCRIPTION_MAX_LENGTH);
    public static final String TASK_CREATED = "%s with ID:%d was created.%n";

    protected final int id;
    protected String title;
    private String description;
    protected Status status;
    protected List<Comment> comments;
    private final ActivityHistory history;

    public BaseTask(int id, String title, String description, Status status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.comments = new ArrayList<>();
        this.history = new ActivityHistory();

    }


    @Override
    public List<Comment> getComments() {
        return List.copyOf(comments);
    }

    @Override
    public List<ActivityLog> getHistory() {
        return history.getHistory();
    }

    @Override
    public void addHistoryLog(String event) {
        history.addEventToHistory(event);
    }

    @Override
    public String displayHistory() {
        return history.listHistory();
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Status getStatus() {
        return this.status;
    }

    @Override
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    @Override
    public void setTitle(String title) {
        ValidationHelpers.validateStringLength(title, TITLE_MIN_LENGTH, TITLE_MAX_LENGTH, TITLE_ERROR_MESSAGE);
        this.title = title;
    }

    protected static void validateTitle(String title) {
        ValidationHelpers.validateStringLength(title, TITLE_MIN_LENGTH, TITLE_MAX_LENGTH, TITLE_ERROR_MESSAGE);
    }

    @Override
    public void setDescription(String description) {
        ValidationHelpers.validateStringLength(description, DESCRIPTION_MIN_LENGTH, DESCRIPTION_MAX_LENGTH, DESCRIPTION_ERROR_MESSAGE);
        this.description = description;
    }

    protected static void validateDescription(String description) {
        ValidationHelpers.validateStringLength(description, DESCRIPTION_MIN_LENGTH, DESCRIPTION_MAX_LENGTH, DESCRIPTION_ERROR_MESSAGE);

    }


    public abstract void setStatus(String status);


    //TODO if something breaks and we don't know what it is probably this!!!!
    protected  <T extends Enum<T>, E> T setEnumValue(T[] enumConstants, E currentValue, String newValue, String fieldName, String message) {
        boolean isCreated = currentValue == null;
        for (T value : enumConstants) {
            if (value.toString().equalsIgnoreCase(newValue)) {
                if (!isCreated) {
                    if (!currentValue.toString().equalsIgnoreCase(value.toString())) {
                        addHistoryLog(String.format("Changed %s from %s to %s", fieldName, currentValue, value));
                    }
                }
                return value;
            }
        }
        throw new IllegalArgumentException(message.formatted(fieldName));
    }

    @Override
    public abstract String toString();
}
