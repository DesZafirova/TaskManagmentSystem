package model;

import model.contracts.Board;
import model.contracts.Member;
import model.contracts.Team;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TeamImplTest {
    private static final int NAME_MIN_LENGTH = 5;
    private static final int NAME_MAX_LENGTH = 15;
    private static final String VALID_TEAM_NAME_LENGTH = "x".repeat(NAME_MIN_LENGTH + 1);
    private static final String INVALID_TEAM_NAME_LENGTH = "x".repeat(NAME_MAX_LENGTH + 1);


    private Team testTeam;
    private List<Member> members;
    private List<Board> boards;


    @Before
    public void before(){
        testTeam = TeamImplTest.initializeTeam();
        members = new ArrayList<>();
        boards = new ArrayList<>();
    }
    

    private static Team initializeTeam() {
        return new TeamImpl(VALID_TEAM_NAME_LENGTH);
    }

}