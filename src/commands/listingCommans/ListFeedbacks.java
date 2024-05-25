package commands.listingCommans;

import commands.BaseCommand;
import core.EngineImpl;
import core.contracts.ApplicationRepository;
import utils.ValidationHelpers;

import java.util.List;

import static utils.TutorialMessages.LIST_FEEDBACK_TASKS_HELP_MSG;

public class ListFeedbacks extends BaseCommand {
    private static final int VALID_ARGS_COUNT = 1;
    public ListFeedbacks(ApplicationRepository app) {
        super(app);
    }

    @Override
    public void execute() {
        if(EngineImpl.tutorialMode){
            System.out.println(LIST_FEEDBACK_TASKS_HELP_MSG);
        }
        List<String> params = extractParameters(EngineImpl.sc.nextLine());
        ValidationHelpers.validateArgumentsCount(params, VALID_ARGS_COUNT);
        String status = (params.get(0).equalsIgnoreCase("n")) ? "none" : params.get(0);
        app.listAllFeedbacks(status);
    }
}
