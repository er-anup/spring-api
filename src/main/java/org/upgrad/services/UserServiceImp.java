package org.upgrad.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upgrad.models.Question;
import org.upgrad.models.User;
import org.upgrad.models.User_Profile;
import org.upgrad.repositories.UserProfileRepository;
import org.upgrad.repositories.UserRepository;
import java.util.List;

/*
    Author - Mananpreet Singh
    Date - 2 July, 2018
    Description - Implementations of the methods defined in UserService Interface.
    Description - Implementations of the methods defined in UserService Interface.
 */

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    @Override
    public String findUserByUsername(String userName)
    {
        return String.valueOf(userRepository.findUserExist(userName)) ;
    }

    @Override
    public String findUserByEmail(String email)
    {
        return String.valueOf(userRepository.findUserEmail(email)) ;
    }

    @Override
    public Boolean registerUserDetails(User user , User_Profile uf ) {
        Boolean success = false ;

        userRepository.addUserCredentials(user.getUserName(), user.getPassword(), user.getEmail(), "user") ;
        int user_id =  Integer.valueOf(userRepository.findUserId(user.getUserName())) ;
        userProfileRepository.addUserProfileCredentials(user_id,  uf.getFirstName(), uf.getLastName(), uf.getAboutMe(), uf.getDob() , uf.getContactNumber() , uf.getCountry() );
        success = true ;
        return  success ;
    }

    @Override
    public String findUserPassword(String userName)
    {
        return String.valueOf(userRepository.findUserPassword(userName)) ;
    }

    @Override
    public String findUserRole(String userName)
    {
        return String.valueOf(userRepository.findUserRole(userName)) ;
    }

    @Override
    public int findUserId(String userName) {
        return userRepository.findUserId(userName);
    }

    @Override
    public void deleteUserById(int id) { userRepository.deleteUserById(id); }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAllUsers();
    }

    @Override
    public User getUserByUsername(String userName) {
        return userRepository.getUserByUsername(userName);
    }
}
