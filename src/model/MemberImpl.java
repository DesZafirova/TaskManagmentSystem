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
    //    public static void main(String[] args) {
//        Member member = new MemberImpl("Gosho");
//        Task task = new FeedbackImpl(1, "Something title", 5, "some text bla bla bls", "NEW", member );
//        Task task2 = new FeedbackImpl(1, "Something title2", 5, "some text bla bla bls2", "NEW", member );
//
//        member.addTask(task);
//        member.addTask(task2);
//        System.out.println(member.displayAllTasks());
//    }
}
