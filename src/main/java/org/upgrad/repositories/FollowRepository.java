package org.upgrad.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.upgrad.models.Like;
import javax.transaction.Transactional;

@Repository
public interface FollowRepository extends CrudRepository<Like, Integer> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="insert into follow (category_id, user_id) values (?1, ?2)")
    void addFollowCategory  (int category_id, int user_id);

    @Query(nativeQuery = true,value="select * from follow where category_id=?1 and user_id=?2")
    String checkFollows (int category_id, int user_id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="DELETE FROM follow where category_id=?1 and user_id=?2")
    void unFollow  (int category_id, int user_id);

    @Query(nativeQuery = true,value="select user_id from follow where category_id=?1 and user_id=?2")
    Integer findUserId (int category_id, int user_id);
}
