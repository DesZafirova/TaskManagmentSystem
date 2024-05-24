package utils;

import model.contracts.tasks.AssignableTask;
import model.contracts.tasks.Bug;
import model.contracts.tasks.Task;

import java.util.List;

public class FilterHelpers {
    public static <T extends Task> List<T> filterByStatus(String status, List<T> tasks) {
        if (!status.equalsIgnoreCase("none")) {
            return tasks.stream().filter(b -> b.getStatus().name().equalsIgnoreCase(status)).toList();
        }
        return tasks;
    }

    public static <T extends AssignableTask> List<T> filterByAssignee(String assigneeName, List<T> tasks) {
        List<T> filterTasks = null;
        if (assigneeName.equalsIgnoreCase("Unassigned")) {
            filterTasks = tasks.stream().filter(b -> !b.hasAssignee()).toList();
        } else if (!assigneeName.equalsIgnoreCase("none")) {
            filterTasks = tasks.stream().filter(b ->
            {
                if (b.hasAssignee()) {
                    return b.getAssignee().getName().equalsIgnoreCase(assigneeName);
                }
                return false;
            }).toList();
        }
        if (filterTasks == null) return tasks;
        return filterTasks;
    }
}
