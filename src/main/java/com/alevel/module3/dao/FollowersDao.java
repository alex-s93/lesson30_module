package com.alevel.module3.dao;

import com.alevel.module3.model.User;

import java.util.List;

public interface FollowersDao {
    List<User> findAllFollowers(User author);

}
