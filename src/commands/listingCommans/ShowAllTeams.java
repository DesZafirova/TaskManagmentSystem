package commands.listingCommans;

import commands.BaseCommand;
import core.contracts.ApplicationRepository;

import java.util.List;

public class ShowAllTeams extends BaseCommand {
    public ShowAllTeams(ApplicationRepository app) {
        super(app);
    }


    @Override
    public void execute() {
        app.showAllTeams();
    }
}
