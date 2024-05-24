package model;

import model.contracts.Board;
import model.contracts.Member;
import model.contracts.Team;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class TeamImplTest {
    static Team testTeam;
    Board testBoard;
    Member testMember;

    @BeforeEach
    public void beforeEach() {
        testTeam = new TeamImpl("testTeam");
        testBoard = testTeam.addBoard("testBoard");
        testMember = new MemberImpl("testMember");
        testTeam.addMember(testMember);


    }


    @Test
    void getMembers() {
        List<Member> members = testTeam.getMembers();
        assertNotNull(members);
        assertEquals(members.size(), 1);

        assertThrows(UnsupportedOperationException.class, () -> members.add(testMember));
        assertEquals(members.size(), 1);
    }

    @Test
    void getBoards() {
        List<Board> board = testTeam.getBoards();
        assertNotNull(board);
        assertEquals(board.size(), 1);
        assertThrows(UnsupportedOperationException.class, () -> board.add(testBoard));
        assertEquals(board.size(), 1);
    }

    @Test
    void getBoardByName() {
        Board foundBoard = testTeam.getBoardByName("testBoard");
        assertNotNull(foundBoard);
        assertSame(testBoard, foundBoard);
    }

    @Test
    void addMember() {
        Member member = new MemberImpl("Michael");
        assertEquals(testTeam.getMembers().size(), 1);
        testTeam.addMember(member);
        assertEquals(testTeam.getMembers().size(), 2);

    }

    @Test
    void removeMember() {
        assertEquals(testTeam.getMembers().size(), 1);
        testTeam.removeMember(testMember);
        assertEquals(testTeam.getMembers().size(), 0);
        assertThrows(IllegalArgumentException.class, () ->testTeam.removeMember(testMember));
    }

    @Test
    void addBoard() {
        assertThrows(IllegalArgumentException.class, () -> testTeam.addBoard(testBoard.getName()));
        assertEquals(testTeam.getBoards().size(), 1);
        testTeam.addBoard("testboard2");
        assertEquals(testTeam.getBoards().size(), 2);

    }

    @Test
    void removeBoard() {
        assertEquals(testTeam.getBoards().size(), 1);
        testTeam.removeBoard(testBoard);
        assertEquals(testTeam.getBoards().size(), 0);
        assertThrows(IllegalArgumentException.class, () ->testTeam.removeBoard(testBoard));

    }

    @Test
    void showAllActivities() {
        assertEquals(5, testTeam.showAllActivities().split("\n").length);
        Member member = new MemberImpl("Steve");
        testTeam.addMember(member);
        assertEquals(6, testTeam.showAllActivities().split("\n").length);
    }

    @Test
    void showAllTeamMembers() {
        assertEquals(1, testTeam.showAllTeamMembers().split("\n")[2].split(",").length);
        testTeam.removeMember(testMember);
        assertEquals("No members in team %s.".formatted(testTeam.getName()), testTeam.showAllTeamMembers());
        testTeam.addMember(testMember);
        Member member = new MemberImpl("Steve");
        Member member1 = new MemberImpl("Peter");
        Member member2 = new MemberImpl("Maxis");
        Member member3 = new MemberImpl("Julia");
        Member member4 = new MemberImpl("Samantha");
        testTeam.addMember(member);
        assertEquals(2, testTeam.showAllTeamMembers().split("\n")[2].split(",").length);
        testTeam.addMember(member1);
        testTeam.addMember(member2);
        testTeam.addMember(member3);
        testTeam.addMember(member4);
        assertEquals(6, testTeam.showAllTeamMembers().split("\n")[2].split(",").length);



    }

    @Test
    void showBoards() {
        testTeam.removeBoard(testBoard);
        assertEquals("No Boards are created in team %s.%n".formatted(testTeam.getName()), testTeam.showBoards());
        testTeam.addBoard("testBoard");
        System.out.println(testTeam.showBoards());
        assertEquals(1, testTeam.showBoards().split(", ").length);
        Board board1 = testTeam.addBoard("board1");
        Board board2 = testTeam.addBoard("board2");
        Board board3 = testTeam.addBoard("board3");
        assertEquals(4, testTeam.showBoards().split(", ").length);
        assertEquals(board3.getName().trim(), testTeam.showBoards().split(", ")[3].trim());
    }

    @Test
    void getName() {
        assertEquals(testTeam.getName(), "testTeam");
    }

    @Test
    void testToString() {
        assertEquals(3, testTeam.toString().split("==============").length);
        assertEquals(9, testTeam.toString().split("\n").length);
        assertEquals("Team: testTeam" , testTeam.toString().split("\n")[0]);
        assertEquals("" , testTeam.toString().split("\n")[1]);
        assertEquals("==============" , testTeam.toString().split("\n")[2]);
        assertEquals("Members:" , testTeam.toString().split("\n")[3]);
        assertEquals("testMember" , testTeam.toString().split("\n")[4]);
        assertEquals("" , testTeam.toString().split("\n")[5]);
        assertEquals("==============" , testTeam.toString().split("\n")[6]);
        assertEquals("Boards:" , testTeam.toString().split("\n")[7]);
        assertEquals("testBoard" , testTeam.toString().split("\n")[8]);
}}