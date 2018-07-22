package org.upgrad.services;

import org.upgrad.models.User;
import org.upgrad.models.UserProfile;
import java.util.List;

public interface UserService {

    String findUserByUsername(String userName);

    String findUserByEmail(String email);

    String findUserPassword(String userName);

    String findUserRole(String userName);

    int findUserId(String userName);

    Boolean registerUserDetails(User user, UserProfile userProfile);

    void deleteUserById(int id);

    List<User> getAllUsers();

    User getUserByUsername(String userName);

}
