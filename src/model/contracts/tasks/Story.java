package model.contracts.tasks;

import model.contracts.utils.Assignable;
import model.enums.Priority;
import model.enums.StorySize;
import utils.contracts.PrintableName;


public interface Story extends  AssignableTask, PrintableName {

    Priority getPriority();

    StorySize getSize();
    void changePriority(String priority);
    void changeSize(String size);

}
