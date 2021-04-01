package com.alevel.module3.dao.impl;

import com.alevel.module3.dao.FollowersDao;
import com.alevel.module3.model.User;
import com.alevel.module3.util.HibernateSessionAnnotationFactoryUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

public class FollowersDaoImpl implements FollowersDao {
    private SessionFactory sessionFactory = HibernateSessionAnnotationFactoryUtil.getSessionFactory();

    @Override
    public List<User> findAllFollowers(User author) {
        List<User> subscriptions = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = session.get(User.class, author.getId());
            Query query = session.createNativeQuery("SELECT follower_id FROM user_followers WHERE id = ?");
            query.setParameter(1, user.getId());
            List<Integer> resultList = (List<Integer>) query.getResultList();
            session.getTransaction().commit();
            for (Integer id: resultList) {
                subscriptions.add(session.get(User.class, id));
            }
        } catch (NoResultException nre) {
            return null;
        }
        return subscriptions;
    }
}
