package org.upgrad.repositories;

import org.upgrad.models.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, String> {


    @Query(nativeQuery = true,value="SELECT userName FROM USERS WHERE UPPER(USERNAME) = UPPER (?1) ")
    String findUserExist(String userName);

    @Query(nativeQuery = true,value="SELECT email FROM USERS WHERE UPPER(EMAIL) = UPPER (?1) ")
    String findUserEmail(String email);

    @Query(nativeQuery = true,value="SELECT id FROM USERS WHERE UPPER(USERNAME) = UPPER (?1) ")
    int findUserId(String userName);

    @org.springframework.transaction.annotation.Transactional
    @Modifying
    @Query(nativeQuery = true,value="INSERT INTO USERS (userName,password,email,role) VALUES (?1,?2,?3,?4)")
    void addUserCredentials(String uname,String password,String email, String role );

    @Query(nativeQuery = true,value="SELECT password FROM USERS WHERE UPPER(USERNAME) = UPPER (?1) ")
    String findUserPassword(String userName);

    @Query(nativeQuery = true,value="SELECT role FROM USERS WHERE UPPER(USERNAME) = UPPER (?1) ")
    String findUserRole(String userName);

    @org.springframework.transaction.annotation.Transactional
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM users WHERE id = ?1")
    void deleteUserById(int id);

    @Query(nativeQuery = true,value="SELECT * FROM USERS ")
    List<User> findAllUsers();

    @Query(nativeQuery = true,value="select * from USERS where username=?1 ")
    User getUserByUsername(String userName);
}
