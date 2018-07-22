package org.upgrad.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upgrad.model.UserProfile;
import org.upgrad.repository.UserProfileRepository;

@Service
public class UserProfileServiceImp implements UserProfileService {

    @Autowired
    UserProfileRepository userProfileRepository;

    @Override
    public UserProfile retrieveUserProfile(int userId) {
        return userProfileRepository.getUserProfileById(userId);
    }

}
