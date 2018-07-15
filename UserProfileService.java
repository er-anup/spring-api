package org.upgrad.services;

import org.springframework.stereotype.Service;
import org.upgrad.models.User_Profile;

@Service
public interface UserProfileService {

    public User_Profile retrieveUserProfile(int userId);

}
