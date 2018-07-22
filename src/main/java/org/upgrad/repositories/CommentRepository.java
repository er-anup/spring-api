package org.upgrad.repositories;

import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.upgrad.models.Comment;

import javax.transaction.Transactional;
/*
    Author - Mananpreet Singh
    Date - 13 July, 2018
    Description - Repository that contains CRUD operations for Comment table
 */

public interface CommentRepository extends CrudRepository<Comment,Integer> {
    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="INSERT INTO COMMENT(content,date,user_id,answer_id,modifiedon) VALUES (?1,NOW(),?2,?3,NOW())")
    void addComment(String comment, int userId, int answerId);

    @Query(nativeQuery = true,value="select user_id from comment where id = ?1")
    int getUserId(int id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="UPDATE comment SET content = ?1  WHERE id = ?2")
    void editCommentById(String comment, int commentId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="delete from comment where id=?1")
    void deleteCommentById(int commentId);

    @Query(nativeQuery = true,value="select * from comment where answer_id=?1")
    Iterable<Comment> getAllComments(int answerId);
}
