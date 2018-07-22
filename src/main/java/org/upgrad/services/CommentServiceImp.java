package org.upgrad.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upgrad.models.Comment;
import org.upgrad.repositories.CommentRepository;

@Service
public class CommentServiceImp implements CommentService {

    @Autowired
    private CommentRepository commentRepository;


    @Override
    public void addComment(String comment, int userId, int answer_id) {
        commentRepository.addComment(comment,userId,answer_id);
    }

    @Override
    public int findUserIdFromComment(int commentId) {
        return commentRepository.getUserId(commentId);
    }

    @Override
    public void editCommentById(String comment, int commentId) {
        commentRepository.editCommentById(comment,commentId);
    }

    @Override
    public void deleteCommentById(int commentId) {
        commentRepository.deleteCommentById(commentId);
    }

    @Override
    public Iterable<Comment> getAllComments(int answerId) {
        return commentRepository.getAllComments(answerId);
    }
}
