package org.upgrad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.upgrad.models.User;
import org.upgrad.services.CommentService;
import org.upgrad.services.NotificationService;

import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/api/comment/")
public class CommentController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private CommentService commentService;

    @PostMapping("/")
    public ResponseEntity<?> giveComment(@RequestParam int answerId, String comment, HttpSession session) {

        User user = (User) session.getAttribute("currUser");
        if (user == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else {
            commentService.addComment(comment, user.getId(), answerId);
            String message = "User with userId " + user.getId() + "has commented on your answer with answerId " + answerId;
            notificationService.addNotification(user.getId(), message);
            return new ResponseEntity<>(" answerId " + answerId + " commented successfully.", HttpStatus.OK);
        }
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<?> editComment(@PathVariable("commentId") int commentId, String comment, HttpSession session) {

        User user = (User) session.getAttribute("currUser");
        if (user == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else {
            int userId = commentService.findUserIdFromComment(commentId);

            if (user.getRole().equals("admin") || user.getId() == userId) {
                commentService.editCommentById(comment, commentId);
                return new ResponseEntity<>(" Comment with commentId " + commentId + " edited successfully.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("You do not have rights to edit this comment.", HttpStatus.UNAUTHORIZED);
            }
        }
    }


    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable("commentId") int commentId, HttpSession session) {

        User user = (User) session.getAttribute("currUser");
        if (user == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else {
            int userId = commentService.findUserIdFromComment(commentId);

            if (user.getRole().equals("admin") || user.getId() == userId) {
                commentService.deleteCommentById(commentId);
                return new ResponseEntity<>(" Comment with commentId " + commentId + " deleted successfully.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("You do not have rights to delete this comment!", HttpStatus.UNAUTHORIZED);
            }
        }
    }

    @GetMapping("/all/{answerId}")
    public ResponseEntity<?> getAllCommentsByAnswer(@PathVariable("answerId") int answerId, HttpSession session) {

        User user = (User) session.getAttribute("currUser");
        if (user == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(commentService.getAllComments(answerId), HttpStatus.OK);
        }
    }
}


