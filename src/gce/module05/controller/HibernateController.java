package gce.module05.controller;

import gce.module05.model.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Starts the hibernate session and session factory
 */
public class HibernateController {

    private static SessionFactory sessionFactory = null;

    // Loads the session factory automatically
    static {
        try {
            loadSessionFactory();
        } catch (Exception e) {
            System.err.println("Exception while initializing hibernate controller.");
            e.printStackTrace();
        }
    }

    /**
     * Loads the session factory based on the hibernate configuration file
     */
    public static void loadSessionFactory() {
        Configuration configuration = new Configuration();

        // Defaults to src/hibernate.cfg.xml
        configuration.configure().addAnnotatedClass(Item.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    /**
     * Opens and returns the hibernate session
     *
     * @return The opened session
     */
    public static Session getSession() {
        Session session = null;

        try {
            session = sessionFactory.openSession();
        } catch (Throwable t) {
            System.err.println("Error opening the session.");

            t.printStackTrace();
        }

        if (session == null) {
            System.err.println("No session exists.");
        }

        return session;
    }
}
