package commands.modifyCommands;

import commands.BaseCommand;
import core.EngineImpl;
import core.contracts.ApplicationRepository;
import utils.ParsingHelpers;
import utils.ValidationHelpers;

import java.util.List;
import java.util.Scanner;

import static utils.TutorialMessages.CHANGE_STORY_DETAILS_HELP_MSG;

public class ChangeStoryDetails extends BaseCommand {
    private static final int VALID_PARAMETERS_COUNT = 3;
    public ChangeStoryDetails(ApplicationRepository app) {
        super(app);
    }


    @Override
    public void execute() {
        if(EngineImpl.tutorialMode){
            System.out.println(CHANGE_STORY_DETAILS_HELP_MSG);
        }
        List<String> params = extractParameters();
        ValidationHelpers.validateArgumentsCount(params, VALID_PARAMETERS_COUNT);
        int taskId = ParsingHelpers.tryParseInt(params.get(0));
        String fieldName = params.get(1);
        String value = params.get(2);
        app.changeStoryDetails(taskId, fieldName, value);
    }

}
