package commands.createCommands;

import commands.BaseCommand;
import core.EngineImpl;
import core.contracts.ApplicationRepository;
import model.CommentImpl;
import model.contracts.Comment;
import model.contracts.tasks.Task;
import utils.ParsingHelpers;
import utils.ValidationHelpers;

import java.util.List;

import static model.CommentImpl.*;
import static utils.TutorialMessages.ADD_COMMENT_TO_TASK_HELP_MSG;

public class AddTaskComment extends BaseCommand {
    private static final int VALID_ARGS_COUNT = 3;
    public AddTaskComment(ApplicationRepository app) {
        super(app);
    }

    @Override
    public void execute() {
        if (EngineImpl.tutorialMode){
            System.out.println(ADD_COMMENT_TO_TASK_HELP_MSG);
        }
        List<String> params = extractParameters();
        ValidationHelpers.validateArgumentsCount(params, VALID_ARGS_COUNT);
        int taskId = ParsingHelpers.tryParseInt(params.get(0));
        String content = params.get(1);
        ValidationHelpers.validateStringLength(content, CONTENT_LEN_MIN, CONTENT_LEN_MAX, CONTENT_LEN_ERR);
        String author = params.get(2);
        app.addCommentToTask(taskId, content, author);

    }
}
