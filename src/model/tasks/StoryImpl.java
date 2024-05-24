package model.tasks;

import exceptions.TaskHasNoAssigneeException;
import model.contracts.Member;
import model.contracts.tasks.Bug;
import model.contracts.tasks.Story;
import model.contracts.tasks.Task;
import model.enums.Priority;
import model.enums.Status;
import model.enums.StorySize;
import utils.ValidationHelpers;


public class StoryImpl extends BaseTask implements Story {

    private static final String PROVIDED_INVALID_FIELD = "Provided invalid %s";
    private static final String STORY_CREATED = "Story with ID:%d was created.";

    private StorySize size;
    private Priority priority;
    private Member assignee;

    protected StoryImpl(int id, String title, String description, Member assignee, String priority, String size) {
        super(id, title, description, Status.NOTDONE);
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
        String assignee = (hasAssignee()) ? getAssignee().getName() : "Unassigned";
        return "ID#%d | Type: %s | Status: %s | Priority: %s | Size: %s | Assignee: %s%nTitle: %s%n%n"
                .formatted(getID(), getRealName(), getStatus(), getPriority(), getSize(), assignee, getTitle());

    }
    @Override
    public int compareTo(Task o) {
        Story compared = (Story) o;
        int titleComparison = this.title.compareTo(compared.getTitle());
        if (titleComparison != 0) {
            return titleComparison;
        }
        int priorityComparison = this.priority.compareTo(compared.getPriority());
        if (priorityComparison != 0) {
            return priorityComparison;
        }
        return this.size.compareTo(compared.getSize());
    }
}
