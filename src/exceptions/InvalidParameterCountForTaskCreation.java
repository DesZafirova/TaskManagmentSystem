package exceptions;

public class InvalidParameterCountForTaskCreation extends RuntimeException{
     public static final String ERROR_MSG = "Provided parameters count is invalid for %s.%nPlease provide: %s.%n";
     public static final String FEEDBACK_PARAM_MSG = "Title, Description, Rating";
     public static final String STORY_PARAM_MSG = "Title, Description, Assignee(optional), Priority, Size";
     public static final String BUG_PARAM_MSG = "Title, Description, Assignee(optional), Priority, Severity, Steps to Reproduce";
//TODO: MIGHT WANT TO COLOR CODE THE PARAMS
    public InvalidParameterCountForTaskCreation(String taskType) {
        super(ERROR_MSG.formatted(taskType,getTypeParamsMsg(taskType)));
    }

    private static String getTypeParamsMsg(String taskType){
        return switch (taskType) {
            case "Bug" -> BUG_PARAM_MSG;
            case "Story" -> STORY_PARAM_MSG;
            case "Feedback" -> FEEDBACK_PARAM_MSG;
            default -> taskType + " is not supported";
        };
    }
}
