package org.upgrad.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upgrad.models.Like;
import org.upgrad.repositories.LikesRepository;

@Service
public class LikeServiceImp implements LikeService {

    @Autowired
    LikesRepository likesRepository;

    @Override
    public void giveLikes(int answerId, int user_id) {
        likesRepository.giveLikes(answerId, user_id);
    }

    @Override
    public Like getLikes (int user_id,int answerId) {
        return likesRepository.checkLikes(answerId, user_id);
    }

    @Override
    public void unlike(int answerId, int user_id){
        likesRepository.unlike(answerId, user_id);
    }

    @Override
    public int getCount(int answerId) {
        return likesRepository.getCount(answerId);
    }

    @Override
    public Integer getUserId(int answerId, int user_id) {
        return likesRepository.getUserId(answerId, user_id);
    }
}
