package org.upgrad.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upgrad.models.User_Profile;
import org.upgrad.repositories.UserProfileRepository;

@Service
public class UserProfileServiceImp implements UserProfileService{

    @Autowired
    UserProfileRepository userProfileRepository;

    @Override
    public User_Profile retrieveUserProfile(int userId) {
        return userProfileRepository.getUserProfileById(userId);
    }
}
