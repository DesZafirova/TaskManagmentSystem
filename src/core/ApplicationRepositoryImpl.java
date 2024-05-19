package core;

import core.contracts.ApplicationRepository;
import exceptions.*;
import model.CommentImpl;
import model.MemberImpl;
import model.TeamImpl;
import model.contracts.Board;
import model.contracts.Comment;
import model.contracts.Member;
import model.contracts.Team;
import model.contracts.tasks.*;
import model.contracts.utils.Assignable;
import model.contracts.utils.Identifiable;
import model.contracts.utils.Nameble;
import model.enums.Status;
import model.tasks.BaseTask;
import model.tasks.BugImpl;
import model.tasks.FeedbackImpl;
import model.tasks.StoryImpl;
import utils.ParsingHelpers;
import utils.ValidationHelpers;

import javax.naming.Name;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static model.CommentImpl.*;

public class ApplicationRepositoryImpl implements ApplicationRepository {
    public static final String ALREADY_EXIST_ERROR = "%s with name %s already exist in the Database.%n";
    public static final String ENTITY_DOES_NOT_EXIST = "%s with name %s not found in the Database.%n";
    public static final String CREATE_SUCCESSFUL_MESSAGE = "%s %s was successfully created.%n";
    public static final String SUCCESSFULLY_ADDED_MEMBER_IN_TEAM = "Member with name %s was added to team %s.%n";
    public static final String ALREADY_IN_TEAM = "%s is already member of %s.%n";
    private static final String CREATED_NEW_BOARD = "Board with name %s was created in team %s.%n";
    public static final String ASSIGNEE_NOT_IN_TEAM_AS_BOARD = "Assignee %s should be in the same team as board %s.%n";
    public static final String TASK_DOES_NOT_EXIST = "Task with id: %d does not exist.";
    public static final String INVALID_FIELD_ERROR = "No field with name %s for tasks of type %s.%n";
    private static int id = 0;
    private List<Team> teams;
    private List<Member> members;
    private List<Board> boards;
    private List<Bug> bugs;
    private List<Story> stories;
    private List<Feedback> feedbacks;
    private List<Task> tasks;

    public ApplicationRepositoryImpl() {
        this.teams = new ArrayList<>();
        this.members = new ArrayList<>();
        this.boards = new ArrayList<>();
        this.bugs = new ArrayList<>();
        this.feedbacks = new ArrayList<>();
        this.stories = new ArrayList<>();
        this.tasks = new ArrayList<>();
    }


    @Override
    public Member createMember(String name) {
        for (Member m : members) {
            //todo
            if (m.getName().equalsIgnoreCase(name)) {
                throw new UserAlreadyExistException(ALREADY_EXIST_ERROR.formatted("Member", name));
            }
        }
        Member member = new MemberImpl(name);
        this.members.add(member);
        System.out.printf(CREATE_SUCCESSFUL_MESSAGE.formatted("Member", name));
        return member;
    }

    @Override
    public String showAllMembers() {
        String memberNames = members.stream().map(Member::getName).collect(Collectors.joining(", "));
        System.out.printf("Members:%n%s%n", memberNames);
        return String.format("Members:%n%s%n", memberNames);
    }

    @Override
    public String showMemberActivities(String name) {
        Member member = findMemberByName(name);
        System.out.println(member.displayHistory());
        return member.displayHistory();
    }

    @Override
    public Team createTeam(String name) {
        List<String> teamNames = teams.stream().map(Nameble::getName).toList();
        if (teamNames.contains(name)) throw new TeamAlreadyExistException(ALREADY_EXIST_ERROR.formatted("Team", name));
        Team team = new TeamImpl(name);
        teams.add(team);
        System.out.printf(CREATE_SUCCESSFUL_MESSAGE.formatted("Team", name));
        return team;
    }

    @Override
    public String showAllTeams() {
        String teamNames = teams.stream().map(Nameble::getName).collect(Collectors.joining(", "));
        //todo
        System.out.printf("Teams:%n%s%n", teamNames);
        return String.format("Teams:%n%s%n", teamNames);

    }

