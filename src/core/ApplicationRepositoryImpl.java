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
import model.contracts.utils.Nameble;
import model.tasks.BugImpl;
import model.tasks.FeedbackImpl;
import model.tasks.StoryImpl;
import utils.ParsingHelpers;
import utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static model.CommentImpl.*;

public class ApplicationRepositoryImpl implements ApplicationRepository {

    public static final String CREATE_SUCCESSFUL_MESSAGE = "%s %s was successfully created.%n";
    public static final String SUCCESSFULLY_ADDED_MEMBER_IN_TEAM = "Member with name %s was added to team %s.%n";
    public static final String ALREADY_IN_TEAM = "%s is already member of %s.%n";
    private static final String CREATED_NEW_BOARD = "Board with name %s was created in team %s.%n";
    public static final String ASSIGNEE_NOT_IN_TEAM_AS_BOARD = "Assignee %s should be in the same team as board %s.%n";
    public static final String NO_ENTITY_IN_DATABASE = "No %s register in the Database.%n";
    private static int id = 0;
    private final List<Team> teams;
    private final List<Member> members;
    private final List<Board> boards;
    private final List<Task> tasks;

    public ApplicationRepositoryImpl() {
        this.teams = new ArrayList<>();
        this.members = new ArrayList<>();
        this.boards = new ArrayList<>();
        this.tasks = new ArrayList<>();
    }

    @Override
    public Member createMember(String name) {
        //TODO Try with stream;
        for (Member m : members) {
            if (m.getName().equalsIgnoreCase(name)) {
                throw new EntityAlreadyExistException("Member", name);
            }
        }
        Member member = new MemberImpl(name);
        this.members.add(member);
        System.out.printf(CREATE_SUCCESSFUL_MESSAGE.formatted("Member", name));
        return member;
    }

    @Override
    public String showAllMembers() {
        if (!members.isEmpty()) {
            String memberNames = members.stream().map(Member::getName).collect(Collectors.joining(", "));
            System.out.printf("Members:%n%s%n", memberNames);
            return String.format("Members:%n%s%n", memberNames);
        }
        System.out.printf(NO_ENTITY_IN_DATABASE, "Member");
        return String.format(NO_ENTITY_IN_DATABASE, "Member");
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
        if (teamNames.contains(name)) throw new EntityAlreadyExistException("Team", name);
        Team team = new TeamImpl(name);
        teams.add(team);
        System.out.printf(CREATE_SUCCESSFUL_MESSAGE.formatted("Team", name));
        return team;
    }

    @Override
    public String showAllTeams() {
        if (!teams.isEmpty()) {
            String teamNames = teams.stream().map(Nameble::getName).collect(Collectors.joining(", "));

            System.out.printf("Teams:%n%s%n", teamNames);
            return String.format("Teams:%n%s%n", teamNames);
        }
        System.out.printf(NO_ENTITY_IN_DATABASE, "Teams");
        return String.format(NO_ENTITY_IN_DATABASE, "Teams");
    }

    @Override
    public String showTeamBoards(String name) {
        Team team = findTeamByName(name);
        String result = team.showBoards();
        System.out.println(result);
        return result;
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
        //// TODO: 20.5.2024 г.
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
        if (!boards.isEmpty()) {
            String boardNames = boards.stream().map(Board::getNameWithTeam).collect(Collectors.joining(""));
            //todo
            System.out.printf("Boards:%n%s%n", boardNames);
            return String.format("Boards:%n%s%n", boardNames);
        }
        System.out.printf(NO_ENTITY_IN_DATABASE, "Board");
        return String.format(NO_ENTITY_IN_DATABASE, "Board");
    }

    @Override
    public String showBoardActivities(String teamName, String boardName) {
        Team team = findTeamByName(teamName);
        Board board = team.getBoardByName(boardName);
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
    }

    private boolean validateTaskCreatedWithAssignee(String taskType, String[] params) {
        if (taskType.equalsIgnoreCase("Bug") && params.length == 6) {
            return true;
        } else return taskType.equalsIgnoreCase("Story") && params.length == 5;
    }

