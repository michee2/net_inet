package ci.aho.demo.models.utils;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;


public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() throws HibernateException {
        if (sessionFactory == null) {
            try {
                // Create registry
                sessionFactory = new Configuration()
                        .configure("hibernate.cfg.xml")
                        .buildSessionFactory();

            } catch (HibernateException ex) {
                throw new RuntimeException("Problème de config:" + ex.getMessage(), ex);
            }
        }
        return sessionFactory;
    }
}