    @Override
    public String showTeamActivities(String name) {
        Team team = findTeamByName(name);
        return team.showAllActivities();
    }

    @Override
    public String addMemberToTeam(String memberName, String teamName) {
        Member member = findMemberByName(memberName);
        Team team = findTeamByName(teamName);
        if (team.getMembers().contains(member))
            throw new AlreadyMemberOfTeam(ALREADY_IN_TEAM.formatted(memberName, teamName));
        team.addMember(member);
        System.out.printf(SUCCESSFULLY_ADDED_MEMBER_IN_TEAM, memberName, teamName);
        return String.format(SUCCESSFULLY_ADDED_MEMBER_IN_TEAM, memberName, teamName);
    }

    @Override
    public String showAllTeamMembers(String name) {
        Team team = findTeamByName(name);
        return team.showAllTeamMembers();
        //todo
    }

    @Override
    public Board createNewBoardInTeam(String boardName, String teamName) {
        Team team = findTeamByName(teamName);
        Board board = team.addBoard(boardName);
        boards.add(board);
        System.out.printf(CREATED_NEW_BOARD, boardName, teamName);
        return board;
    }

    @Override
    public String showAllBoards() {
        String boardNames = boards.stream().map(Board::getNameWithTeam).collect(Collectors.joining(""));
        //todo
        System.out.printf("Boards:%n%s%n", boardNames);
        return String.format("Boards:%n%s%n", boardNames);
    }

    @Override
    public String showBoardActivities(Board board) {
        return board.displayAllTasks();
    }

    @Override
    public Task createNewTaskInBoard(String taskType, String boardName, String teamName, String[] params) {
        Task task;
        Board board = findBoardByName(boardName, teamName);
        Team team = findTeamByName(teamName);
        if (validateTaskCreatedWithAssignee(taskType, params)) {

            Member member = findMemberByName(params[2]);
            if (!(team.getMembers().contains(member) && team.getBoards().contains(board))) {

                throw new MemberAndBoardAreInDifferentTeamsException(
                        ASSIGNEE_NOT_IN_TEAM_AS_BOARD.formatted(member.getName(), board.getName()));
            }
        }
        task = createTask(taskType, params);
        board.addTask(task);
        return task;
        // TODO: 17.5.2024 г.

    }

    private boolean validateTaskCreatedWithAssignee(String taskType, String[] params) {
        if (taskType.equalsIgnoreCase("Bug") && params.length == 6) {
            return true;
        } else if (taskType.equalsIgnoreCase("Story") && params.length == 5) {
            return true;
        }
        return false;
    }

    @Override
    public String changeBugDetails(int id, String fieldName, String value) {
        // TODO: 17.5.2024 г.  
        Bug bug;
        try {
            bug = (Bug) findTaskById(id);
        } catch (ClassCastException ex) {
            throw new ProvidedTaskIsNotABugException("Provided task with id %d is not of type Bug.%n".formatted(id));
        }
        String result = "%s changed to %s for Bug with id: %d.%n";
        if (fieldName.equalsIgnoreCase("Status")) {
            bug.setStatus(value);
            result = result.formatted("Status", value, id);


        } else if (fieldName.equalsIgnoreCase("Priority")) {
            bug.changePriority(value);
            result = result.formatted("Priority", value, id);


        } else if (fieldName.equalsIgnoreCase("Severity")) {
            bug.changeSeverity(value);
            result = result.formatted("Severity", value, id);

        } else {
            throw new InvalidFieldNameForBugException(INVALID_FIELD_ERROR.formatted(fieldName, bug.getRealName()));
        }
        System.out.println(result);
        return result;
    }

