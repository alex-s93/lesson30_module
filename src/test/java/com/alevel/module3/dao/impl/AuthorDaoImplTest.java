package com.alevel.module3.dao.impl;

import com.alevel.module3.model.Post;
import com.alevel.module3.model.PostStatus;
import com.alevel.module3.model.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AuthorDaoImplTest {

    private PostDaoImpl postDao = new PostDaoImpl();
    private UserDaoImpl userDao = new UserDaoImpl();
    private AuthorDaoImpl authorDao = new AuthorDaoImpl();

    @Test
    void findAllAuthors() {
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

        assertFalse(userDao.findByEmail(userOne).getAuthor());
        assertFalse(userDao.findByEmail(userTwo).getAuthor());

        Post post = new Post();
        post.setAuthor(userOne);
        post.setTitle("Test title");
        post.setContent("Test content length  more than 1000 :)");
        post.setRating(0);
        post.setStatus(PostStatus.DRAFT);
        postDao.create(post);

        assertTrue(userDao.findByEmail(userOne).getAuthor());
        assertFalse(userDao.findByEmail(userTwo).getAuthor());

        assertEquals(authorDao.findAllAuthors().size(), 1);
        assertEquals(authorDao.findAllAuthors().get(0).getEmail(), userOne.getEmail());

    }

    @Test
    void findTopAuthors() {
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

        User userThree = new User();
        userThree.setAge(20);
        userThree.setEmail(TestUtils.getData().get("email"));
        userThree.setFullName(TestUtils.getData().get("fullName"));
        userThree.setLogin(TestUtils.getData().get("login"));
        userDao.create(userThree);

        assertFalse(userDao.findByEmail(userOne).getAuthor());
        assertFalse(userDao.findByEmail(userTwo).getAuthor());
        assertFalse(userDao.findByEmail(userThree).getAuthor());

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
        post3.setAuthor(userThree);
        post3.setTitle("Test title 3");
        post3.setContent("Test content 3 length  more than 1000 :)");
        post3.setRating(3);
        post3.setStatus(PostStatus.PUBLISHED);
        postDao.create(post3);

        assertTrue(userDao.findByEmail(userOne).getAuthor());
        assertTrue(userDao.findByEmail(userTwo).getAuthor());
        assertTrue(userDao.findByEmail(userThree).getAuthor());

        List<User> top2author = authorDao.findTopAuthors(2);
        assertEquals(top2author.size(), 2);
        assertEquals(top2author.get(0).getEmail(), userThree.getEmail());
        assertEquals(top2author.get(1).getEmail(), userTwo.getEmail());
    }

}