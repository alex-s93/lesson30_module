package com.alevel.module3.dao.impl;

import com.alevel.module3.dao.PostDao;
import com.alevel.module3.model.Post;
import com.alevel.module3.model.User;
import com.alevel.module3.util.HibernateSessionAnnotationFactoryUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

public class PostDaoImpl implements PostDao {

    private SessionFactory sessionFactory = HibernateSessionAnnotationFactoryUtil.getSessionFactory();

    public void create(Post post) {
        UserDaoImpl userDao = new UserDaoImpl();

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(post);
            session.getTransaction().commit();
        }

        User user = post.getAuthor();
        user.setAuthor(true);
        userDao.update(post.getAuthor(), user);
    }

    @Override
    public List<Post> findAllPostsByAuthor(User author) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = session.get(User.class, author.getId());
            Query<Post> query = session.createQuery("FROM Post WHERE author = :author", Post.class);
            query.setParameter("author", user);
            return query.getResultList();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public List<Post> findTopPosts(int limit) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<Post> query = session.createQuery("FROM Post order by rating desc", Post.class);
            query.setMaxResults(limit);
            return query.getResultList();
        } catch (NoResultException nre) {
            return null;
        }
    }


    @Override
    public List<Post> findTopAuthorsPosts(User author, int limit) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = session.get(User.class, author.getId());
            Query<Post> query = session.createQuery("FROM Post WHERE author = :author order by rating desc", Post.class);
            query.setParameter("author", user);
            query.setMaxResults(limit);
            return query.getResultList();
        } catch (NoResultException nre) {
            return null;
        }
    }
}
