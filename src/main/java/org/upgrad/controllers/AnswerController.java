package org.upgrad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.upgrad.models.User;
import org.upgrad.services.AnswerService;
import org.upgrad.services.LikeService;
import org.upgrad.services.NotificationService;
import org.upgrad.services.QuestionService;
import javax.servlet.http.HttpSession;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@RestController
public class AnswerController {
    @Autowired
    private LikeService likeService;

    @Autowired
    private AnswerService answerService ;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private NotificationService notificationService;

    /**
     * @param questionId questionId to answer question
     * @param answer answer description
     * @param session HTTP session for status
     * @return Response entity answer saved in database.
     * */
    @PostMapping("/api/answer")
    public ResponseEntity<?> createAnswer(@RequestParam int questionId, String answer , HttpSession session) {

        if (session.getAttribute("currUser")==null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!",HttpStatus.UNAUTHORIZED);
        }
        else {
            User user = (User) session.getAttribute("currUser");
            answerService.addAnswer(answer,user.getId(),questionId);
            String message =  "User with userId "+ user.getId() + "has answered your question with questionId "+questionId ;
            notificationService.addnotification(user.getId(),message);
            return new ResponseEntity<>("Answer to questionId " + questionId + " added successfully.", HttpStatus.OK);
        }
    }
    
    /**
     * @param answerId answerId of a particular answer to be edited
     * @param answer answer description that needs to be edited
     * @param session HTTP session for status
     * @return Response entity answer saved in database.
     * */
    @PutMapping("/api/answer/{answerId}")
    public ResponseEntity<?> editAnswer(@PathVariable("answerId") int answerId, String answer , HttpSession session) {

        if (session.getAttribute("currUser")==null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!",HttpStatus.UNAUTHORIZED);
        }
        else {
            User user = (User) session.getAttribute("currUser");
            int userId =  answerService.findUserIdfromAnswer(answerId)  ;

            if ( user.getRole().equals("admin")  || user.getId() == userId   ) {
                answerService.editAnswerById(answer, answerId);
                return new ResponseEntity<>(" Answer with answerId " + answerId   +" edited successfully.", HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>("You do not have rights to edit this answer.", HttpStatus.UNAUTHORIZED);
            }
        }
    }

   /**
    * @param questionId questionId for which all answers are returned
    * @param session HTTP session for status
    * @return Response entity list of answers.
    */
    @GetMapping("/api/answer/all/{questionId}")
    public ResponseEntity<?> getAllAnswersToQuestion(@PathVariable("questionId") int questionId, HttpSession session) {

        if (session.getAttribute("currUser")==null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!",HttpStatus.UNAUTHORIZED);
        }
        else {
            User user = (User) session.getAttribute("currUser");
            return new ResponseEntity<>(answerService.getAllAnswersToQuestion(questionId), HttpStatus.OK);
        }
    }

    /**
     * @param session HTTP session for status
     * @return Response entity list of answers.
     */
    @GetMapping("/api/answer/all")
    public ResponseEntity<?> getAllAnswersByUser(HttpSession session) {

        if (session.getAttribute("currUser")==null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!",HttpStatus.UNAUTHORIZED);
        }
        else {
            User user = (User) session.getAttribute("currUser");
            return new ResponseEntity<>(answerService.getAllAnswersByUser(user.getId()), HttpStatus.OK);
        }
    }

    /**
     * @param session HTTP session for status
     * @return Response entity that gives success message after deleting
     */
    @DeleteMapping("/api/answer/{answerId}")
    public ResponseEntity<?> deleteAnswer(@PathVariable("answerId") int answerId, HttpSession session) {

        User user = (User) session.getAttribute("currUser");
        if (user==null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }
        else {
            int userId =  answerService.findUserIdfromAnswer(answerId)  ;

            if ( user.getRole().equals("admin")  || user.getId() == userId   ) {
                answerService.deleteAnswerById(answerId);
                return new ResponseEntity<>(" Answer with answerId "+ answerId  +" deleted successfully.!", HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>("You do not have rights to delete this answer.", HttpStatus.UNAUTHORIZED);
            }
        }
    }

    /**
     * @param session HTTP session for status
     * @param questionId for which all answers to be retrieved.
     * @return Response entity that list of all answers along-with no. of
     */
    @GetMapping("/api/answer/all/likes/{questionId}")
    public ResponseEntity<?> getAllAnswersByLikes(@PathVariable("questionId") int questionId, HttpSession session) {

        if (session.getAttribute("currUser")==null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }
        else {
            Map<String, Integer> DescendingOrder = new TreeMap<String, Integer>();
            List<Integer> answers = answerService.getAllAnswerId(questionId);
            for ( int answerId :answers){
                String answer = answerService.getAnswer(answerId);
                int count = likeService.getCount(answerId);
                DescendingOrder.put(answer,count);
            }
            Map sortedMap = new TreeMap(new ValueComparator(DescendingOrder));
            sortedMap.putAll(DescendingOrder);
            return new ResponseEntity<>(DescendingOrder,HttpStatus.OK);
        }
    }
}

class ValueComparator implements Comparator {
    Map map;

    public ValueComparator(Map map) {
        this.map = map;
    }
    public int compare(Object keyA, Object keyB) {
        Comparable valueA = (Comparable) map.get(keyA);
        Comparable valueB = (Comparable) map.get(keyB);
        return valueB.compareTo(valueA);
    }
}
