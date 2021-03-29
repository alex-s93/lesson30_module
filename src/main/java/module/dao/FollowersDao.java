package module.dao;

import module.model.User;

import java.util.List;

public interface FollowersDao {
    List<User> findAllFollowers(User author);

}
