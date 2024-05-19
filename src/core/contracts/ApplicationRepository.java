package core.contracts;

import model.contracts.Board;
import model.contracts.Comment;
import model.contracts.Member;
import model.contracts.Team;
import model.contracts.tasks.AssignableTask;
import model.contracts.tasks.Task;
import model.contracts.utils.Assignable;
import model.contracts.utils.Identifiable;
import model.contracts.utils.Nameble;
import model.tasks.BaseTask;

import java.util.List;

public interface ApplicationRepository {

    Member createMember(String name);
    String showAllMembers();
    String showMemberActivities(String name);
    Team createTeam(String name);
    String showAllTeams();
    String showTeamActivities(String name);
    String addMemberToTeam(String memberName, String teamName);
    String showAllTeamMembers(String name);
    Board createNewBoardInTeam(String boardName, String teamName);
    String showAllBoards();
    String showBoardActivities(Board board);

    Task createNewTaskInBoard(String taskType, String boardName, String teamName, String[] params);

    String changeBugDetails(int id, String fieldName, String value);
    String changeFeedbackDetails(int id,String fieldName, String value);
    String changeStoryDetails(int id,String fieldName, String value);
    <T extends AssignableTask> String assignTaskToMember(T task, String memberName);
    <T extends AssignableTask> String unAssignTaskToMember(T task);
    String addCommentToTask(int id, String content, String author);
    Task findTaskById(int id);
    List<Nameble> findByName(String name);
    //TODO tobe continue......
    Member findMemberByName(String name);

    Team findTeamByName(String name);
    Board findBoardByName(String boardName, String teamName);
    <T extends Task> T createTask(String taskTpe, String... params);



}
