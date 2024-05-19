package model;

import model.contracts.Board;
import model.contracts.Member;
import model.contracts.Team;
import model.contracts.tasks.Task;
import model.tasks.FeedbackImpl;
import utils.ValidationHelpers;

public class BoardImpl extends EntityImpl implements Board {
    private static final int NAME_MIN_LENGTH = 3;
    private static final int NAME_MAX_LENGTH = 10;
    private static final String NAME_ERROR_MESSAGE = String.format("Board name must be between %d and %d characters.",
            NAME_MIN_LENGTH, NAME_MAX_LENGTH);

    private final Team team;

    public BoardImpl(String name, Team team) {
        super(name);
        this.team = team;

    }


    @Override
    protected void validateName(String name) {
        ValidationHelpers.validateStringLength(name, NAME_MIN_LENGTH, NAME_MAX_LENGTH, NAME_ERROR_MESSAGE);
    }


    @Override
    public String getNameWithTeam() {
        return """
                Board: %s [Team: %s]
                """.formatted(this.name, this.team.getName());
    }
}
