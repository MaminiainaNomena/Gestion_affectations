package com.example.gestion.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public final class HibernateUtil {
    private static final SessionFactory SESSION_FACTORY = buildSessionFactory();

    private HibernateUtil() {
    }

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration().configure();
            configuration.setProperty("hibernate.connection.url",
                    env("DB_URL", "jdbc:postgresql://localhost:5432/gestion_affectations"));
            configuration.setProperty("hibernate.connection.username", env("DB_USER", "postgres"));
            configuration.setProperty("hibernate.connection.password", env("DB_PASSWORD", "postgres"));
            configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
            return configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("Impossible d'initialiser Hibernate : " + ex.getMessage());
        }
    }

    private static String env(String name, String defaultValue) {
        String value = System.getenv(name);
        return value == null || value.isBlank() ? defaultValue : value;
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
