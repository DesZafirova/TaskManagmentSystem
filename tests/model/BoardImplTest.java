package model;

import model.contracts.Board;
import model.contracts.Team;
import model.contracts.tasks.Task;
import model.tasks.FeedbackImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardImplTest {
    private Team team;
    private Board board;
    private Task task;

    @BeforeEach
    void setUp() {
        team = new TeamImpl("testTeam");
        board = team.addBoard("testBoard");
        task = FeedbackImpl.createFeedback(1,"test title", "test description", 5);
    }

    @Test
    void validateName() {
        assertEquals("testBoard", board.getName());
        assertDoesNotThrow(() -> team.addBoard("valid"));
        assertEquals("testBoard", board.getName());
        assertThrows(IllegalArgumentException.class, () -> team.addBoard("a"));
        assertThrows(IllegalArgumentException.class, () -> team.addBoard("a".repeat(16)));
    }

    @Test
    void getNameWithTeam() {
        String result = """
                Board: %s [Team: %s]
                """.formatted(board.getName(), board.getTeam().getName());
        assertEquals(result, board.getNameWithTeam());
        assertNotNull(board.getNameWithTeam(), result);

    }
}