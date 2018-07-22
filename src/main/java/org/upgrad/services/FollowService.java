package org.upgrad.services;

import org.springframework.stereotype.Service;

@Service
public interface FollowService {
    void addFollowCategory(int category_id, int user_id);
    String checkFollows(int category_id, int user_id);
    void unFollow (int category_id, int user_id);
    Integer findUserId(int category_id, int user_id);
}
