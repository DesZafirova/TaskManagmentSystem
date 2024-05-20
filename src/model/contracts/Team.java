package model.contracts;


import model.contracts.utils.Nameble;

import java.util.List;

public interface Team extends Nameble {

    List<Member> getMembers();
    List<Board> getBoards();
    Board getBoardByName(String boardName);
    void addMember(Member member);
    void removeMember(Member member);
    Board addBoard(String board);
    void removeBoard(Board board);
    String showAllActivities();
    String showAllTeamMembers();
    String showBoards();

}
