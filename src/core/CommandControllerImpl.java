package core;

import commands.EngineCommands.ChangeColorCommand;
import commands.EngineCommands.ListCommandsCommand;
import commands.EngineCommands.ToggleTutorialCommand;
import commands.contracts.Command;
import commands.createCommands.*;
import commands.enums.CommandType;
import commands.listingCommans.*;
import commands.modifyCommands.*;
import core.contracts.ApplicationRepository;
import core.contracts.CommandController;
import exceptions.InvalidCommandTypeException;
import utils.ParsingHelpers;

public class CommandControllerImpl implements CommandController {


    @Override
    public Command createCommandFromCommandName(String commandTypeAsString, ApplicationRepository applicationRepository) {
        CommandType commandType = ParsingHelpers.tryParseEnum(commandTypeAsString, CommandType.class);
        switch (commandType){
            case LC: return new ListCommandsCommand(applicationRepository);
            case SETCOLOR: return new ChangeColorCommand(applicationRepository);
            case TUTORIAL: return new ToggleTutorialCommand(applicationRepository);
            case CREATEMEMBER: return new CreateMember(applicationRepository);
            case SHOWALLMEMBERS: return new ShowAllMembers(applicationRepository);
            case SHOWMEMBERACTIVITY: return new ShowMemberActivity(applicationRepository);
            case CREATETEAM: return new CreateTeam(applicationRepository);
            case SHOWTEAMS: return new ShowAllTeams(applicationRepository);
            case SHOWTEAMACTIVITY: return new ShowTeamActivity(applicationRepository);
            case ADDMEMBERTOTEAM: return new AddMemberToTeam(applicationRepository);
            case SHOWTEAMMEMBERS: return new ShowAllTeamMembers(applicationRepository);
            case CREATEBOARDINTEAM: return new CreateBoardInTeam(applicationRepository);
            case SHOWALLTEAMBOARDS: return new ShowAllBoards(applicationRepository);
            case SHOWBOARDACTIVITY: return new ShowBoardActivities(applicationRepository);
            case CREATETASKINBOARD: return new CreateTaskInBoard(applicationRepository);
            case CHANGEBUGDETAILS: return new ChangeBugDetails(applicationRepository);
            case CHANGESTORYDETAILS: return new ChangeStoryDetails(applicationRepository);
            case CHANGEFEEDBACKDETAILS: return new ChangeFeedbackDetails(applicationRepository);
            case ASSIGNTASK: return new AssignTaskToMember(applicationRepository);
            case UNAASIGNTASK: return new UnAssignTaskToMember(applicationRepository);
            case ADDTASKCOMMENT: return new AddTaskComment(applicationRepository);
            case LISTALLTASKS: return new ListAllTasks(applicationRepository);
            case LISTBUGS: return new ListBugs(applicationRepository);
            case LISTSTORIES: return new ListStories(applicationRepository);
            default: throw new InvalidCommandTypeException("%s is not supported.".formatted(commandType));


        }
    }
}
