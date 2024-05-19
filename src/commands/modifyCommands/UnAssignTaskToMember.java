package commands.modifyCommands;

import commands.BaseCommand;
import core.EngineImpl;
import core.contracts.ApplicationRepository;
import model.contracts.tasks.AssignableTask;
import utils.ParsingHelpers;
import utils.ValidationHelpers;

import java.util.List;
import static utils.TutorialMessages.UN_ASSIGN_TASK_HELP_MSG;

public class UnAssignTaskToMember extends BaseCommand {
    private static final int VALID_PARAMETERS_COUNT = 1;
    public UnAssignTaskToMember(ApplicationRepository app) {
        super(app);
    }


    @Override
    public void execute() {
        if (EngineImpl.tutorialMode) {
            System.out.println(UN_ASSIGN_TASK_HELP_MSG);
        }
        List<String> params = extractParameters();
        ValidationHelpers.validateArgumentsCount(params, VALID_PARAMETERS_COUNT);
        int taskId = ParsingHelpers.tryParseInt(params.get(0));
        try {
            AssignableTask task = (AssignableTask) app.findTaskById(taskId);
            app.unAssignTaskToMember(task);
        } catch (Exception e) {
            throw new IllegalArgumentException("Task of type Feedback has no assignee.");
        }
    }
}
