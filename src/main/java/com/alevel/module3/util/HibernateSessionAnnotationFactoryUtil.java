package com.alevel.module3.util;

import com.alevel.module3.model.Post;
import com.alevel.module3.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionAnnotationFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionAnnotationFactoryUtil() {
        throw new UnsupportedOperationException();
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Post.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Error!" + e);
            }
        }
        return sessionFactory;
    }
}
