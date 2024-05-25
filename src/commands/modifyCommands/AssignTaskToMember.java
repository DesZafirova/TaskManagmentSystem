package commands.modifyCommands;

import commands.BaseCommand;
import core.EngineImpl;
import core.contracts.ApplicationRepository;
import model.contracts.tasks.AssignableTask;
import utils.ParsingHelpers;
import utils.ValidationHelpers;

import java.util.List;


import static utils.TutorialMessages.ASSIGN_TASK_TO_MEMBER_HELP_MSG;

public class AssignTaskToMember extends BaseCommand {
    private static final int VALID_PARAMETERS_COUNT = 2;

    public AssignTaskToMember(ApplicationRepository app) {
        super(app);
    }


    @Override
    public void execute() {
        if (EngineImpl.tutorialMode) {
            System.out.println(ASSIGN_TASK_TO_MEMBER_HELP_MSG);
        }
        List<String> params = extractParameters(EngineImpl.sc.nextLine());
        ValidationHelpers.validateArgumentsCount(params, VALID_PARAMETERS_COUNT);
        int taskId = ParsingHelpers.tryParseInt(params.get(0));
        try {
            AssignableTask task = (AssignableTask) app.findTaskById(taskId);
            String memberName = params.get(1);
            app.assignTaskToMember(task, memberName);
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Feedback is not assignable.");
        }
    }
}