    @Override
    public String changeBugDetails(int id, String fieldName, String value) {
        // TODO: Could Improve TASK DETAIL MUTATION
        Bug bug;
        try {
            bug = (Bug) findTaskById(id);
        } catch (ClassCastException ex) {
            throw new ProvidedTaskIsNotABugException(id);
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
            throw new InvalidFieldNameForBugException(fieldName, bug.getRealName());
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
            throw new ProvidedTaskIsNotAFeedbackException(id);
        }
        String result = "%s changed to %s for Feedback with id: %d.%n";
        if (fieldName.equalsIgnoreCase("Status")) {
            feedback.setStatus(value);
            result = result.formatted("Status", value, id);

        } else if (fieldName.equalsIgnoreCase("Rating")) {
            feedback.changeRating(ParsingHelpers.tryParseInt(value));
            result = result.formatted("Rating", value, id);
        } else {
            throw new InvalidFieldNameForFeedbackException(fieldName, feedback.getRealName());
        }
        System.out.println(result);
        return result;
    }

    @Override
    public String changeStoryDetails(int id, String fieldName, String value) {
        // TODO: Could be Improve!
        Story story;
        try {
            story = (Story) findTaskById(id);
        } catch (ClassCastException ex) {
            throw new ProvidedTaskIsNotAStoryException(id);
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
            throw new InvalidFieldNameForStoryException(fieldName, story.getRealName());
        }
        System.out.println(result);
        return result;
    }

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
        if (task.hasAssignee()) {
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
                .orElseThrow(() -> new TaskWithIdDoesNotExist(id));
    }

    @Override
    public List<Nameble> findByName(String name) {
//        //TODO Command not implemented with commandENUM
        StringBuilder sb = new StringBuilder();
        List<Nameble> nameableList = new ArrayList<>();
        filter(name, teams, nameableList, sb, "Teams");
        filter(name, members, nameableList, sb, "Members");
        filter(name, boards, nameableList, sb, "Boards");

        System.out.println(sb);
        return nameableList;
    }

    @Override
    public Member findMemberByName(String name) {
        return members.stream()
                .filter(m -> m.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new EntityWithNameDoesNotExist("Member", name));
    }

    @Override
    public Team findTeamByName(String name) {
        return teams.stream()
                .filter(t -> t.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new EntityWithNameDoesNotExist("Team", name));
    }

    @Override
    public Board findBoardByName(String boardName, String teamName) {
        Team team = findTeamByName(teamName);
        return team.getBoards()
                .stream()
                .filter(b -> b.getName().equalsIgnoreCase(boardName))
                .findFirst()
                .orElseThrow(() -> new NoBoardInTeamException(boardName, teamName));

    }

    @Override
    public Task createTask(String taskType, String... params) {
        int paramCount = params.length;
        String task = taskType.toLowerCase();

        switch (task) {
            case "bug" -> {
                return addTask(createBug(params, paramCount));
            }
            case "story" -> {
                return addTask(createStory(params, paramCount));
            }
            case "feedback" -> {
                return addTask(createFeedback(params, paramCount));
            }
            default -> throw new TaskTypeIsNotSupportedException(taskType);

        }
    }

    private static Feedback createFeedback(String[] params, int paramCount) {
        if (paramCount != 3) throw new InvalidParameterCountForTaskCreation("Feedback");

        return FeedbackImpl.createFeedback(
                ++id, params[0], params[1], ParsingHelpers.tryParseInt(params[2]));
    }

    private Story createStory(String[] params, int paramCount) {
        //TODO get realName()
        return switch (paramCount) {
            case 5 -> StoryImpl.createStory(++id, params[0], params[1], findMemberByName(params[2]), params[3], params[4]);
            case 4 -> StoryImpl.createStory(++id, params[0], params[1], params[2], params[3]);
            default -> throw new InvalidParameterCountForTaskCreation("Story");
        };
    }

    private Bug createBug(String[] params, int paramCount) {
        return switch (paramCount) {
            case 6 -> BugImpl.createBug(
                    ++id, params[0], params[1], findMemberByName(params[2]), params[3], params[4], params[5]);
            case 5 -> BugImpl.createBug(
                    ++id, params[0], params[1], params[2], params[3], params[4]);
            default -> throw new InvalidParameterCountForTaskCreation("Bug");
        };
    }

    private Task addTask(Task task) {
        tasks.add(task);
        return task;
    }

    private <T extends Nameble> void filter(String name, List<T> items, List<Nameble> nameableList, StringBuilder sb, String category) {
        List<T> matchedItems = items.stream()
                .filter(item -> item.getName().equalsIgnoreCase(name))
                .toList();

        if (!matchedItems.isEmpty()) {
            nameableList.addAll(matchedItems);
            sb.append(category).append(":").append(System.lineSeparator());
            matchedItems.forEach(item -> sb.append(item.getName()).append(" "));
            sb.append(System.lineSeparator());
        }
    }
}
