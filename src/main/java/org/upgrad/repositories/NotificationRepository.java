package org.upgrad.repositories;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import org.upgrad.models.Notification;
import java.util.List;


public interface NotificationRepository extends CrudRepository<Notification,Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM NOTIFICATION WHERE READ = 'f' AND user_id=?1")
    List<Notification> findNotification(int id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE NOTIFICATION SET READ = 't' WHERE READ = 'f' AND user_id=?1")
    void updateRead(int id);

    @Query(nativeQuery = true, value = "SELECT * FROM NOTIFICATION WHERE user_id=?1")
    List<Notification> findAllNotification(int id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE NOTIFICATION SET READ = 't' WHERE READ = 'f'")
    void updateAllRead();

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="INSERT INTO NOTIFICATION (id,user_id,message,date,read) values (DEFAULT,?1,?2,NOW(),'f')")
    void addNotification(int userId, String message);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="delete from notification where user_id =?1")
    void deleteNotification(int userId);
}
