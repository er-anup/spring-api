package org.upgrad.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upgrad.models.Answer;
import org.upgrad.repositories.AnswerRepository;
import java.util.List;

@Service
public class AnswerServiceImp implements AnswerService{

    @Autowired
    AnswerRepository answerRepository;

    @Override
    public int addAnswer(String ans, int userId ,int questionId) {
       return answerRepository.addAnswer(ans,userId, questionId) ;
    }

    public Iterable<Answer> getAllAnswersToQuestion(int questionId)
    {
        return answerRepository.getAllAnswersByUserByQuestion(questionId);
    }

    public Iterable<Answer> getAllAnswersByUser(int userId)
    {
        return answerRepository.getAllAnswersByUser(userId);
    }

    public int findUserIdFromAnswer(int answer_id)
    {
        return answerRepository.getUserId(answer_id);
    }

    public void deleteAnswerById(int answer_id)
    {
        answerRepository.deleteAnswerById(answer_id);
    }

    public void editAnswerById(String answer ,int answer_id){
        answerRepository.editAnswerById(answer ,answer_id);
    }

    @Override
    public List<Integer> getAllAnswerId(int questionId) {
        return answerRepository.getAllAnswerId(questionId);
    }

    @Override
    public String getAnswer(int answerId) {
        return answerRepository.getAnswer(answerId);
    }
}
