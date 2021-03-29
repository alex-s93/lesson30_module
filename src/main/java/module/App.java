package module;

import module.util.HibernateSessionXmlFactoryUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class App {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateSessionXmlFactoryUtil.getSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            System.out.println("test");

        }
    }
}
