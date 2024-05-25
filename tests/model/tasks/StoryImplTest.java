package model.tasks;

import model.MemberImpl;
import model.contracts.Member;
import model.enums.Priority;
import model.enums.Status;
import model.enums.StorySize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StoryImplTest {
    private static final String VAlID_STORY = "Vladid story";
    private static final String VALID_DESCRIPTION = "Valid description";
    private static final String VALID_SIZE = "Medium";
    private static final String VALID_PRIORITY = "Medium";
    private StoryImpl story;
    private Member member;

    @BeforeEach
    void setUp() {
        story = StoryImpl.createStory(1, VAlID_STORY, VALID_DESCRIPTION, VALID_PRIORITY,
                VALID_SIZE);
        member = new MemberImpl("Janne");
        story.setAssignee(member);
    }

    @Test
    void createStory() {
        assertNotNull(story);
        assertThrows(IllegalArgumentException.class, () -> StoryImpl.createStory(1, "a", VALID_DESCRIPTION, VALID_PRIORITY, VALID_SIZE));
    }

    @Test
    void setStatus() {
        assertEquals(Status.NOTDONE, story.getStatus());
        story.setStatus(Status.DONE.toString());
        assertEquals(Status.DONE, story.getStatus());

    }

    @Test
    void setAssignee() {
        assertSame(member, story.getAssignee());
        Member newmember = new MemberImpl("Janne");
        story.setAssignee(newmember);
        assertSame(newmember, story.getAssignee());
    }

    @Test
    void hasAssignee() {
        assertTrue(story.hasAssignee());
        story.setAssignee(null);
        assertFalse(story.hasAssignee());

    }

    @Test
    void getPriority() {
        assertNotNull(story.getPriority());
        assertEquals(Priority.MEDIUM, story.getPriority());
    }

    @Test
    void getSize() {
        assertNotNull(story.getSize());
        assertEquals(StorySize.MEDIUM, story.getSize());
    }

    @Test
    void changePriority() {
        assertEquals(Priority.MEDIUM, story.getPriority());
        story.changePriority(Priority.HIGH.toString());
        assertEquals(Priority.HIGH, story.getPriority());

    }

    @Test
    void changeSize() {
        assertEquals(StorySize.MEDIUM, story.getSize());
        story.changeSize(StorySize.LARGE.toString());
        assertEquals(StorySize.LARGE, story.getSize());
    }

    @Test
    void getStatus() {
        assertNotNull(story.getStatus());
        assertEquals(Status.NOTDONE, story.getStatus());
    }

    @Test
    void getAssignee() {
        assertNotNull(story.getAssignee());
        assertSame(member, story.getAssignee());
    }

    @Test
    void testToString() {
        assertNotNull(story.toString());
    }

    @Test
    void compareTo() {
        StoryImpl compared = StoryImpl.createStory(1, VAlID_STORY, VALID_DESCRIPTION, VALID_PRIORITY,VALID_SIZE);
        assertEquals(0, story.compareTo(compared));
        compared.setTitle("aaaaaaaaaa");
        assertTrue(story.compareTo(compared) < 0);
        compared.setTitle(VAlID_STORY);
        compared.changePriority("High");
        assertEquals(1, story.compareTo(compared));
        compared.changePriority(VALID_PRIORITY);
        compared.changeSize("Large");
        assertEquals(1, story.compareTo(compared));

    }
}