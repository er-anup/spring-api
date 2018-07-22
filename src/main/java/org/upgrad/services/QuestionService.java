package org.upgrad.services;

import org.springframework.stereotype.Service;
import org.upgrad.models.Question;
import java.util.List;
import java.util.Set;

@Service
public interface QuestionService {

    void addQuestion(String content, int userId, Set<Integer> categoryId);
    Iterable<Question> getAllQuestionsByCategory(int categoryId);
    Iterable<Question> getAllQuestionsByUser(int userId);
    void deleteQuestion(int id);
    Iterable<Integer> getQuestionId(int categoryId);
    int findUserIdFromQuestion(int questionId);
    Iterable<Question> getAllQuestions();
}
