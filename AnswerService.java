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
    int addAnswer(String ans, int user_id, int question_id) ;
    Iterable<Answer> getAllAnswersToQuestion(int question_id);
    Iterable<Answer> getAllAnswersByUser(int user_id) ;
    int findUserIdfromAnswer(int answer_id);
    void deleteAnswerById(int answer_id);
    void editAnswerById(String answer, int answer_id);
    public List<Integer> getAllAnswerId(int questionId);
    public String getAnswer(int answerId);
}
