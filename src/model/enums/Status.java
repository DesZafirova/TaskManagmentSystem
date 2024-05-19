package model.enums;

import java.util.ArrayList;
import java.util.List;

public enum Status {
    ACTIVE,
    DONE,
    NOTDONE,
    INPROGRESS,
    NEW, UNSCHEDULED, SCHEDULED;
    public static Status[] getBugStatusList(){
        Status[] bugStatuses = {ACTIVE, DONE};

        return bugStatuses;
    }
    public static Status[] getStoryStatusList(){
        Status[] storyStatuses = {NOTDONE,INPROGRESS, DONE};
        return storyStatuses;
    }
    public static Status[] getFeedbackStatusList(){
        Status[] feedbackStatus = {NEW, UNSCHEDULED, SCHEDULED, DONE};
        return feedbackStatus;
    }


}
