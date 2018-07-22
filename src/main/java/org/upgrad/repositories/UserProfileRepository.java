package org.upgrad.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.upgrad.models.UserProfile;

import java.util.Date;

@Repository
public interface UserProfileRepository extends CrudRepository<UserProfile, Integer> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="INSERT INTO USER_PROFILE (user_id,firstName,lastName,aboutMe,dob,contactNumber,country) VALUES (?1,?2,?3,?4,?5,?6,?7)")
    void addUserProfileCredentials(int user_id, String firstName, String lastName, String aboutMe , Date dob, String contactNumber, String country);

    @Query(nativeQuery = true,value="select * from USER_PROFILE where user_id=?1")
    UserProfile getUserProfileById(int userId);

}
