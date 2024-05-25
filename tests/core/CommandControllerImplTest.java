package core;

import commands.enums.CommandType;
import core.contracts.ApplicationRepository;
import exceptions.InvalidCommandTypeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandControllerImplTest {
    private CommandControllerImpl controller;
    private ApplicationRepository app;

    @BeforeEach
    void setUp() {
        controller = new CommandControllerImpl();
        app = new ApplicationRepositoryImpl();
    }

    @Test
    void createCommandFromCommandName() {
        assertNotNull(controller.createCommandFromCommandName(CommandType.LC.name(), app));
        assertNotNull(controller.createCommandFromCommandName(CommandType.SETCOLOR.name(), app));
        assertNotNull(controller.createCommandFromCommandName(CommandType.TUTORIAL.name(), app));
        assertNotNull(controller.createCommandFromCommandName(CommandType.CREATEMEMBER.name(), app));
        assertNotNull(controller.createCommandFromCommandName(CommandType.SHOWALLMEMBERS.name(), app));
        assertNotNull(controller.createCommandFromCommandName(CommandType.SHOWMEMBERACTIVITY.name(), app));
        assertNotNull(controller.createCommandFromCommandName(CommandType.CREATETEAM.name(), app));
        assertNotNull(controller.createCommandFromCommandName(CommandType.SHOWTEAMS.name(), app));
        assertNotNull(controller.createCommandFromCommandName(CommandType.SHOWTEAMACTIVITY.name(), app));
        assertNotNull(controller.createCommandFromCommandName(CommandType.ADDMEMBERTOTEAM.name(), app));
        assertNotNull(controller.createCommandFromCommandName(CommandType.SHOWTEAMMEMBERS.name(), app));
        assertNotNull(controller.createCommandFromCommandName(CommandType.CREATEBOARDINTEAM.name(), app));
        assertNotNull(controller.createCommandFromCommandName(CommandType.SHOWALLTEAMBOARDS.name(), app));
        assertNotNull(controller.createCommandFromCommandName(CommandType.SHOWBOARDACTIVITY.name(), app));
        assertNotNull(controller.createCommandFromCommandName(CommandType.CREATETASKINBOARD.name(), app));
        assertNotNull(controller.createCommandFromCommandName(CommandType.CHANGEBUGDETAILS.name(), app));
        assertNotNull(controller.createCommandFromCommandName(CommandType.CHANGESTORYDETAILS.name(), app));
        assertNotNull(controller.createCommandFromCommandName(CommandType.CHANGEFEEDBACKDETAILS.name(), app));
        assertNotNull(controller.createCommandFromCommandName(CommandType.ASSIGNTASK.name(), app));
        assertNotNull(controller.createCommandFromCommandName(CommandType.UNAASIGNTASK.name(), app));
        assertNotNull(controller.createCommandFromCommandName(CommandType.ADDTASKCOMMENT.name(), app));
        assertNotNull(controller.createCommandFromCommandName(CommandType.LISTALLTASKS.name(), app));
        assertNotNull(controller.createCommandFromCommandName(CommandType.LISTBUGS.name(), app));
        assertNotNull(controller.createCommandFromCommandName(CommandType.LISTSTORIES.name(), app));
        assertNotNull(controller.createCommandFromCommandName(CommandType.LISTFEEDBACKS.name(), app));
        assertNotNull(controller.createCommandFromCommandName(CommandType.LISTTASKSWITHASSIGNEE.name(), app));
        assertThrows(IllegalArgumentException.class, () -> controller.createCommandFromCommandName("name", app));
    }
}