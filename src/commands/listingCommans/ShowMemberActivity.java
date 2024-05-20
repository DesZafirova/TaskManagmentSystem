package commands.listingCommans;

import commands.BaseCommand;
import core.EngineImpl;
import core.contracts.ApplicationRepository;
import utils.ValidationHelpers;

import java.util.List;

import static utils.TutorialMessages.SHOW_MEMBER_ACTIVITIES_HELP_MSG;

public class ShowMemberActivity extends BaseCommand {
    private static final int VALID_ARGS_COUNT = 1;
    public ShowMemberActivity(ApplicationRepository app) {
        super(app);
    }


    @Override
    public void execute() {
        if(EngineImpl.tutorialMode){
            System.out.println(SHOW_MEMBER_ACTIVITIES_HELP_MSG);
        }
        List<String> params = extractParameters();
        ValidationHelpers.validateArgumentsCount(params, VALID_ARGS_COUNT);
        String memberName = params.get(0);
        app.showMemberActivities(memberName);
    }
}
