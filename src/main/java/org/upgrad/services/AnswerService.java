package org.upgrad.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upgrad.models.Answer;
import org.upgrad.models.Question;
import org.upgrad.repositories.AnswerRepository;
import org.upgrad.repositories.QuestionRepository;
import java.util.List;
import java.util.Set;

@Service
public interface AnswerService {

    int addAnswer(String ans, int userId ,int questionId) ;
    Iterable<Answer> getAllAnswersToQuestion(int questionId);
    Iterable<Answer> getAllAnswersByUser(int userId) ;
    int findUserIdFromAnswer(int answer_id);
    void deleteAnswerById(int answer_id);
    void editAnswerById(String answer , int answer_id);
    List<Integer> getAllAnswerId(int questionId);
    String getAnswer(int answerId);
    
}
