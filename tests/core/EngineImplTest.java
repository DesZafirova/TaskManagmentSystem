package core;

import commands.enums.CommandType;
import core.contracts.ApplicationRepository;
import core.contracts.CommandController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EngineImplTest {
   private EngineImpl engine;

    @BeforeEach
    void setUp() {
        engine = new EngineImpl();
    }

    @Test
    void run() {
        assertNotNull(engine);
    }
    @Test
    void processCommand(){
        assertNotNull(engine.processCommand(CommandType.SHOWTEAMS.name()));

    }

}