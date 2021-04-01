package com.alevel.module3.dao;

import com.alevel.module3.model.User;

import java.util.List;

public interface AuthorDao {
    List<User> findAllAuthors();

    List<User> findTopAuthors(int limit);
}
