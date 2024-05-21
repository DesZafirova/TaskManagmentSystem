package commands.EngineCommands;

import commands.BaseCommand;
import core.contracts.ApplicationRepository;

import static utils.FormattingHelpers.setColor;

public class ChangeColorCommand extends BaseCommand {
    public ChangeColorCommand(ApplicationRepository app) {
        super(app);
    }

    @Override
    public void execute() {
        setColor();
    }
}
