package model;

import exceptions.BoardNotFoundInTeam;
import model.contracts.Board;
import model.contracts.Member;
import model.contracts.utils.Nameble;
import model.contracts.Team;
import utils.contracts.PrintableName;
import utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TeamImpl implements Team, Nameble, PrintableName {
    private static final int NAME_MIN_LENGTH = 5;
    private static final int NAME_MAX_LENGTH = 15;
    private static final String NAME_ERROR_MESSAGE = String.format("Name must be between %d and %d characters.",
            NAME_MIN_LENGTH, NAME_MAX_LENGTH);
    public static final String BOARD_NAME_EXISTS = "Board with name %s already exists for team %s.";
    public static final String BOARD_CREATED = "Board with name %s was created for team %s.";
    public static final String ADD_MEMBER = "Add new member with name %s to team %s.";
    public static final String MEMBER_REMOVED = "Member with name %s was removed from team with name %s.";
    public static final String BOARD_REMOVED = "Board with name %s was removed from team with name %s.";
    public static final String NOT_MEMBER_OF_TEAM = "%s is not a member of team %s.";
    public static final String NO_BOARD_ERROR = "Team %s does not have board with name %s.";
    public static final String BOARD_NOT_FOUND_IN_TEAM = "Board with name %s not found for team %s.%n";

    private String name;
    private List<Member> members;
    private List<Board> boards;
    private ActivityHistory activityHistory;

    public TeamImpl(String name) {
        this.setName(name);
        this.members = new ArrayList<>();
        this.boards = new ArrayList<>();
        this.activityHistory = new ActivityHistory();
    }


    public List<Member> getMembers() {
        return List.copyOf(members);
    }


    public List<Board> getBoards() {
        return List.copyOf(boards);
    }

    @Override
    public Board getBoardByName(String boardName) {
        return boards.stream()
                .filter(b -> b.getName().equalsIgnoreCase(boardName))
                .findFirst()
                .orElseThrow(() -> new BoardNotFoundInTeam(BOARD_NOT_FOUND_IN_TEAM.formatted(boardName, name)));
    }


    @Override
    public void addMember(Member member) {
        members.add(member);
        activityHistory.addEventToHistory(ADD_MEMBER.formatted(member.getName(), this.name));

    }

    @Override
    public void removeMember(Member member) {
        if (members.remove(member)) {
            activityHistory.addEventToHistory(MEMBER_REMOVED.formatted(member.getName(), this.name));
            return;
        }
        throw new IllegalArgumentException(NOT_MEMBER_OF_TEAM.formatted(member.getName(), this.name));
    }


    @Override
    public Board addBoard(String board) {
        for (Board b : boards) {
            if (b.getName().equalsIgnoreCase(board)) {
                throw new IllegalArgumentException(BOARD_NAME_EXISTS.formatted(board, this.name));
            }
        }
        Board newBoard = new BoardImpl(board, this);
        boards.add(newBoard);
        activityHistory.addEventToHistory(BOARD_CREATED.formatted(board, this.name));
        return newBoard;
    }

    @Override
    public void removeBoard(Board board) {
        if (boards.remove(board)) {
            activityHistory.addEventToHistory(BOARD_REMOVED.formatted(board.getName(), this.name));
            return;
        }
        throw new IllegalArgumentException(NO_BOARD_ERROR.formatted(board.getName(), this.name));
    }

    @Override
    public String showAllActivities() {

        String result = """
                Team: %s 
                Team Activities: 
                                
                %s""".formatted(this.name,
                activityHistory.listHistory());
        System.out.println(result);
        return result;
    }

    @Override
    public String showAllTeamMembers() {
        String result = "";
        if (members.isEmpty()) {
            result = "No members in team %s.".formatted(this.name);
            return result;
        }
        result = """
                Team: %s
                Team Members:
                %s
                """.formatted(this.name,
                members.stream().map(Member::getName).collect(Collectors.joining(", ")));
        System.out.println(result);
        return result;
    }

    @Override
    public String showBoards() {
        StringBuilder sb = new StringBuilder();
        if(!boards.isEmpty()){

            boards.forEach(b -> sb.append("| ").append(b.getName()).append(" | "));
            return sb.toString();
        }
        return "No Boards are created in team %s.%n".formatted(this.name);
    }

    @Override
    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        ValidationHelpers.validateStringLength(name, NAME_MIN_LENGTH, NAME_MAX_LENGTH, NAME_ERROR_MESSAGE);
        this.name = name;
    }


    @Override
    public String toString() {
        return """
                %s: %s
                                 
                ==============
                Members:
                %s
                                 
                ==============
                Boards:
                %s
                """.formatted(
                getRealName(),
                this.name,
                members.stream().map(Member::getName).collect(Collectors.joining(", ")),
                boards.stream().map(Board::getName).collect(Collectors.joining(", "))
        );

    }

}
