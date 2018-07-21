package org.upgrad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.upgrad.models.Question;
import org.upgrad.models.User;
import org.upgrad.services.QuestionService;
import org.upgrad.services.UserService;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Set;

@RestController
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    /**
     * @param categoryId Set of category Ids to which question belongs
     * @param question content of the question
     * @param session HTTP session object
     * @return returns the appropriate JSON response
     */
    @PostMapping("/api/question")
    public ResponseEntity<?> createQuestion(@RequestParam("categoryId") Set<Integer> categoryId, @RequestParam("question") String question, HttpSession session) {

        if (session.getAttribute("currUser")==null) {
            return new ResponseEntity<>("Please Login",HttpStatus.UNAUTHORIZED);
        }
        else {
            User user = (User) session.getAttribute("currUser");
            questionService.addQuestion(question, user.getId(),categoryId);
            return new ResponseEntity<>("Question is added.", HttpStatus.OK);
        }
    }

    /**
     * @param categoryId category id for which questions are to be retrieved
     * @param session HTTP session
     * @return returns all questions based on categoryId
     */
    @GetMapping("/api/question/all/{categoryId}")
    public ResponseEntity<?> getAllQuestionsByCategory(@PathVariable("categoryId") int categoryId, HttpSession session) {

        if (session.getAttribute("currUser")==null) {
            return new ResponseEntity<>("Please Login",HttpStatus.UNAUTHORIZED);
        }

        else {
            User user = (User) session.getAttribute("currUser");
            return new ResponseEntity<>(questionService.getAllQuestionsByCategory(categoryId), HttpStatus.OK);
        }
    }

    /**
     * @param session HTTP session
     * @return returns all questions for the logged in user
     */
    @GetMapping("/api/question/all")
    public ResponseEntity<?> getAllQuestionsByUser(HttpSession session) {

        if (session.getAttribute("currUser")==null) {
            return new ResponseEntity<>("Please Login",HttpStatus.UNAUTHORIZED);
        }

        else {
            User user = (User) session.getAttribute("currUser");
            return new ResponseEntity<>(questionService.getAllQuestionsByUser(user.getId()), HttpStatus.OK);
        }
    }

    /**
     * @param questionId the path variable  'questionId' of data type Integer which has to be deleted.
     * @param session HTTP session
     * @return returns appropriate JSON response
     */
    @DeleteMapping("/api/question/{questionId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable("questionId") int questionId,HttpSession session) {

        if (session.getAttribute("currUser")==null) {
            return new ResponseEntity<>("Please Login",HttpStatus.UNAUTHORIZED);
        }

        else {
            User user = (User) session.getAttribute("currUser");
            int userId = questionService.findUserIdfromQuestion(questionId);
            if(userId == user.getId() || user.getRole().equalsIgnoreCase("admin")) {
                questionService.deleteQuestion(questionId);
                return new ResponseEntity<>("Question and it'sId " + questionId + " deleted.", HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>("You can't delete this question!", HttpStatus.FORBIDDEN);
            }
        }
    }
}