    @Override
    public String changeFeedbackDetails(int id, String fieldName, String value) {
        // TODO: 17.5.2024 г.
        Feedback feedback;
        try {
            feedback = (Feedback) findTaskById(id);
        } catch (ClassCastException ex) {
            throw new ProvidedTaskIsNotAFeedbackException("Provided task with id %d is not of type Feedback.%n".formatted(id));
        }
        String result = "%s changed to %s for Feedback with id: %d.%n";
        if (fieldName.equalsIgnoreCase("Status")) {
            feedback.setStatus(value);
            result = result.formatted("Status", value, id);

        } else if (fieldName.equalsIgnoreCase("Rating")) {
            feedback.changeRating(ParsingHelpers.tryParseInt(value));
            result = result.formatted("Rating", value, id);
        } else {
            throw new InvalidFieldNameForFeedbackException(INVALID_FIELD_ERROR.formatted(fieldName, feedback.getRealName()));
        }
        System.out.println(result);
        return result;
    }

    @Override
    public String changeStoryDetails(int id, String fieldName, String value) {
        // TODO: 17.5.2024 г.  
        Story story;
        try {
            story = (Story) findTaskById(id);
        } catch (ClassCastException ex) {
            throw new ProvidedTaskIsNotAStoryException("Provided task with id %d is not of type Story.%n".formatted(id));
        }
        String result = "%s changed to %s for Story with id: %d.%n";
        if (fieldName.equalsIgnoreCase("Status")) {
            story.setStatus(value);
            result = result.formatted("Status", value, id);
        } else if (fieldName.equalsIgnoreCase("Priority")) {
            story.changePriority(value);
            result = result.formatted("Priority", value, id);
        } else if (fieldName.equalsIgnoreCase("Size")) {
            story.changeSize(value);
            result = result.formatted("Size", value, id);
        } else {
            throw new InvalidFieldNameForStoryException(INVALID_FIELD_ERROR.formatted(fieldName, story.getRealName()));
        }
        System.out.println(result);
        return result;
    }

    //// TODO: 19.5.2024 г.
    @Override

    public <T extends AssignableTask> String assignTaskToMember(T task, String memberName) {
        if (task.hasAssignee()) {
            unAssignTaskToMember(task);
        }
        Member member = findMemberByName(memberName);
        task.setAssignee(member);
        member.addTask(task);
        System.out.printf("Task ID#%s was assigned to %s.%n", task.getID(), member.getName());
        return "Task ID#%s was assigned to %s.%n".formatted(task.getID(), member.getName());
    }

    @Override
    public <T extends AssignableTask> String unAssignTaskToMember(T task) {
        if(task.hasAssignee()){
            Member member = task.getAssignee();
            member.removeTask(task);
            task.setAssignee(null);
            System.out.printf("Task ID#%s was unassigned from %s.%n", task.getID(), member.getName());
            return "Task ID#%s was unassigned from %s.%n".formatted(task.getID(), member.getName());
        }

        System.out.printf("Task with ID#%s has no assignee.", task.getID());
        return "Task with ID#%s has no assignee.".formatted(task.getID());
    }

    @Override
    public String addCommentToTask(int id, String content, String author) {
        //// TODO: 19.5.2024 г. ask trainer
        ValidationHelpers.validateStringLength(content, CONTENT_LEN_MIN, CONTENT_LEN_MAX, CONTENT_LEN_ERR);
        Task task = findTaskById(id);
        Comment comment = new CommentImpl(author, content);
        task.addComment(comment);
        System.out.printf("Comment was added to task ID#%s by %s.%n", task.getID(), author);
        return "Comment was added to task ID#%s.%n".formatted(task.getID());
    }

    public Task findTaskById(int id) {
        return tasks.stream()
                .filter(t -> t.getID() == id)
                .findFirst()
                .orElseThrow(() -> new TaskWithIdDoesNotExist(TASK_DOES_NOT_EXIST.formatted(id)));
    }

