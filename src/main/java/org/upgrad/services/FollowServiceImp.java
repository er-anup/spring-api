package org.upgrad.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upgrad.repositories.FollowRepository;

    /*Author - Mananpreet Singh
    Date Created - 13 July, 2018
    Description - Implementing services for 'Follow' service*/

@Service
public class FollowServiceImp implements FollowService {

    @Autowired
    FollowRepository followRepository;

    @Override
    public void addFollowCategory(int category_id, int user_id) {
        followRepository.addFollowCategory(category_id, user_id);
    }

    @Override
    public String checkFollows (int category_id, int user_id) {
        return followRepository.checkFollows(category_id, user_id);
    }

    @Override
    public void unFollow (int category_id, int user_id) {
        followRepository.unFollow(category_id, user_id);
    }

    @Override
    public Integer findUserId(int category_id, int user_id) {
       return followRepository.findUserId(category_id,user_id);
    }

}
