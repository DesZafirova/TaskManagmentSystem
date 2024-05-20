package commands.listingCommans;

import commands.BaseCommand;
import core.contracts.ApplicationRepository;

public class ShowAllBoards extends BaseCommand {

    public ShowAllBoards(ApplicationRepository app) {
        super(app);
    }


    @Override
    public void execute() {
        app.showAllBoards();
    }
}
