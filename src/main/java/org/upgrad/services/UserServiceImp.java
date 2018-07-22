package org.upgrad.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upgrad.models.User;
import org.upgrad.models.UserProfile;
import org.upgrad.repositories.UserProfileRepository;
import org.upgrad.repositories.UserRepository;
import java.util.List;

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
    public Boolean registerUserDetails(User user , UserProfile userProfile ) {

        Boolean success = false ;

        userRepository.addUserCredentials(user.getUserName(), user.getPassword(), user.getEmail(), "user") ;
        int userId =  Integer.valueOf(userRepository.findUserId(user.getUserName())) ;
        userProfileRepository.addUserProfileCredentials(userId,  userProfile.getFirstName(), userProfile.getLastName(), userProfile.getAboutMe(), userProfile.getDob() , userProfile.getContactNumber() , userProfile.getCountry() );
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
