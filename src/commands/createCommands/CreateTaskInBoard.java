package commands.createCommands;

import commands.BaseCommand;
import core.EngineImpl;
import core.contracts.ApplicationRepository;
import exceptions.InvalidParameterCountForTaskCreation;
import model.contracts.Team;
import utils.ValidationHelpers;

import java.util.Arrays;
import java.util.List;

import static utils.TutorialMessages.CREATE_TASK_IN_BRD_HELP_MSG;
import static utils.TutorialMessages.printTaskHelpMsg;

public class CreateTaskInBoard extends BaseCommand {
    private static final int VALID_ARGUMENT_COUNT = 3;
    public CreateTaskInBoard(ApplicationRepository app) {
        super(app);
    }


    @Override
    public void execute() {
        if(EngineImpl.tutorialMode){
            System.out.println(CREATE_TASK_IN_BRD_HELP_MSG);
        }
        List<String> params = extractParameters();
        ValidationHelpers.validateArgumentsCount(params, VALID_ARGUMENT_COUNT);
        String taskType = params.get(0);
        String boardName = params.get(1);
        String teamName = params.get(2);
        app.findTeamByName(teamName);
        app.findBoardByName(boardName, teamName);
        printTaskHelpMsg(taskType);
        String [] taskParams = EngineImpl.sc.nextLine().split(" / ");

//        String[] taskParams = params.subList(3, params.size() - 1).toArray(String[]::new);
        System.out.println(Arrays.toString(taskParams));

        app.createNewTaskInBoard(taskType, boardName, teamName, taskParams);
    }


}
