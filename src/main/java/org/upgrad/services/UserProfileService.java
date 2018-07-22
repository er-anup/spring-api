package org.upgrad.services;

import org.springframework.stereotype.Service;
import org.upgrad.model.UserProfile;

@Service
public interface UserProfileService {

    UserProfile retrieveUserProfile(int userId);

}
