package org.upgrad.services;

import org.springframework.stereotype.Service;
import org.upgrad.models.Like;

@Service
public interface LikeService {
    void giveLikes(int answerId, int user_id);
    Like getLikes(int user_id, int answerId);
    void unlike(int answerId, int user_id);
    public int getCount(int answerId);
    Integer getUserId(int answerId,int user_id);
}
