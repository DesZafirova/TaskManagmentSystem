package commands.listingCommans;

import commands.BaseCommand;
import core.EngineImpl;
import core.contracts.ApplicationRepository;
import utils.ValidationHelpers;

import java.util.List;

import static utils.TutorialMessages.SHOW_TEAM_INFO_HELP_MSG;

public class ShowAllTeamMembers extends BaseCommand {
    private static final int VALID_ARGS_COUNT = 1;
    public ShowAllTeamMembers(ApplicationRepository app) {
        super(app);
    }


    @Override
    public void execute() {
        if(EngineImpl.tutorialMode){
            System.out.println(SHOW_TEAM_INFO_HELP_MSG);
        }
        List<String> params = extractParameters(EngineImpl.sc.nextLine());
        ValidationHelpers.validateArgumentsCount(params, VALID_ARGS_COUNT);
        String teamName = params.get(0);
        app.showAllTeamMembers(teamName);
    }
}
