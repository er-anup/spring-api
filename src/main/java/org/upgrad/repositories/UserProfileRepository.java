package org.upgrad.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.upgrad.models.User_Profile;

import java.util.Date;

/*
    Author - Mananpreet Singh
    Date - 2 July, 2018
    Description - Repository that contains CRUD operations for User_profile table
 */

@Repository
public interface UserProfileRepository extends CrudRepository<User_Profile, Integer> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="INSERT INTO USER_PROFILE (user_id,firstName,lastName,aboutMe,dob,contactNumber,country) VALUES (?1,?2,?3,?4,?5, ?6, ?7)")
    void addUserProfileCredentials(int user_id, String firstName, String lastName, String aboutMe, Date dob, String contactNumber, String country);

    @Query(nativeQuery = true,value="select * from USER_PROFILE where user_id=?1")
    User_Profile getUserProfileById(int user_id);

}
