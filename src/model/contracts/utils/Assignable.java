package model.contracts.utils;

import model.contracts.Member;

public interface Assignable {
    public static final String NOT_ASSIGNED = "%s with ID#%d is not currently assigned to any member.%n";
     Member getAssignee();
    void setAssignee(Member assignee);
    public boolean hasAssignee();
}
