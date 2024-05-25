package commands.createCommands;

import commands.BaseCommand;
import core.EngineImpl;
import core.contracts.ApplicationRepository;
import utils.ValidationHelpers;

import java.util.List;
import static utils.TutorialMessages.CREATE_TEAM_HELP_MSG;

public class CreateTeam extends BaseCommand {
    private static final int VALID_ARGUMENTS_COUNT = 1;

    public CreateTeam(ApplicationRepository app) {
        super(app);
    }


    @Override
    public void execute() {
        if(EngineImpl.tutorialMode){
            System.out.println(CREATE_TEAM_HELP_MSG);
        }
        List<String> params = extractParameters(EngineImpl.sc.nextLine());
        ValidationHelpers.validateArgumentsCount(params, VALID_ARGUMENTS_COUNT);
        String teamName = params.get(0);
        app.createTeam(teamName);
    }

   }
