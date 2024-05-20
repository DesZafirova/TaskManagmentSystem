package commands.listingCommans;

import commands.BaseCommand;
import core.EngineImpl;
import core.contracts.ApplicationRepository;
import model.contracts.Board;
import model.contracts.Team;
import utils.ValidationHelpers;

import java.util.List;
import java.util.Scanner;

import static utils.TutorialMessages.SHOW_BRD_ACTIVITIES_HELP_MSG;

public class ShowBoardActivities extends BaseCommand {
    private static final int VALID_ARGS_COUNT = 2;
    public ShowBoardActivities(ApplicationRepository app) {
        super(app);
    }


    @Override
    public void execute() {
        if(EngineImpl.tutorialMode){
            System.out.println(SHOW_BRD_ACTIVITIES_HELP_MSG);
        }
        List<String> params = extractParameters();
        ValidationHelpers.validateArgumentsCount(params, VALID_ARGS_COUNT);
        String teamName = params.get(0);
        String boardName = params.get(1);
        app.showBoardActivities(teamName, boardName);
    }

}
