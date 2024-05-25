package commands.listingCommans;

import commands.BaseCommand;
import core.EngineImpl;
import core.contracts.ApplicationRepository;
import core.contracts.Engine;
import utils.ValidationHelpers;

import java.util.Arrays;
import java.util.List;

import static utils.TutorialMessages.LIST_ALL_TASKS_HELP_MSG;
import static utils.TutorialMessages.LIST_FILTER_TASK_HELP_MSG;

public class ListAllTasks extends BaseCommand {
    private static final int VALID_PARAM_COUNT = 1;

    public ListAllTasks(ApplicationRepository app) {
        super(app);
    }

    @Override
    public void execute() {
        System.out.println(LIST_ALL_TASKS_HELP_MSG);
        String answer = EngineImpl.sc.nextLine();
        if (answer.equalsIgnoreCase("Y")) {

            System.out.println(LIST_FILTER_TASK_HELP_MSG);
            List<String> params = extractParameters(EngineImpl.sc.nextLine());
            ValidationHelpers.validateArgumentsCount(params, VALID_PARAM_COUNT);
            String queryParam = params.get(0);
            boolean strictSearch = false;
            if (params.size() > 1) {
                if (params.get(1).equalsIgnoreCase("Y")) {
                    strictSearch = true;
                }
            }
                app.listAllTasks(queryParam, strictSearch);
                return;
        }
        app.listAllTasks();
    }
}
