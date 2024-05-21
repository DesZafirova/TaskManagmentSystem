package utils;


import java.math.BigDecimal;

import static commands.enums.CommandType.listCommands;
import static core.EngineImpl.sc;

public class FormattingHelpers {
    private static final String[] WelcomeMessage = {
            "Hello and welcome to our Task Management App.",
            "A few things before you get started:",
            " -you can manually turn off the instructions by using the \"tutorial\" command",
            " -all commands provide instruction on required information when executed.",
            " -this app is not case sensitive.",
            " -you can list all possible commands with the \"lc\" command.",
            " -you can change the color theme of the assisting messages with the \"setcolor\" command",
            "Enjoy!"
    };
    public static final String RESET = "\033[0m";

    public static final String RED = "\033[31m";
    public static final String GREEN = "\033[32m";
    public static final String YELLOW = "\033[33m";
    public static final String BLUE = "\033[34m";
    public static final String PURPLE = "\033[35m";
    public static final String CYAN = "\033[36m";
    public static final String WHITE = "\033[37m";

    public static String colorCode = PURPLE;

    public static void setColor() {


        System.out.println("Please select the number of the color code:");
        System.out.println("Or type the color name (red, green, yellow, blue, purple, cyan, white)");
        System.out.println("This affect only Tutorial Mode");
        System.out.println("1. Red");
        System.out.println("2. Green");
        System.out.println("3. Yellow");
        System.out.println("4. Blue");
        System.out.println("5. Purple");
        System.out.println("6. Cyan");
        System.out.println("7. White");

        String input = sc.nextLine().trim().toLowerCase();

        switch (input) {
            case "1":
            case "red":
                colorCode = RED;
                break;
            case "2":
            case "green":
                colorCode = GREEN;
                break;
            case "3":
            case "yellow":
                colorCode = YELLOW;
                break;
            case "4":
            case "blue":
                colorCode = BLUE;
                break;
            case "5":
            case "purple":
                colorCode = PURPLE;
                break;
            case "6":
            case "cyan":
                colorCode = CYAN;
                break;
            case "7":
            case "white":
                colorCode = WHITE;
                break;
            default:
                System.out.println("Not a valid choice");
                break;
        }
        System.out.printf("Color code is set to : %s", "%sCOLOR%s%n".formatted(colorCode, RESET));
    }


    public static String removeTrailingZerosFromDouble(double number) {
        BigDecimal num = BigDecimal.valueOf(number).stripTrailingZeros();
        return num.toPlainString();
    }

    public static void welcome() {
        for (String s : WelcomeMessage) {
            System.out.println(s);
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();

            }

        }
        System.out.println();
            listCommands();
    }
}
