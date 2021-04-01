package com.alevel.module3.dao;

import com.alevel.module3.model.Post;
import com.alevel.module3.model.User;

import java.util.List;

public interface PostDao {
    List<Post> findAllPostsByAuthor(User author);

    List<Post> findTopPosts(int limit);

    List<Post> findTopAuthorsPosts(User author, int limit);
}
