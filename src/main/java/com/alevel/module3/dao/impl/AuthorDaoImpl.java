package com.alevel.module3.dao.impl;

import com.alevel.module3.dao.AuthorDao;
import com.alevel.module3.model.User;
import com.alevel.module3.util.HibernateSessionAnnotationFactoryUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

public class AuthorDaoImpl implements AuthorDao {

    private SessionFactory sessionFactory = HibernateSessionAnnotationFactoryUtil.getSessionFactory();

    @Override
    public List<User> findAllAuthors() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<User> query = session.createQuery("FROM User WHERE isAuthor = true", User.class);
            return query.getResultList();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public List<User> findTopAuthors(int limit) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<User> query = session.createQuery("SELECT author FROM Post order by rating desc", User.class);
            query.setMaxResults(limit);
            return query.getResultList();
        } catch (NoResultException nre) {
            return null;
        }
    }
}
