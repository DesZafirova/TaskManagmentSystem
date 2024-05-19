package model;

import java.util.ArrayList;
import java.util.List;

public class ActivityHistory {
    private final List<ActivityLog> history;

    public ActivityHistory() {
        this.history = new ArrayList<>();
    }
    public void addEventToHistory(String description){
        ActivityLog log = new ActivityLog(description);
        history.add(log);
    }
    public void removeEventFromHistory(ActivityLog event){
        history.remove(event);
    }

    public String listHistory(){
        StringBuilder sb = new StringBuilder();
        for (ActivityLog log : history) {
            sb.append(log.viewInfo());
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    public List<ActivityLog> getHistory() {
        return List.copyOf(history);
    }
}
