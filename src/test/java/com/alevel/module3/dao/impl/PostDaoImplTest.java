package com.alevel.module3.dao.impl;

import com.alevel.module3.model.Post;
import com.alevel.module3.model.PostStatus;
import com.alevel.module3.model.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PostDaoImplTest {
    private PostDaoImpl postDao = new PostDaoImpl();
    private UserDaoImpl userDao = new UserDaoImpl();

    @Test
    void create() {
        User user = new User();
        user.setAge(19);
        user.setEmail(TestUtils.getData().get("email"));
        user.setFullName(TestUtils.getData().get("fullName"));
        user.setLogin(TestUtils.getData().get("login"));
        userDao.create(user);

        Post post = new Post();
        post.setAuthor(user);
        post.setTitle("Test title");
        post.setContent("Test content length  more than 1000 :)");
        post.setRating(0);
        post.setStatus(PostStatus.DRAFT);
        postDao.create(post);

        assertEquals(1, postDao.findAllPostsByAuthor(user).size());
    }

    @Test
    void findAllPostsByAuthor() {
        User userOne = new User();
        userOne.setAge(19);
        userOne.setEmail(TestUtils.getData().get("email"));
        userOne.setFullName(TestUtils.getData().get("fullName"));
        userOne.setLogin(TestUtils.getData().get("login"));
        userDao.create(userOne);

        User userTwo = new User();
        userTwo.setAge(20);
        userTwo.setEmail(TestUtils.getData().get("email"));
        userTwo.setFullName(TestUtils.getData().get("fullName"));
        userTwo.setLogin(TestUtils.getData().get("login"));
        userDao.create(userTwo);

        Post post1 = new Post();
        post1.setAuthor(userOne);
        post1.setTitle("Test title 1");
        post1.setContent("Test content 1 length  more than 1000 :)");
        post1.setRating(1);
        post1.setStatus(PostStatus.DRAFT);
        postDao.create(post1);

        Post post2 = new Post();
        post2.setAuthor(userTwo);
        post2.setTitle("Test title 2");
        post2.setContent("Test content 2 length  more than 1000 :)");
        post2.setRating(2);
        post2.setStatus(PostStatus.IN_PROGRESS);
        postDao.create(post2);

        Post post3 = new Post();
        post3.setAuthor(userOne);
        post3.setTitle("Test title 3");
        post3.setContent("Test content 3 length  more than 1000 :)");
        post3.setRating(3);
        post3.setStatus(PostStatus.PUBLISHED);
        postDao.create(post3);

        List<Post> postsUserOne = postDao.findAllPostsByAuthor(userOne);
        List<Post> postsUserTwo = postDao.findAllPostsByAuthor(userTwo);

        assertEquals(postsUserOne.size(), 2);
        assertEquals(postsUserOne.get(1).getTitle(), "Test title 3");

        assertEquals(postsUserTwo.size(), 1);
        assertEquals(postsUserTwo.get(0).getStatus(), PostStatus.IN_PROGRESS);
    }

    @Test
    void findTopPosts() {
        User userOne = new User();
        userOne.setAge(19);
        userOne.setEmail(TestUtils.getData().get("email"));
        userOne.setFullName(TestUtils.getData().get("fullName"));
        userOne.setLogin(TestUtils.getData().get("login"));
        userDao.create(userOne);

        Post post1 = new Post();
        post1.setAuthor(userOne);
        post1.setTitle("Test title 1");
        post1.setContent("Test content 1 length  more than 1000 :)");
        post1.setRating(1);
        post1.setStatus(PostStatus.DRAFT);
        postDao.create(post1);

        Post post2 = new Post();
        post2.setAuthor(userOne);
        post2.setTitle("Test title 2");
        post2.setContent("Test content 2 length  more than 1000 :)");
        post2.setRating(2);
        post2.setStatus(PostStatus.IN_PROGRESS);
        postDao.create(post2);

        Post post3 = new Post();
        post3.setAuthor(userOne);
        post3.setTitle("Test title 3");
        post3.setContent("Test content 3 length  more than 1000 :)");
        post3.setRating(3);
        post3.setStatus(PostStatus.PUBLISHED);
        postDao.create(post3);

        assertEquals(postDao.findTopPosts(1).get(0).getRating(), 3);
    }

    @Test
    void findTopAuthorsPosts() {
        User userOne = new User();
        userOne.setAge(19);
        userOne.setEmail(TestUtils.getData().get("email"));
        userOne.setFullName(TestUtils.getData().get("fullName"));
        userOne.setLogin(TestUtils.getData().get("login"));
        userDao.create(userOne);

        User userTwo = new User();
        userTwo.setAge(20);
        userTwo.setEmail(TestUtils.getData().get("email"));
        userTwo.setFullName(TestUtils.getData().get("fullName"));
        userTwo.setLogin(TestUtils.getData().get("login"));
        userDao.create(userTwo);

        Post post1 = new Post();
        post1.setAuthor(userOne);
        post1.setTitle("Test title 1");
        post1.setContent("Test content 1 length  more than 1000 :)");
        post1.setRating(1);
        post1.setStatus(PostStatus.DRAFT);
        postDao.create(post1);

        Post post2 = new Post();
        post2.setAuthor(userTwo);
        post2.setTitle("Test title 2");
        post2.setContent("Test content 2 length  more than 1000 :)");
        post2.setRating(5);
        post2.setStatus(PostStatus.IN_PROGRESS);
        postDao.create(post2);

        Post post3 = new Post();
        post3.setAuthor(userOne);
        post3.setTitle("Test title 3");
        post3.setContent("Test content 3 length  more than 1000 :)");
        post3.setRating(3);
        post3.setStatus(PostStatus.PUBLISHED);
        postDao.create(post3);

        List<Post> topPostUserOne = postDao.findTopAuthorsPosts(userOne, 1);
        assertEquals(topPostUserOne.size(), 1);
    }
}