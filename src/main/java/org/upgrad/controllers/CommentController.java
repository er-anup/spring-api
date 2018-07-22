package org.upgrad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.upgrad.models.User;
import org.upgrad.services.AnswerService;
import org.upgrad.services.CommentService;
import org.upgrad.services.NotificationService;

import javax.servlet.http.HttpSession;
/*
 * Author - Mananpreet Singh
 * Date - 13 July 2018
 * Description - This class contains rest api corresponding to comment related methods.
 */
@RestController
public class CommentController {
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private CommentService commentService;
    /*adds a comment to the answer
    @param answerId,comment
    its a post request
     */
    @PostMapping("/api/comment")
    public ResponseEntity<?> giveComment(@RequestParam int answerId, String comment, HttpSession session) {

        // Checking if the user is already registered or not
        User user = (User) session.getAttribute("currUser");
        if (user == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else {
            commentService.addComment(comment, user.getId(), answerId);
            String message = "User with userId " + user.getId() + "has commented on your answer with answerId " + answerId;
            notificationService.addnotification(user.getId(), message);
            return new ResponseEntity<>(" answerId " + answerId + " commented successfully.", HttpStatus.OK);
        }
    }
    /*edits comment of an answer
    @pathVariable commentId
    @param comment
    its a put request
     */
    @PutMapping("/api/comment/{commentId}")
    public ResponseEntity<?> editComment(@PathVariable("commentId") int commentId, String comment, HttpSession session) {

        // Checking if the user is already registered or not
        User user = (User) session.getAttribute("currUser");
        if (user == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else {
            int userId = commentService.findUserIdfromComment(commentId);

            if (user.getRole().equals("admin") || user.getId() == userId) {
                //Updating the new answer for that id.
                commentService.editCommentById(comment, commentId);
                return new ResponseEntity<>(" Comment with commentId " + commentId + " edited successfully.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("You do not have rights to edit this comment!", HttpStatus.UNAUTHORIZED);
            }
        }
    }
    /*deletes comment of an answer
    @pathVariable commentId
    its a delete request
     */
    @DeleteMapping("api/comment/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable("commentId") int commentId, HttpSession session) {

        // Checking if the user is already registered or not
        User user = (User) session.getAttribute("currUser");
        if (user == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else {
            int userId = commentService.findUserIdfromComment(commentId);

            if (user.getRole().equals("admin") || user.getId() == userId) {
                //Updating the new answer for that id.
                commentService.deleteCommentById(commentId);
                return new ResponseEntity<>(" Comment with commentId " + commentId + " deleted successfully.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("You do not have rights to delete this comment!", HttpStatus.UNAUTHORIZED);
            }
        }
    }
    /*All comment of an answer
        @pathVariable answerId
        its a get request
         */
    @GetMapping("/api/comment/all/{answerId}")
    public ResponseEntity<?> getAllCommentsByAnswer(@PathVariable("answerId") int answerId, HttpSession session) {

        // Checking if the user is already registered or not
        User user = (User) session.getAttribute("currUser");
        if (user == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else {
            // Returns all answers corresponding to questionId for current user
            return new ResponseEntity<>(commentService.getAllComments(answerId), HttpStatus.OK);
        }
    }
}


