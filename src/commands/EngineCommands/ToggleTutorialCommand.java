package commands.EngineCommands;

import commands.BaseCommand;
import core.contracts.ApplicationRepository;

import static core.EngineImpl.tutorialMode;

public class ToggleTutorialCommand extends BaseCommand {
    public ToggleTutorialCommand(ApplicationRepository app) {
        super(app);
    }

    @Override
    public void execute() {
        toggleTutorialMode();

    }
    public void toggleTutorialMode(){
        tutorialMode = !tutorialMode;
                    if (tutorialMode) {
                        System.out.println("Tutorial mode is ON.");
                    } else {
                        System.out.println("Tutorial mode is OFF.");
                    }
    }
}
