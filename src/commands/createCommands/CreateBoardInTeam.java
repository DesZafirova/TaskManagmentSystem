package commands.createCommands;

import commands.BaseCommand;
import core.EngineImpl;
import core.contracts.ApplicationRepository;
import utils.ValidationHelpers;

import java.util.List;
import static utils.TutorialMessages.CREATE_BRD_HELP_MSG;


public class CreateBoardInTeam extends BaseCommand {
    private static final int VALID_PARAM_COUNT = 2;

    public CreateBoardInTeam(ApplicationRepository app) {
        super(app);
    }


    @Override
    public void execute() {
        if(EngineImpl.tutorialMode){
            System.out.println(CREATE_BRD_HELP_MSG);
        }
        List<String> params = extractParameters(EngineImpl.sc.nextLine());
        ValidationHelpers.validateArgumentsCount(params, VALID_PARAM_COUNT);
        String boardName = params.get(0);
        String teamName = params.get(1);
        app.createNewBoardInTeam(boardName, teamName);

    }

}
