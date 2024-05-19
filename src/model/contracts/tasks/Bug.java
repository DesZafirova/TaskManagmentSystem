package model.contracts.tasks;

import model.contracts.utils.Assignable;
import model.enums.Priority;
import model.enums.BugSeverity;
import utils.contracts.PrintableName;

import java.util.List;

public interface Bug extends PrintableName, AssignableTask {

    Priority getPriority();

    BugSeverity getSeverity();
    List<String> getStepsToReproduce();
    void changeSeverity(String value);
    void changePriority(String value);
    //addStep
}
