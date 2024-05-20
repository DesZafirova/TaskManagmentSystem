package utils;

import java.security.PublicKey;

import static utils.FormattingHelpers.RESET;
import static utils.FormattingHelpers.colorCode;

public class TutorialMessages {
    public static void printTaskHelpMsg(String taskType) {
        if(taskType.equalsIgnoreCase("Bug")){
            System.out.printf("Bug params: %s%s%s%n", colorCode, " Title(10-100) / Description(10-500) / Member assigned(optional) / Priority(High, Medium or Low) / Severity(Critical, Major or Minor) / Steps to reproduce(comma separated)", RESET);
        } else if (taskType.equalsIgnoreCase("Story")) {
            System.out.printf("Story params: %s%s%s%n", colorCode, "Title(10-100) / Description(10-500) / Member assigned(optional) / Priority(High, Medium or Low) / Size(Large, Medium or Small)", RESET);
        } else if (taskType.equalsIgnoreCase("Feedback")) {
            System.out.printf("Feedback params: %s%s%s%n", colorCode, "Title(10-100) / Description(10-500) / Rating(Number from 1 to 10)", RESET);
        }else {
            throw new IllegalArgumentException("%s is not a valid Task type.".formatted(taskType));
        }
    }
    public static final String ADD_TEAM_MEMBER_HELP_MSG = String.format(
            "In order to add a member to a team, please provide the following parameters separated by a /%nParenthesis after a parameter show the symbol limit of the field  %n%s%s%s%n",
    colorCode, "Existing Member Name / Existing Team Name", RESET
    );
    public static final String CREATE_BRD_HELP_MSG = String.format(
            "In order to create board in a team, please provide the following parameters separated by a /%nParenthesis after a parameter show the symbol limit of the field  %n%s%s%s%n",
            colorCode, "New Board Name(5-10) / Existing Team Name", RESET
    );
    public static final String CREATE_MEMBER_HELP_MSG = String.format(
            "In order to create member, please provide the following parameters separated by a /%nParenthesis after a parameter show the symbol limit of the field  %n%s%s%s%n",
    colorCode, "Name(5-15)", RESET
    );

    public static final String CREATE_TEAM_HELP_MSG = String.format(
            "In order to create team, please provide the following parameters separated by a /%nParenthesis after a parameter show the symbol limit of the field  %n%s%s%s%n",
            colorCode, "Name(5-15)", RESET
    );
    public static final String CREATE_TASK_IN_BRD_HELP_MSG = String.format("In order to create a task in a board, you need to provide: %n%s%s%s%n", colorCode,
            "Task type(Bug, Story or Feedback) / Existing Board name / Existing Team name", RESET);
    public static final String ADD_COMMENT_TO_TASK_HELP_MSG = String.format("In order to create a comment, you need to provide following parameters separated by / %n%s%s%s%n", colorCode,
            "Task id / Content(3-200) / Author", RESET);
    public static final String CHANGE_BUG_DETAILS_HELP_MSG = String.format("In order to change details of Bug, you need to provide the following parameters separated by / %n%s%s%s%n", colorCode,
            "Task id / Field name(Status{Active or Done}, Priority{High, Medium or Low} or Severity{Critical, Major or Minor}) / New Value ", RESET);
    public static final String CHANGE_STORY_DETAILS_HELP_MSG = String.format("In order to change details of Story, you need to provide the following parameters separated by / %n%s%s%s%n", colorCode,
            "Task id / Field name(Status{NotDone, InProgress, Done}, Priority{High, Medium or Low} or Size{Large, Medium or Small}) / New Value ", RESET);
    public static final String CHANGE_FEEDBACK_DETAILS_HELP_MSG = String.format("In order to change details of Feedback, you need to provide the following parameters separated by / %n%s%s%s%n", colorCode,
            "Task id / Field name(Status{New, Scheduled, Unscheduled or Done} or Rating(From 1 to 10)) / New Value ", RESET);
    public static final String ASSIGN_TASK_TO_MEMBER_HELP_MSG = String.format("In order to assign a task to a member, you need to provide the following parameters by / %n%s%s%s%n", colorCode,
            "Task id / Existing Member Name", RESET);
    public static final String UN_ASSIGN_TASK_HELP_MSG = String.format("In order to remove an assignee from a task, you need to provide the following parameters by / %n%s%s%s%n", colorCode,
            "Task id ", RESET);
    public static final String SHOW_TEAM_INFO_HELP_MSG = String.format("In order to show all requested information for team, you need to provide the following parameters:  %n%s%s%s%n", colorCode,
            "Existing Team Name", RESET);
    public static final String SHOW_BRD_ACTIVITIES_HELP_MSG = String.format("In order to show all board activities, you need to provide the following parameters separated by / %n%s%s%s%n", colorCode,
            "Existing Team Name /  Board Name", RESET);
    public static final String SHOW_MEMBER_ACTIVITIES_HELP_MSG =String.format("In order to show member activities, you need to provide the following parameters ", colorCode,
            "Existing Member Name", RESET);
}
