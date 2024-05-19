package model.contracts.tasks;

import model.contracts.utils.Commentable;
import model.contracts.utils.Historical;
import model.contracts.utils.Identifiable;
import model.enums.Status;



public interface Task extends Identifiable, Commentable, Historical {

    String getTitle();

    String getDescription();

    Status getStatus();
    void setStatus(String status);
    void setTitle(String title);
    void setDescription(String description);




}
