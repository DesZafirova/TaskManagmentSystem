package model.contracts.tasks;

import model.contracts.utils.Commentable;
import model.contracts.utils.Historical;
import model.contracts.utils.Identifiable;
import model.enums.Status;
import utils.contracts.PrintableName;

import java.util.Comparator;


public interface Task extends Identifiable, Commentable, Historical, PrintableName, Comparable<Task> {

    String getTitle();

    String getDescription();

    Status getStatus();
    void setStatus(String status);
    void setTitle(String title);
    void setDescription(String description);




}
