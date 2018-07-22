package org.upgrad.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.upgrad.models.Answer;
import org.upgrad.models.Question;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface AnswerRepository extends CrudRepository<Answer,Integer> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="insert into ANSWER (ans,date,user_id,question_id,modifiedOn) values (?1,NOW(),?2,?3,NOW())")
    int addAnswer( String ans, int userId , int questionId );

    @Query(nativeQuery = true,value="select * from answer where question_id=?1")
    Iterable<Answer> getAllAnswersByUserByQuestion(int questionId);

    @Query(nativeQuery = true,value="select * from answer where user_id=?1")
    Iterable<Answer> getAllAnswersByUser(int userId);

    @Query(nativeQuery = true,value="select user_id from answer where id = ?1")
    int getUserId(int id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="delete from answer where id=?1 ")
    int deleteAnswerById(int id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="UPDATE answer SET ans = ?1 , modifiedon = NOW() WHERE id = ?2")
    int editAnswerById(String ans, int id);

    @Query(nativeQuery = true,value="select id from answer where question_id=?1")
    List<Integer> getAllAnswerId(int questionId);

    @Query(nativeQuery = true,value="select * from answer where id=?1")
    String getAnswer(int answerId);
}
