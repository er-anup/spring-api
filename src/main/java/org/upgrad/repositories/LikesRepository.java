package org.upgrad.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.upgrad.models.Like;
import org.upgrad.models.Question;
import javax.transaction.Transactional;

@Repository
public interface LikesRepository extends CrudRepository<Like, Integer> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="insert into likes (answer_id, user_id) values (?1, ?2)")
    void giveLikes (int answerId, int user_id);

    @Query(nativeQuery = true,value="select * from likes where answer_id=?1 and user_id=?2")
    Like checkLikes (int answerId, int user_id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="delete from likes where answer_id=?1 and user_id=?2")
    void unlike (int answerId, int user_id);

    @Query(nativeQuery = true,value = "select count(*) from likes where answer_id = ?1 ")
    int getCount(int answerId);

    @Query(nativeQuery = true,value="select user_id from likes where answer_id=?1 and user_id=?2")
    Integer getUserId (int answerId, int user_id);
}
