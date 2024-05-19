package model.contracts.utils;

import model.contracts.Comment;

import java.util.List;

public interface Commentable {

    List<Comment> getComments();

    void addComment(Comment comment);
}
