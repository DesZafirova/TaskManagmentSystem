package model.tasks;

import exceptions.TaskHasNoAssigneeException;
import model.contracts.Member;
import model.contracts.tasks.Story;
import model.enums.Priority;
import model.enums.Status;
import model.enums.StorySize;
import utils.ValidationHelpers;


public class StoryImpl extends BaseTask implements Story {

    private static final String PROVIDED_INVALID_FIELD = "Provided invalid %s";
    private static final String STORY_CREATED = "Story with ID:%d was created.";

    private StorySize size;
    private Status status = Status.NOTDONE;
    private Priority priority;
    private Member assignee;

    protected StoryImpl(int id, String title, String description, Member assignee, String priority, String size) {
        super(id, title, description);
        this.setPriority(priority);
        this.setSize(size);
        this.assignee = assignee;
        addHistoryLog(STORY_CREATED.formatted(getID()));


    }

    public static Story createStory(int i, String title, String description, Member assignee, String priority, String size) {
        validateTitle(title);
        validateDescription(description);
        ValidationHelpers.validateEnum(priority, Priority.class);
        ValidationHelpers.validateEnum(size, StorySize.class);
        StoryImpl story = new StoryImpl(i, title, description, assignee, priority, size);
        System.out.printf(TASK_CREATED.formatted(story.getRealName(), story.getID()));
        return story;
    }

    public static Story createStory(int i, String title, String description, String priority, String size) {
        return createStory(i, title, description, null, priority, size);
    }

    @Override
    public void setStatus(String status) {
        this.status = setEnumValue(Status.getStoryStatusList(), this.status, status, "Status", PROVIDED_INVALID_FIELD);
    }

    private void setSize(String size) {
        this.size = setEnumValue(StorySize.values(), this.size, size, "Size", PROVIDED_INVALID_FIELD);

    }

    private void setPriority(String priority) {
        this.priority = setEnumValue(Priority.values(), this.priority, priority, "Priority", PROVIDED_INVALID_FIELD);

    }

    public void setAssignee(Member assignee) {
        this.assignee = assignee;
    }

    @Override
    public boolean hasAssignee() {
        return this.assignee != null;
    }

    @Override
    public Priority getPriority() {
        return this.priority;
    }

    @Override
    public StorySize getSize() {
        return this.size;
    }

    @Override
    public void changePriority(String priority) {
        setPriority(priority);
    }

    @Override
    public void changeSize(String size) {
        setSize(size);
    }

    @Override
    public Status getStatus() {
        return this.status;
    }

    @Override
    public Member getAssignee() {
        if (this.assignee == null) {
            throw new TaskHasNoAssigneeException(NOT_ASSIGNED.formatted(getRealName(), this.id));
        }
        return this.assignee;
    }

    @Override
    public String toString() {
        return """
                %s
                Assignee: %s
                Status: %s
                Size: %s
                Priority: %s
                """.formatted(
                super.toString(),
                this.assignee.getName(),
                this.status,
                this.size,
                this.priority
        );
    }
}
