package commands.listingCommans;

import commands.BaseCommand;
import core.EngineImpl;
import core.contracts.ApplicationRepository;
import utils.ValidationHelpers;

import java.util.List;
import static utils.TutorialMessages.SHOW_TEAM_INFO_HELP_MSG;

public class ShowAllTeamBoards extends BaseCommand {
    private static final int VALID_ARGS_COUNT = 1;
    protected ShowAllTeamBoards(ApplicationRepository app) {
        super(app);
    }

    @Override
    public void execute() {
        if(EngineImpl.tutorialMode){
            System.out.println(SHOW_TEAM_INFO_HELP_MSG);
        }
        List<String> params = extractParameters();
        ValidationHelpers.validateArgumentsCount(params, VALID_ARGS_COUNT);
        String teamName = params.get(0);
        app.showTeamBoards(teamName);
    }
}
