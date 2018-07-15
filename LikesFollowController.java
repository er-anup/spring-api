package org.upgrad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.upgrad.models.User;
import org.upgrad.services.AnswerService;
import org.upgrad.services.FollowService;
import org.upgrad.services.LikeService;
import org.upgrad.services.NotificationService;
import javax.servlet.http.HttpSession;

/*
 * Author - Mananpreet Singh
 * Date - 13 July 2018
 * Description - Rest API Controller for 'Likes' feature.
 */


@RestController
public class LikesFollowController {

    @Autowired
    LikeService likeService;

    @Autowired
    NotificationService notificationService;

    @Autowired
    AnswerService answerService;

    @Autowired
    FollowService followService;

    /* Method to 'like' an 'answer'.
     * Input Param answerId is required.
     * User has to be logged in.
     * User can only 'like' an answer 'once'.
     * The user who wrote the 'liked' 'answer' will be notified.
     */
    @PostMapping("/api/like/{answerId}")
    public ResponseEntity<?> giveLikes(@PathVariable("answerId") int answerId, HttpSession session) {
        if (session.getAttribute("currUser") == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else {
            User user = (User) session.getAttribute("currUser");
            if (likeService.getLikes(user.getId(), answerId) != null) {
                return new ResponseEntity<>("You have already liked this answer!", HttpStatus.CONFLICT);
            } else {
                likeService.giveLikes(answerId, user.getId());
                //Adding a notification to the 'user' who created the 'answer'
                notificationService.addnotification(answerService.findUserIdfromAnswer(answerId), "User with userId"
                        + user.getId() + " has liked your answer with answerId " + answerId);
                return new ResponseEntity<>(" answerId " + answerId + " liked successfully.", HttpStatus.OK);
            }
        }
    }

    /* Method to 'unlike' an 'answer'.
     * Input Param answerId is required.
     * User has to be logged in.
     * User can only 'unlike' answer that they have 'liked'.
     */
    @DeleteMapping("/api/unlike/{answerId}")
    public ResponseEntity<?> unlike(@PathVariable("answerId") int answerId, HttpSession session) {
        User user = (User) session.getAttribute("currUser");
        if (user == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else {
            if (likeService.getLikes(user.getId(), answerId) == null) {
                return new ResponseEntity<>("You have not liked this answer.", HttpStatus.CONFLICT);
            } else {
                likeService.unlike(answerId, user.getId());
                return new ResponseEntity<>("You have unliked answer with answerId " + answerId + " successfully.", HttpStatus.OK);
            }
        }
    }

    /* Method to 'follow' an 'category'.
     * Input Param categoryId is required.
     * User has to be logged in.
     * User can only 'follow' an answer 'once'.
     */
    @PostMapping("/api/follow/{categoryId}")
    public ResponseEntity<?> addFollowCategory(@PathVariable("categoryId") int categoryId, HttpSession session) {
        if (session.getAttribute("currUser") == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else {
            User user = (User) session.getAttribute("currUser");
            if (followService.checkFollows(categoryId, user.getId()) != null) {
                return new ResponseEntity<>("You have already followed this category!", HttpStatus.CONFLICT);
            } else {
                followService.addFollowCategory(categoryId, user.getId());
                return new ResponseEntity<>(" categoryId " + categoryId + " followed successfully.", HttpStatus.OK);
            }
        }
    }


    /* Method to 'unfollow' an 'category'.
     * Input Param categoryId is required.
     * User has to be logged in.
     * User can only 'unfollow' if he/she is already 'follow'ing it.
     */
    @DeleteMapping ("/api/unfollow/{categoryId}")
    public ResponseEntity <?> unFollow (@PathVariable("categoryId") int categoryId, HttpSession session) {
        if (session.getAttribute("currUser") == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else {
            User user = (User) session.getAttribute("currUser");
            if (followService.findUserId(categoryId, user.getId())==null) {
                return new ResponseEntity<>("You are currently not following this category", HttpStatus.CONFLICT);
            } else {
                followService.unFollow(categoryId, user.getId());
                return new ResponseEntity<>(" You have unfollowed the category with categoryId " + categoryId +
                        " successfully.", HttpStatus.OK);
            }

        }
    }
}