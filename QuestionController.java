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

/*
 * Author - Mananpreet Singh
 * Date - 8 July 2018
 * Description - This class contains rest api corresponding to question related methods.
 */

@RestController
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    /**
     * It requests the parameters of the 'categoryId' to which a question belongs and the question to be added.
     * saves the information of question, the user who created the question, categories to which the question belongs
     * @param categoryId Set of category Ids to which question belongs
     * @param question content of the question
     * @param session HTTP session object
     * @return returns the appropriate JSON response
     */
    @PostMapping("/api/question")
    public ResponseEntity<?> createQuestion(@RequestParam("categoryId") Set<Integer> categoryId, @RequestParam("question") String question, HttpSession session) {

        if (session.getAttribute("currUser")==null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!",HttpStatus.UNAUTHORIZED);
        }
        else {
            User user = (User) session.getAttribute("currUser");
            questionService.addQuestion(question, user.getId(),categoryId);
            return new ResponseEntity<>("Question added successfully.", HttpStatus.OK);
        }
    }

    /**
     * Retreives all questions based on the request parameter categoryId
     * @param categoryId category id for which questions are to be retrieved
     * @param session HTTP session
     * @return returns all questions based on categoryId
     */
    @GetMapping("/api/question/all/{categoryId}")
    public ResponseEntity<?> getAllQuestionsByCategory(@PathVariable("categoryId") int categoryId, HttpSession session) {

        if (session.getAttribute("currUser")==null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!",HttpStatus.UNAUTHORIZED);
        }

        else {
            User user = (User) session.getAttribute("currUser");
            return new ResponseEntity<>(questionService.getAllQuestionsByCategory(categoryId), HttpStatus.OK);
        }
    }

    /**
     * Retreives all questions for the logged in user
     * @param session HTTP session
     * @return returns all questions for the logged in user
     */
    @GetMapping("/api/question/all")
    public ResponseEntity<?> getAllQuestionsByUser(HttpSession session) {

        if (session.getAttribute("currUser")==null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!",HttpStatus.UNAUTHORIZED);
        }

        else {
            User user = (User) session.getAttribute("currUser");
            return new ResponseEntity<>(questionService.getAllQuestionsByUser(user.getId()), HttpStatus.OK);
        }
    }

    /**
     * Deletes a question for the authorized user using parameter question id
     * @param questionId the path variable  'questionId' of data type Integer which has to be deleted.
     * @param session HTTP session
     * @return returns appropriate JSON response
     */
    @DeleteMapping("/api/question/{questionId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable("questionId") int questionId,HttpSession session) {

        if (session.getAttribute("currUser")==null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!",HttpStatus.UNAUTHORIZED);
        }

        else {
            User user = (User) session.getAttribute("currUser");
            int userId = questionService.findUserIdfromQuestion(questionId);
            if(userId == user.getId() || user.getRole().equalsIgnoreCase("admin")) {
                questionService.deleteQuestion(questionId);
                return new ResponseEntity<>("Question with questionId " + questionId + " deleted successfully.", HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>("You do not have rights to delete this question!", HttpStatus.FORBIDDEN);
            }
        }
    }
}
