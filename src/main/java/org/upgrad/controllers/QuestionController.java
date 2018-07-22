package org.upgrad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.upgrad.models.User;
import org.upgrad.services.QuestionService;

import javax.servlet.http.HttpSession;
import java.util.Set;

@RestController
@RequestMapping("/api/question/")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping("/")
    public ResponseEntity<?> addQuestion(@RequestParam("categoryId") Set<Integer> categoryId, @RequestParam("question") String question, HttpSession session) {

        if (session.getAttribute("currUser")==null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!",HttpStatus.UNAUTHORIZED);
        }
        else {
            User user = (User) session.getAttribute("currUser");
            questionService.addQuestion(question, user.getId(),categoryId);
            return new ResponseEntity<>("Question added successfully.", HttpStatus.OK);
        }
    }

    @GetMapping("/all/{categoryId}")
    public ResponseEntity<?> getAllQuestionsByCategory(@PathVariable("categoryId") int categoryId, HttpSession session) {

        if (session.getAttribute("currUser")==null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!",HttpStatus.UNAUTHORIZED);
        }

        else {
            User user = (User) session.getAttribute("currUser");
            return new ResponseEntity<>(questionService.getAllQuestionsByCategory(categoryId), HttpStatus.OK);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllQuestionsByUser(HttpSession session) {

        if (session.getAttribute("currUser")== null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!",HttpStatus.UNAUTHORIZED);
        }

        else {
            User user = (User) session.getAttribute("currUser");
            return new ResponseEntity<>(questionService.getAllQuestionsByUser(user.getId()), HttpStatus.OK);
        }
    }


    @DeleteMapping("/{questionId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable("questionId") int questionId,HttpSession session) {

        if (session.getAttribute("currUser")==null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!",HttpStatus.UNAUTHORIZED);
        }

        else {
            User user = (User) session.getAttribute("currUser");
            int userId = questionService.findUserIdFromQuestion(questionId);
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
