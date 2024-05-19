package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ActivityLog {
    private static final String LOCAL_DATE_TIME_PATTERN = "dd-MMMM-yyyy HH:mm:ss";

    private static final String DESCRIPTION_CANNOT_BE_EMPTY = "Description cannot be empty";
    private final String description;
    private final LocalDateTime timestamp;

    public ActivityLog(String description) {
        if (description.isEmpty()) {
            throw new IllegalArgumentException(DESCRIPTION_CANNOT_BE_EMPTY);
        }
        this.description = description;
        this.timestamp = LocalDateTime.now();
    }

    public ActivityLog() {
        throw new IllegalArgumentException(DESCRIPTION_CANNOT_BE_EMPTY);
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String viewInfo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(LOCAL_DATE_TIME_PATTERN);
        return String.format("[%s] %s", timestamp.format(formatter), description);
    }
}
