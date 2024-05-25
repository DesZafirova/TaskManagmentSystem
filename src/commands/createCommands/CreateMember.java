package commands.createCommands;

import commands.BaseCommand;
import core.EngineImpl;
import core.contracts.ApplicationRepository;
import core.contracts.Engine;
import utils.ValidationHelpers;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static utils.FormattingHelpers.RESET;
import static utils.FormattingHelpers.colorCode;
import static utils.TutorialMessages.CREATE_MEMBER_HELP_MSG;


public class CreateMember extends BaseCommand {

    private static final int VALID_ARGUMENTS_COUNT = 1;

    public CreateMember(ApplicationRepository app) {
        super(app);
    }


    @Override
    public void execute() {
        if(EngineImpl.tutorialMode){
            System.out.println(CREATE_MEMBER_HELP_MSG);
        }
        List<String> params = extractParameters(EngineImpl.sc.nextLine());
        ValidationHelpers.validateArgumentsCount(params, VALID_ARGUMENTS_COUNT);
        String memberName = params.get(0);
        app.createMember(memberName);

    }


}
