package model;

import model.contracts.Member;
import model.contracts.tasks.Task;
import model.tasks.FeedbackImpl;
import utils.ValidationHelpers;

public class MemberImpl extends EntityImpl implements Member{
    private static final int NAME_MIN_LENGTH = 5;
    private static final int NAME_MAX_LENGTH = 15;
    private static final String NAME_ERROR_MESSAGE = String.format("Member name must be between %d and %d characters.",
            NAME_MIN_LENGTH, NAME_MAX_LENGTH);

    public MemberImpl(String name) {
        super(name);
    }


    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    protected void validateName(String name) {
        ValidationHelpers.validateStringLength(name, NAME_MIN_LENGTH, NAME_MAX_LENGTH, NAME_ERROR_MESSAGE);
    }

    @Override
    public String toString() {
        return """
                %s: %s
                Tasks: 
                %s
                =================
                History: 
                %s
                """.formatted(getRealName(),
                this.name,
                displayAllTasks(),
                displayHistory());
    }
}
