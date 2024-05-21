package core;

import commands.contracts.Command;
import core.contracts.ApplicationRepository;
import core.contracts.CommandController;
import core.contracts.Engine;

import java.util.Scanner;

import static utils.FormattingHelpers.welcome;


public class EngineImpl implements Engine {
    private static final String EMPTY_LINE_MSG = "Command line is empty, care to try again?";
    private static final String EXIT_COMMAND = "Exit";
    public static final Scanner sc = new Scanner(System.in);
    private final ApplicationRepository app;
    private final CommandController commandController;
    public static boolean tutorialMode = true;

    public EngineImpl() {
        this.commandController = new CommandControllerImpl();
        this.app = new ApplicationRepositoryImpl();
    }

    @Override
    public void run() {
       // welcome();
        Scanner scanner = new Scanner(System.in);
        String[] arr = {"some title", "some description", "4"};
        String[] arr1 = {"some title chavo", "some description", "high", "small"};
        app.createTeam("chavo1");
        app.createNewBoardInTeam("chavo1", "chavo1");
        app.createNewTaskInBoard("feedback", "chavo1", "chavo1", arr);
        app.createNewTaskInBoard("story", "chavo1", "chavo1", arr1);
        app.listAllTasks();
        while (true) {
            try {
                String inputLine = scanner.nextLine();
                if (inputLine.isBlank()) {
                    System.out.println(EMPTY_LINE_MSG);
                    continue;
                } else if (inputLine.equalsIgnoreCase(EXIT_COMMAND)) {
                    System.out.printf("Thanks for using our Tool.%nGoodbye!%n");
                    break;
                }
                processCommand(inputLine);
            } catch (Exception ex) {
                if (ex.getMessage() != null && !ex.getMessage().isEmpty()) {
                    System.out.println(ex.getMessage());
                } else {
                    System.out.println(ex);
                }
            }
        }
    }

    private void processCommand(String inputLine) {
        String command = inputLine.trim();
        Command cmd = commandController.createCommandFromCommandName(command, app);
        cmd.execute();
    }
}
