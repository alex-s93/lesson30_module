package com.alevel.module3.dao.impl;

import com.alevel.module3.dao.Dao;
import com.alevel.module3.model.User;
import com.alevel.module3.util.HibernateSessionAnnotationFactoryUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

public class UserDaoImpl implements Dao<User> {
    private SessionFactory sessionFactory = HibernateSessionAnnotationFactoryUtil.getSessionFactory();

    @Override
    public void create(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(User oldModel, User newModel) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User oldUser = session.get(User.class, oldModel.getId());
            Query query = session.createQuery("UPDATE User " +
                    "SET age = :age," +
                    "email = :email," +
                    "fullName = :full_name," +
                    "isAuthor = :is_author," +
                    "isModerator = :is_moderator," +
                    "login = :login " +
                    "WHERE id = :id");
            System.out.println(newModel);
            query.setParameter("age", newModel.getAge());
            query.setParameter("email", newModel.getEmail());
            query.setParameter("full_name", newModel.getFullName());
            query.setParameter("is_author", newModel.getAuthor());
            query.setParameter("is_moderator", newModel.getModerator());
            query.setParameter("login", newModel.getLogin());
            query.setParameter("id", oldUser.getId());
            query.executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public User findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("  FROM  User where id = :id");
            query.setParameter("id", id);
            User singleResult = (User) query.getSingleResult();
            session.getTransaction().commit();
            return singleResult;
        } catch (NoResultException nre) {
            return null;
        }
    }

    public User findByEmail(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("  FROM  User where email = :email");
            query.setParameter("email", user.getEmail());
            User singleResult = (User) query.getSingleResult();
            session.getTransaction().commit();
            return singleResult;
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public List<User> findAll() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<User> query = session.createQuery("FROM User", User.class);
            List<User> resultList = query.getResultList();
            session.getTransaction().commit();
            return resultList;
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public void deleteById(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("DELETE FROM User " +
                    "WHERE id = :id");
            query.setParameter("id", id);
            query.executeUpdate();
            session.getTransaction().commit();
        }
    }
}
