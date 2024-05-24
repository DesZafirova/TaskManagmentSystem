package model;

import model.contracts.Comment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommentImplTest {
    private Comment comment;


    @BeforeEach
    void beforeEach() {
        comment = new CommentImpl("Valid author", "Valid content");
    }

    @Test
    void getContent() {
        String result = comment.getContent();
        assertNotNull(result);
        assertEquals("Valid content", result);
    }

    @Test
    void getAuthor() {
        String author = comment.getAuthor();
        assertNotNull(author);
        assertEquals("Valid author", author);
    }
    @Test
    void createCommentWithValidParameters(){
        assertDoesNotThrow(() -> new CommentImpl("Valid author", "Valid content"));
        assertNotNull(comment);
    }
    @Test
    void createCommentWithInvalidParametersThrowsException(){
        assertThrows(IllegalArgumentException.class,
                () -> new CommentImpl("valid", "a".repeat(201)));
        assertThrows(IllegalArgumentException.class,
                () -> new CommentImpl("valid", "a".repeat(2)));
    }
}