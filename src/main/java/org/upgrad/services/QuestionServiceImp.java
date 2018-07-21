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
    public void addQuestion(String content, int user_id,Set<Integer> categoryId) {
        questionRepository.addQuestion(content,user_id);
        int question_id = questionRepository.getInsertedId();
        for(Integer catId : categoryId) {
            questionRepository.addCategory(question_id, catId);
        }
    }

    @Override
    public Iterable<Question> getAllQuestionsByCategory(int categoryId) {
        Iterable<Integer> questionId = getQuestionId(categoryId);
        return questionRepository.getQuestionsByQuestionId(questionId);
    }

    @Override
    public Iterable<Question> getAllQuestionsByUser(int user_id) {
        return questionRepository.getQuestionsByUser(user_id);
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
    public int findUserIdfromQuestion(int questionId) {return questionRepository.getUserIdByQuestion(questionId); }

    @Override
    public Iterable<Question> getAllQuestions(){
        return questionRepository.findAll();
    }
}
