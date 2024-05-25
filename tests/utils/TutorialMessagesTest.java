package utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TutorialMessagesTest {

    @Test
    void printTaskHelpMsg() {

        assertNotNull(TutorialMessages.printTaskHelpMsg("bug"));
        assertNotNull(TutorialMessages.printTaskHelpMsg("story"));
        assertNotNull(TutorialMessages.printTaskHelpMsg("feedback"));
        assertThrows(IllegalArgumentException.class, () -> TutorialMessages.printTaskHelpMsg("task"));
    }
}