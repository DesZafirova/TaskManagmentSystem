package core.contracts;

import commands.contracts.Command;

public interface CommandController {

    Command createCommandFromCommandName(String commandTypeAsString, ApplicationRepository applicationRepository);

}
