package model.contracts.utils;

import model.ActivityLog;

import java.util.List;

public interface Historical {

    List<ActivityLog> getHistory();

    void addHistoryLog(String event);
    String displayHistory();
}
