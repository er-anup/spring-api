package org.upgrad.services;

import org.springframework.stereotype.Service;
import org.upgrad.models.User;
import org.upgrad.models.User_Profile;
import java.util.List;

@Service
public interface UserService {

    public String findUserByUsername(String userName);

    public String findUserByEmail(String email);

    public String findUserPassword(String userName);

    public String findUserRole(String userName);

    public int findUserId(String userName);

    public Boolean registerUserDetails(User user, User_Profile uf);

    public void deleteUserById(int id);

    public List<User> getAllUsers();

    public User getUserByUsername(String userName);

}
