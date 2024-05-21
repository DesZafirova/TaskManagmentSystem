package commands.EngineCommands;

import commands.BaseCommand;
import core.contracts.ApplicationRepository;

import static commands.enums.CommandType.listCommands;

public class ListCommandsCommand extends BaseCommand {
    public ListCommandsCommand(ApplicationRepository app) {
        super(app);
    }

    @Override
    public void execute() {
        listCommands();
    }
}
