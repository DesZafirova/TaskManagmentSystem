package model;

import model.contracts.Member;
import model.contracts.tasks.AssignableTask;
import model.tasks.StoryImpl;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class MemberImplTest {

    private static MemberImpl member;
    private AssignableTask task;

    @BeforeEach
    void beforeEach() {
        member = new MemberImpl("testMember");
        task = StoryImpl.createStory(1, "test Title", "test Description", "High", "Medium");
        member.addTask(task);
    }


    @Test
    void setName() {
        assertDoesNotThrow(() -> member.setName("valid"));
        assertEquals("valid", member.getName());
    }

    @Test
    public void validateName() {
        assertEquals("testMember", member.getName());
        assertDoesNotThrow(() -> member.setName("valid"));
        assertEquals("valid", member.getName());
        assertThrows(IllegalArgumentException.class, () -> member.setName("a"));
        assertThrows(IllegalArgumentException.class, () -> member.setName("a".repeat(16)));
    }

    @Test
    public void testToString() {
        List<String> list = Arrays.stream(member.toString().split("\n")).toList();
        assertEquals(10, list.size());
        String result = """
                %s: %s
                Tasks: 
                %s
                =================
                History: 
                %s
                """.formatted(member.getRealName(),
                member.getName(),
                member.displayAllTasks(),
                member.displayHistory());
        assertEquals(result, member.toString());

    }


}