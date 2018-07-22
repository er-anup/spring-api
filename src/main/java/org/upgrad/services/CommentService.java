package org.upgrad.services;

import org.springframework.stereotype.Service;
import org.upgrad.models.Comment;

@Service
public interface CommentService {
    public void addComment(String comment, int userId, int answer_id);
    public int findUserIdFromComment(int commentId);
    public void editCommentById(String comment, int commentId);
    public void deleteCommentById(int commentId);
    public Iterable<Comment> getAllComments(int answerId);
}