    @Override
    public List<Nameble> findByName(String name) {
        StringBuilder sb = new StringBuilder();
        List<Nameble> nameableList = new ArrayList<>();
        List<String> teamNames = teams.stream().map(Team::getName).filter(tName -> tName.equalsIgnoreCase(name)).toList();
        List<String> boardNames = boards.stream().map(Board::getName).filter(tName -> tName.equalsIgnoreCase(name)).toList();
        List<String> memberNames = members.stream().map(Member::getName).filter(mName -> mName.equalsIgnoreCase(name)).toList();

        if (!teamNames.isEmpty()) {
            teams.forEach(t -> {
                if (t.getName().equalsIgnoreCase(name)) {
                    nameableList.add(t);
                }
            });
            sb.append("Teams:").append(System.lineSeparator());
            teamNames.forEach(t -> sb.append(t).append(" "));
            sb.append(System.lineSeparator());
        }
        if (!memberNames.isEmpty()) {
            members.forEach(m -> {
                if (m.getName().equalsIgnoreCase(name)) {
                    nameableList.add(m);
                }
            });
            sb.append("Members:").append(System.lineSeparator());
            memberNames.forEach(m -> sb.append(m).append(" "));
            sb.append(System.lineSeparator());
        }
        if (!boardNames.isEmpty()) {
            boards.forEach(b -> {
                if (b.getName().equalsIgnoreCase(name)) {
                    nameableList.add(b);
                }
            });
            sb.append("Boards:").append(System.lineSeparator());
            memberNames.forEach(b -> sb.append(b).append(" "));
            sb.append(System.lineSeparator());
        }
        System.out.println(sb);
        return nameableList;
    }

    @Override
    public Member findMemberByName(String name) {
        return members.stream()
                .filter(m -> m.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new UserWithNameDoesNotExist(ENTITY_DOES_NOT_EXIST.formatted("Member", name)));
    }

    @Override
    public Team findTeamByName(String name) {
        return teams.stream()
                .filter(t -> t.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new TeamWithNameDoesNotExist(ENTITY_DOES_NOT_EXIST.formatted("Team", name)));
    }

    @Override
    public Board findBoardByName(String boardName, String teamName) {
        Team team = findTeamByName(teamName);
        return team.getBoards()
                .stream()
                .filter(b -> b.getName().equalsIgnoreCase(boardName))
                .findFirst()
                .orElseThrow(() -> new NoBoardInTeamException("No board with name %s found in team %s.%n".formatted(boardName, teamName)));

    }

    @Override
    public Task createTask(String taskType, String... params) {
        int paramCount = params.length;
        String task = taskType.toLowerCase();
        switch (task) {

            case "bug" -> {
                Bug bug;
                if (paramCount == 6) {
                    bug = BugImpl.createBug(
                            ++id, params[0], params[1], findMemberByName(params[2]), params[3], params[4], params[5]);
                } else if (paramCount == 5) {

                    bug = BugImpl.createBug(
                            ++id, params[0], params[1], params[2], params[3], params[4]);
                } else {
                    throw new InvalidParameterCountForTaskCreation(
                            "Provided parameters count is invalid for Bug.%nPlease provide: Title, Description, Assignee(optional), Priority, Severity, Steps to Reproduce.%n".formatted());
                }

                bugs.add(bug);
                tasks.add(bug);
                return bug;
            }
            case "story" -> {
                Story story;
                if (paramCount == 5) {

                    story = StoryImpl.createStory(++id, params[0], params[1], findMemberByName(params[2]), params[3], params[4]);
                } else if (paramCount == 4) {
                    story = StoryImpl.createStory(++id, params[0], params[1], params[2], params[3]);

                } else {
                    throw new InvalidParameterCountForTaskCreation(
                            "Provided parameters count is invalid for Story.%nPlease provide: Title, Description, Assignee(optional), Priority, Size.%n"
                                    .formatted());
                }
                stories.add(story);
                tasks.add(story);
                return story;
            }
            case "feedback" -> {
                if (paramCount != 3) {
                    throw new InvalidParameterCountForTaskCreation(
                            "Provided parameters count is invalid for Feedback.%nPlease provide: Title, Description, Rating.%n"
                                    .formatted());
                }
                Feedback feedback = FeedbackImpl.createFeedback(
                        ++id, params[0], params[1], ParsingHelpers.tryParseInt(params[2]));
                feedbacks.add(feedback);
                tasks.add(feedback);
                return feedback;
            }
            default -> {
                throw new InvalidParameterCountForTaskCreation("");
            }
        }

    }
}
