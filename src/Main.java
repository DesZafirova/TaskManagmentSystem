import core.ApplicationRepositoryImpl;
import core.EngineImpl;
import core.contracts.ApplicationRepository;
import core.contracts.Engine;
import model.BoardImpl;
import model.MemberImpl;
import model.contracts.Board;
import model.contracts.Member;
import model.contracts.Team;
import model.contracts.tasks.Bug;
import model.contracts.tasks.Feedback;
import model.contracts.tasks.Story;
import model.contracts.tasks.Task;
import model.tasks.BugImpl;
import model.tasks.FeedbackImpl;
import model.tasks.StoryImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {

//        Member member = new MemberImpl("Ivan1");
//
//
//        BugImpl bug = new BugImpl(1, "Some title of Bug", "Some long description",
//                member, List.of("Something new happens", "Log in the application"),
//                "High", "CRITICAL");
//        bug.setSeverity("Major");
//        bug.setStatus("Done");
//        bug.setStatus("Active");
//        bug.setStatus("Active");
//
//        bug.setPriority("Low");
//        System.out.println(bug);
//        System.out.println(bug.displayHistory());
//
//
//        Board board = new BoardImpl("Board");
//
//        Task task = new FeedbackImpl(1, "Something title", 5, "some text bla bla bls");
//        Task task2 = new FeedbackImpl(2, "Something title2", 5, "some text bla bla bls2");
//        board.addTask(task);
//
//        task.setStatus("Done");
//
//        Task story = new StoryImpl(3, "Story title", "some text bla bla bls", "High", "Large", member);
//
//        board.addTask(task2);
//        System.out.println(board);
//
//
//        System.out.println(story);
//
//
//        System.out.println(story.displayHistory());
//
//
//        System.out.println("---------------------------------------------------------");
//        System.out.println(bug.displayHistory());
//        System.out.println(task.displayHistory());

        ApplicationRepository app = new ApplicationRepositoryImpl();
//        app.createMember("Pesho");
//        app.createMember("Pesho1");
//        app.createMember("Pesho4");
//        app.createMember("Pesho6");
//        app.createMember("Pesho3");
//        app.showAllMembers();
//        Member pesho1 = app.findMemberByName("Pesho1");
//        System.out.println(pesho1);
//        app.createTeam("team1");
//        app.createTeam("team3");
//        app.createTeam("team2");
//        app.showAllTeams();
//        app.addMemberToTeam("Pesho3", "team1");
//        app.addMemberToTeam("Pesho6", "team2");
//
//        Team tm = app.findTeamByName("team1");
//        tm.showAllTeamMembers();
//
//        app.createNewBoardInTeam("Tasks", "team2");
//        app.createNewBoardInTeam("MonthTask", "team1");
//        app.createNewBoardInTeam("DailyTasks", "team2");
//        app.createNewBoardInTeam("UrgentTask", "team1");
//        app.showAllBoards();
//        Board someBoard = tm.getBoardByName("MonthTask");
//        app.showBoardActivities(someBoard);
//        Member pesho6 = app.findMemberByName("Pesho6");
//        tm.addMember(pesho6);
//        String[] feedback = {"someTitleblabla", "someDescription", "1"};
//        String[] story = {"someTitleblfds", "someDescription", "Pesho6", "High", "Medium"};
//        String[] bug = {"someTitlesfas", "someDescription", "Pesho3", "Something happens, close project",
//                "High", "Major"};
//        app.createNewTaskInBoard(someBoard, tm, "Feedback", feedback);
//        app.createNewTaskInBoard(someBoard, tm, "Story", story);
//        app.createNewTaskInBoard(someBoard, tm, "Bug", bug);
//        app.changeBugDetails(3, "Severity", "Major");
//        app.changeFeedbackDetails(1, "Status", "Done");
//        app.changeStoryDetails(2, "Size", "Small");
//
//        Story task = app.createTask("story", "TitleTitle", "Very long description","Low", "Medium");
//
//        Member ivan = app.createMember("Vankata");
//        Member gosho = app.createMember("Gosho");
//        Team ivanTeam = app.createTeam("Vankata");
//        Board board = app.createNewBoardInTeam("Vankata", "Vankata");
//        app.assignTaskToMember(task, ivan);
//        app.unAssignTaskToMember(task);
//        app.assignTaskToMember(task, gosho);
//        app.findByName("Vankata");
//        Team team = (Team) app.findByName("Vankata").get(0);
//        team.addMember(app.createMember("Stefan"));

        Engine engine = new EngineImpl();
        engine.run();

    }
}