package commands.enums;

import static utils.FormattingHelpers.RESET;
import static utils.FormattingHelpers.colorCode;

public enum CommandType {
    LC,
    TUTORIAL,
    SETCOLOR,
    CREATEMEMBER,
    SHOWALLMEMBERS,
    SHOWMEMBERACTIVITY,
    CREATETEAM,
    SHOWTEAMS,
    SHOWTEAMACTIVITY,
    ADDMEMBERTOTEAM,
    SHOWTEAMMEMBERS,
    CREATEBOARDINTEAM,
    SHOWALLTEAMBOARDS,
    SHOWBOARDACTIVITY,
    CREATETASKINBOARD,
    CHANGEBUGDETAILS,
    CHANGESTORYDETAILS,
    CHANGEFEEDBACKDETAILS,
    ASSIGNTASK,
    UNAASIGNTASK,
    ADDTASKCOMMENT,
    LISTALLTASKS;


    public static void listCommands() {
        for (CommandType type : CommandType.values()) {
            System.out.printf("%s%s%s%n".formatted(colorCode,type.name(), RESET));
             try {
                Thread.sleep(250);//280
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();

            }
        }

    }

}
