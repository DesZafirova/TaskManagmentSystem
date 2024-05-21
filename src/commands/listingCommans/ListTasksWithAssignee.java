package commands.listingCommans;

import commands.BaseCommand;
import core.EngineImpl;
import core.contracts.ApplicationRepository;
import utils.ValidationHelpers;

import java.util.List;

import static utils.TutorialMessages.LIST_TASKS_HELP_MSG;

public class ListTasksWithAssignee extends BaseCommand {
    private static final int VALID_ARGS_COUNT = 2;

    public ListTasksWithAssignee(ApplicationRepository app) {
        super(app);
    }

    @Override
    public void execute() {
        if (EngineImpl.tutorialMode) {
            System.out.println(LIST_TASKS_HELP_MSG);
        }
        List<String> params = extractParameters();
        ValidationHelpers.validateArgumentsCount(params, VALID_ARGS_COUNT);
        String status = (params.get(0).equalsIgnoreCase("n")) ? "none" : params.get(0);
        String assigneeName = (params.get(1).equalsIgnoreCase("n")) ? "none" : params.get(1);
        app.listAllAssignableTasks(status, assigneeName);
    }
}
