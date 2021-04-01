package com.alevel.module3.dao.impl;

import com.alevel.module3.model.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoImplTest {
    private UserDaoImpl userDao = new UserDaoImpl();

    @Test
    void create() {
        SubscriptionsDaoImpl subscriberDao = new SubscriptionsDaoImpl();
        FollowersDaoImpl followersDao = new FollowersDaoImpl();

        List<User> userThreeSubscribers = new ArrayList<>();
        List<User> userFourFollowers = new ArrayList<>();

        User userOne = new User();
        userOne.setAge(19);
        userOne.setEmail(TestUtils.getData().get("email"));
        userOne.setFullName(TestUtils.getData().get("fullName"));
        userOne.setLogin(TestUtils.getData().get("login"));
        userDao.create(userOne);
        userThreeSubscribers.add(userOne);
        userFourFollowers.add(userOne);

        User userTwo = new User();
        userTwo.setAge(20);
        userTwo.setEmail(TestUtils.getData().get("email"));
        userTwo.setFullName(TestUtils.getData().get("fullName"));
        userTwo.setLogin(TestUtils.getData().get("login"));
        userDao.create(userTwo);
        userThreeSubscribers.add(userTwo);
        userFourFollowers.add(userTwo);

        User userThree = new User();
        userThree.setAge(21);
        userThree.setEmail(TestUtils.getData().get("email"));
        userThree.setFullName(TestUtils.getData().get("fullName"));
        userThree.setLogin(TestUtils.getData().get("login"));
        userThree.setSubscriptions(userThreeSubscribers);
        userDao.create(userThree);
        userFourFollowers.add(userThree);


        User userFour = new User();
        userFour.setAge(22);
        userFour.setEmail(TestUtils.getData().get("email"));
        userFour.setFullName(TestUtils.getData().get("fullName"));
        userFour.setLogin(TestUtils.getData().get("login"));
        userFour.setFollowers(userFourFollowers);
        userDao.create(userFour);

        assertEquals(userDao.findByEmail(userOne).getAge(), 19);
        assertEquals(userDao.findByEmail(userTwo).getAge(), 20);
        assertEquals(userDao.findByEmail(userThree).getAge(), 21);
        assertEquals(userDao.findByEmail(userFour).getAge(), 22);

        // Test for subscriptions
        assertEquals(subscriberDao.findAllSubscriptions(userThree).size(), 2);
        // Test for followers
        assertEquals(followersDao.findAllFollowers(userFour).size(), 3);
    }

    @Test
    void update() {
        User user = new User();
        user.setAge(19);
        user.setEmail(TestUtils.getData().get("email"));
        user.setFullName(TestUtils.getData().get("fullName"));
        user.setLogin(TestUtils.getData().get("login"));
        userDao.create(user);

        String newEmail = TestUtils.getData().get("email");
        String newFullName = TestUtils.getData().get("fullName");
        String newLogin = TestUtils.getData().get("login");
        int newAge = 20;

        User updatedUser = new User();
        updatedUser.setAge(newAge);
        updatedUser.setEmail(newEmail);
        updatedUser.setFullName(newFullName);
        updatedUser.setLogin(newLogin);

        userDao.update(user, updatedUser);
        User dbUser = userDao.findById(user.getId());
        assertEquals(dbUser.getEmail(), newEmail);
        assertEquals(dbUser.getAge(), newAge);
        assertEquals(dbUser.getFullName(), newFullName);
        assertEquals(dbUser.getLogin(), newLogin);
    }

    @Test
    void findById() {
        String email = TestUtils.getData().get("email");
        User user = new User();
        user.setAge(19);
        user.setEmail(email);
        user.setFullName(TestUtils.getData().get("fullName"));
        user.setLogin(TestUtils.getData().get("login"));
        userDao.create(user);

        assertEquals(userDao.findById(user.getId()).getEmail(), email);
    }

    @Test
    void findByEmail() {
        String login = TestUtils.getData().get("login");
        User user = new User();
        user.setAge(19);
        user.setEmail(TestUtils.getData().get("email"));
        user.setFullName(TestUtils.getData().get("fullName"));
        user.setLogin(login);
        userDao.create(user);

        assertEquals(userDao.findByEmail(user).getLogin(), login);
    }

    @Test
    void findAll() {
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
        userThree.setAge(21);
        userThree.setEmail(TestUtils.getData().get("email"));
        userThree.setFullName(TestUtils.getData().get("fullName"));
        userThree.setLogin(TestUtils.getData().get("login"));
        userDao.create(userThree);

        List<User> allUsers = userDao.findAll();
        assertTrue(allUsers.size() >= 3);
    }

    @Test
    void deleteById() {
        String email = TestUtils.getData().get("email");
        User user = new User();
        user.setAge(19);
        user.setEmail(email);
        user.setFullName(TestUtils.getData().get("fullName"));
        user.setLogin(TestUtils.getData().get("login"));
        userDao.create(user);

        userDao.deleteById(user.getId());

        assertNull(userDao.findById(user.getId()));
    }
}