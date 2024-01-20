package util;

import org.hibernate.SessionFactory;

import java.io.File;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {
    private static SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration().configure(new File("hibernate.cfg.xml")).buildSessionFactory();
    }


    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
