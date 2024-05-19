package model.contracts.tasks;

import utils.contracts.PrintableName;

public interface Feedback extends Task, PrintableName {

    int getRating();
    void changeRating(int rating);

}
