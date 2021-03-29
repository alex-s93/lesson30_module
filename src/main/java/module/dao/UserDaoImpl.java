package module.dao;

import module.model.User;
import module.util.HibernateSessionXmlFactoryUtil;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoImpl implements Dao<User> {
    private SessionFactory sessionFactory = HibernateSessionXmlFactoryUtil.getSessionFactory();

    @Override
    public User create(User model) {
        return null;
    }

    @Override
    public User update(User model) {
        return null;
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
