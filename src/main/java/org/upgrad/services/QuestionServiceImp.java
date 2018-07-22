package org.upgrad.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.upgrad.models.Question;
import org.upgrad.repositories.QuestionRepository;
import java.sql.ResultSet;
import java.util.List;
import java.util.Set;

@Service
public class QuestionServiceImp implements QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Override
    public void addQuestion(String content, int userId,Set<Integer> categoryId) {
        questionRepository.addQuestion(content,userId);
        int questionId = questionRepository.getInsertedId();
        for(Integer catId : categoryId) {
            questionRepository.addCategory(questionId, catId);
        }
    }

    @Override
    public Iterable<Question> getAllQuestionsByCategory(int categoryId) {
        Iterable<Integer> questionId = getQuestionId(categoryId);
        return questionRepository.getQuestionsByQuestionId(questionId);
    }

    @Override
    public Iterable<Question> getAllQuestionsByUser(int userId) {
        return questionRepository.getQuestionsByUser(userId);
    }

    @Override
    public void deleteQuestion(int id) {
        questionRepository.deleteQuestionById(id);
    }

    @Override
    public Iterable<Integer> getQuestionId(int categoryId) {
        return questionRepository.getQuestionId(categoryId);
    }

    @Override
    public int findUserIdFromQuestion(int questionId) {return questionRepository.getUserIdByQuestion(questionId); }

    @Override
    public Iterable<Question> getAllQuestions(){
        return questionRepository.findAll();
    }
}
