package core;

import exceptions.*;
import model.MemberImpl;
import model.contracts.Board;
import model.contracts.Member;
import model.contracts.Team;
import model.contracts.tasks.*;
import model.enums.BugSeverity;
import model.enums.Priority;
import model.enums.Status;
import model.enums.StorySize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationRepositoryImplTest {
    public static final String VALID_TITLE = "some title";
    public static final String VALID_DESCRIPTION = "some description";
    private ApplicationRepositoryImpl app;
    private ApplicationRepositoryImpl app2;
    Team secondTestTeam;
    Team testTeam;
    Board board;
    Board board2;
    private Member member;
    String[] STORY_PARAMS = {VALID_TITLE, VALID_DESCRIPTION, "Medium", "Medium"};
    String[] BUG_PARAMS = {VALID_TITLE, VALID_DESCRIPTION, "High", "Critical", "step1, step2, step3"};
    String[] FEEDBACK_PARAMS = {VALID_TITLE, VALID_DESCRIPTION, "6"};
    Bug bug;

    @BeforeEach
    void setUp() {
        app = new ApplicationRepositoryImpl();
        app2 = new ApplicationRepositoryImpl();
        testTeam = app.createTeam("Test Team");
        secondTestTeam = app.createTeam("secondTestTeam");
        member = app.createMember("member");
        app.addMemberToTeam(member.getName(), testTeam.getName());
        board = app.createNewBoardInTeam("TestBoard", testTeam.getName());
        board2 = app.createNewBoardInTeam("TestBoard2", testTeam.getName());
        bug = (Bug) app.createNewTaskInBoard("bug", board.getName(), testTeam.getName(), BUG_PARAMS);
    }

    @Test
    void createMember() {
        assertNotNull(app.findMemberByName("member"));
        assertThrows(EntityAlreadyExistException.class, () -> app.createMember("member"));
        assertThrows(IllegalArgumentException.class, () -> app.createMember("er"));
    }

    @Test
    void showAllMembers() {
        String[] result = app.showAllMembers().split("\n")[1].split(", ");
        assertEquals(1, result.length);
        assertEquals(result[0].trim(), member.getName());
        ApplicationRepositoryImpl testApp = new ApplicationRepositoryImpl();
        assertEquals("No Member register in the Database.%n".formatted(), testApp.showAllMembers());
    }

    @Test
    void showMemberActivities() {
        String result = app.showMemberActivities(member.getName());
        assertNotNull(result);
        assertEquals("No activities recorded.", result);
    }

    @Test
    void createTeam() {
        assertNotNull(testTeam);

        assertNotNull(secondTestTeam);

    }

    @Test
    void showAllTeams() {
        assertNotNull(app.showAllTeams());
        assertEquals(2, app.showAllTeams().split("\n")[1].split(", ").length);
        assertEquals("No Teams register in the Database.%n".formatted(), app2.showAllTeams());
    }

    @Test
    void showTeamBoards() {
        assertNotNull(app.showTeamBoards(testTeam.getName()));
        assertEquals(2, app.showTeamBoards(testTeam.getName()).split(", ").length);
        assertEquals("No Boards are created in team secondTestTeam.%n".formatted(), app.showTeamBoards(secondTestTeam.getName()));
    }

    @Test
    void showTeamActivities() {
        assertNotNull(app.showTeamActivities(testTeam.getName()));
        assertEquals(6, app.showTeamActivities(testTeam.getName()).split("\n").length);
        assertEquals("No activities recorded.", app.showTeamActivities(secondTestTeam.getName()).split("\n")[3]);
    }

    @Test
    void addMemberToTeam() {
        assertThrows(AlreadyMemberOfTeam.class, () -> app.addMemberToTeam(member.getName(), testTeam.getName()));
        assertEquals(1, app.showAllTeamMembers(testTeam.getName()).split("\n")[1].split(", ").length);
        Member testMember = app.createMember("Steve");
        app.addMemberToTeam(testMember.getName(), testTeam.getName());
        assertEquals(2, app.showAllMembers().split("\n")[1].split(", ").length);
    }

    @Test
    void showAllTeamMembers() {
        assertEquals(1, app.showAllTeamMembers(testTeam.getName()).split("\n")[1].split(", ").length);
        assertEquals(1, app.showAllTeamMembers(secondTestTeam.getName()).split("\n").length);
    }

    @Test
    void createNewBoardInTeam() {
        assertNotNull(board);
        Board testBoard2 = app.createNewBoardInTeam("testboard3", testTeam.getName());
        assertNotNull(testBoard2);
    }

    @Test
    void showAllBoards() {
        assertEquals(3, app.showAllBoards().trim().split("\n").length);
        assertEquals("Board: TestBoard [Team: Test Team]", app.showAllBoards().trim().split("\n")[1]);
        assertEquals("Board: TestBoard2 [Team: Test Team]", app.showAllBoards().trim().split("\n")[2]);
    }

    @Test
    void showBoardActivities() {
        assertNotNull(app.showBoardActivities(testTeam.getName(), board.getName()));
        assertThrows(EntityWithNameDoesNotExist.class, () -> app.showBoardActivities("someteam", board.getName()));
        assertThrows(BoardNotFoundInTeam.class, () -> app.showBoardActivities(testTeam.getName(), "not a board"));
        assertEquals("Board TestBoard2 is empty.", app.showBoardActivities(testTeam.getName(), board2.getName()).split("\n")[0].trim());
    }

    @Test
    void createNewTaskInBoard() {
              Task task = app.createNewTaskInBoard(
                "Feedback",
                board.getName(),
                testTeam.getName(),
                FEEDBACK_PARAMS
        );
        assertNotNull(task);
        assertThrows(IllegalArgumentException.class, () -> board.addTask(task));
    }

    @Test
    void changeBugDetails() {
        assertEquals(Status.ACTIVE, bug.getStatus());
        app.changeBugDetails(bug.getID(), "status", "done");
        app.changeBugDetails(bug.getID(), "priority", "low");
        app.changeBugDetails(bug.getID(), "severity", "minor");
        assertEquals(Status.DONE, bug.getStatus());
        assertEquals(Priority.LOW, bug.getPriority());
        assertEquals(BugSeverity.MINOR, bug.getSeverity());
    }

    @Test
    void changeFeedbackDetails() {

        Feedback feedback = (Feedback) app.createNewTaskInBoard("feedback", board.getName(), testTeam.getName(), FEEDBACK_PARAMS);
        assertEquals(Status.NEW, feedback.getStatus());
        app.changeFeedbackDetails(feedback.getID(), "status", "done");
        app.changeFeedbackDetails(feedback.getID(), "rating", "9");
        assertEquals(Status.DONE, feedback.getStatus());
        assertEquals(9, feedback.getRating());
    }

    @Test
    void changeStoryDetails() {
        Story story = (Story) app.createNewTaskInBoard("story", board.getName(), testTeam.getName(), STORY_PARAMS);
        assertEquals(Status.NOTDONE, story.getStatus());
        app.changeStoryDetails(story.getID(), "status", "done");
        app.changeStoryDetails(story.getID(), "priority", "low");
        app.changeStoryDetails(story.getID(), "size", "large");

        assertEquals(Status.DONE, story.getStatus());
        assertEquals(Priority.LOW, story.getPriority());
        assertEquals(StorySize.LARGE, story.getSize());
    }

    @Test
    void assignTaskToMember() {

        Feedback feedback = (Feedback) app.createNewTaskInBoard("feedback", board.getName(), testTeam.getName(), FEEDBACK_PARAMS);
        assertFalse(bug.hasAssignee());
        app.assignTaskToMember(bug, member.getName());
        assertTrue(bug.hasAssignee());
        assertThrows(ClassCastException.class, () -> app.assignTaskToMember((AssignableTask) feedback, member.getName()));
    }

    @Test
    void unAssignTaskToMember() {
        app.assignTaskToMember(bug, member.getName());
        assertTrue(bug.hasAssignee());
        app.unAssignTaskToMember(bug);
        assertFalse(bug.hasAssignee());
    }

    @Test
    void addCommentToTask() {
        app.addCommentToTask(bug.getID(), "Random comment", "Jane Doe");
        assertNotNull(bug.getComments());
        assertEquals(1, bug.getComments().size());
        assertEquals("Jane Doe", bug.getComments().get(0).getAuthor());
        assertEquals("Random comment", bug.getComments().get(0).getContent());
        assertThrows(IllegalArgumentException.class, () -> app.addCommentToTask(bug.getID(), "", "Jane Doe"));


    }

    @Test
    void findTaskById() {
        Task task = app.findTaskById(bug.getID());
        assertSame(bug, task);
        assertThrows(TaskWithIdDoesNotExist.class, () -> app.findTaskById(3213));
    }

    @Test
    void findByName() {
        assertNotNull(app.findByName(testTeam.getName()));
        assertEquals(1, app.findByName(testTeam.getName()).size());
    }

    @Test
    void findMemberByName() {
        assertSame(member, app.findMemberByName(member.getName()));
        assertThrows(EntityWithNameDoesNotExist.class, () -> app.findMemberByName("randominp"));
    }

    @Test
    void findTeamByName() {
        assertSame(testTeam, app.findTeamByName(testTeam.getName()));
        assertThrows(EntityWithNameDoesNotExist.class, () -> app.findTeamByName("randominp"));
    }

    @Test
    void findBoardByName() {
        assertSame(board, app.findBoardByName(board.getName(), testTeam.getName()));
        assertThrows(EntityWithNameDoesNotExist.class, () -> app.findBoardByName(board.getName(), "not a team"));
        assertThrows(NoBoardInTeamException.class, () -> app.findBoardByName("non a board", testTeam.getName()));
    }

    @Test
    void createTask() {
        Bug testBug = (Bug) app.createTask("bug", BUG_PARAMS);
        assertNotNull(testBug);

        Feedback testFeedback = (Feedback) app.createTask("feedback", FEEDBACK_PARAMS);
        assertNotNull(testFeedback);
    }

    @Test
    void listAllTasks() {
        assertNotNull(app.listAllTasks());
        assertEquals(1, app.listAllTasks().size());
        assertEquals(bug, app.listAllTasks().get(0));
    }

    @Test
    void testListAllTasks() {
        assertNotNull(app.listAllTasks("title", false));
        assertEquals(1, app.listAllTasks("title", false).size());
        assertEquals(0, app.listAllTasks("title", true).size());
        assertEquals(1, app.listAllTasks("some title", true).size());
        assertEquals(bug, app.listAllTasks("title", false).get(0));
    }

    @Test
    void printAllTasks() {
        assertNotNull(app.printAllTasks(board.getTasks()));
    }

    @Test
    void listAllStories() {
        assertNotNull(app.listAllStories("none", "none"));
        assertEquals(0, app.listAllStories("none", "none").size());
        app.createNewTaskInBoard("story", board.getName(), testTeam.getName(), STORY_PARAMS);
        assertEquals(1, app.listAllStories("none", "none").size());
    }

    @Test
    void listAllFeedbacks() {
        assertNotNull(app.listAllFeedbacks("none"));
        assertEquals(0, app.listAllFeedbacks("none").size());
        app.createNewTaskInBoard("feedback", board.getName(), testTeam.getName(), FEEDBACK_PARAMS);
        assertEquals(1, app.listAllFeedbacks("none").size());
    }

    @Test
    void listAllAssignableTasks() {
        assertNotNull(app.listAllAssignableTasks("none", "none"));
        app.assignTaskToMember(bug, member.getName());
        assertEquals(1,app.listAllAssignableTasks("none", "none").size());
        assertSame(bug,app.listAllAssignableTasks("none", "none").get(0));
    }

    @Test
    void listAllBugs() {
        assertNotNull(app.listAllBugs("none", "none"));
        assertEquals(1, app.listAllBugs("none", "none").size());
        assertEquals(bug, app.listAllBugs("none", "none").get(0));
    }
}