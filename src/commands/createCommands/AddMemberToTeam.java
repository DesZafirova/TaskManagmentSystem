package commands.createCommands;

import commands.BaseCommand;
import core.EngineImpl;
import core.contracts.ApplicationRepository;
import utils.TutorialMessages;
import utils.ValidationHelpers;

import java.util.List;
import static utils.TutorialMessages.ADD_TEAM_MEMBER_HELP_MSG;

public class AddMemberToTeam extends BaseCommand {
    private static final int VALID_PARAM_COUNT = 2;

    public AddMemberToTeam(ApplicationRepository app) {
        super(app);
    }


    @Override
    public void execute() {
        if(EngineImpl.tutorialMode){
            System.out.println(ADD_TEAM_MEMBER_HELP_MSG);
        }
        List<String> params = extractParameters(EngineImpl.sc.nextLine());
        ValidationHelpers.validateArgumentsCount(params, VALID_PARAM_COUNT);
        String memberName = params.get(0);
        String teamName = params.get(1);
        app.addMemberToTeam(memberName, teamName);
    }


}
