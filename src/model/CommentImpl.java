package model;

import model.contracts.Comment;
import utils.ValidationHelpers;

import java.lang.reflect.Member;

import static java.lang.String.format;

public class CommentImpl implements Comment {
    public static final int CONTENT_LEN_MIN = 3;
    public static final int CONTENT_LEN_MAX = 200;
    public static final String CONTENT_LEN_ERR = format(
            "Content must be between %d and %d characters long.",
            CONTENT_LEN_MIN,
            CONTENT_LEN_MAX);
    //todo should author be a Member?
    private String author;
    private String content;

    public CommentImpl(String author, String content) {
        this.author = author;
        this.setContent(content);
    }

    private void setContent(String content) {
        ValidationHelpers.validateStringLength(content, CONTENT_LEN_MIN, CONTENT_LEN_MAX, CONTENT_LEN_ERR);
        this.content = content;
    }

    @Override
    public String getContent() {
        return this.content;
    }

    @Override
    public String getAuthor() {
        return this.author;
    }
}
