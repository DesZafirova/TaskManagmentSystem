package commands.createCommands;

import core.ApplicationRepositoryImpl;
import core.contracts.ApplicationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddMemberToTeamTest {
    private AddMemberToTeam addMemberToTeam;
    private ApplicationRepository app;

    @BeforeEach
    void setUp() {
        app = new ApplicationRepositoryImpl();
        addMemberToTeam = new AddMemberToTeam(app);
    }

    @Test
    void execute() {
        assertNotNull(addMemberToTeam);

    }
    @Test
    void extractParams(){
        assertNotNull(addMemberToTeam.extractParameters("some input / another"));
        assertEquals(2 , addMemberToTeam.extractParameters("some input / another").size());
    }
}