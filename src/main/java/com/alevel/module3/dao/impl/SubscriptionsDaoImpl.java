package com.alevel.module3.dao.impl;

import com.alevel.module3.model.User;
import com.alevel.module3.util.HibernateSessionAnnotationFactoryUtil;
import com.alevel.module3.dao.SubscriptionsDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionsDaoImpl implements SubscriptionsDao {
    private SessionFactory sessionFactory = HibernateSessionAnnotationFactoryUtil.getSessionFactory();

    @Override
    public List<User> findAllSubscriptions(User subscriber) {
        List<User> subscriptions = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = session.get(User.class, subscriber.getId());
            Query query = session.createNativeQuery("SELECT subscription_id FROM user_subscriptions WHERE id = ?");
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
