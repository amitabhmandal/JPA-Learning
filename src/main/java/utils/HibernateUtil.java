package utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import entities.User;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {

        //Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

        //System.out.println("configuration properties =" + configuration.getProperties());
        // configuration.addAnnotatedClass(User.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml")
            .build();
        
        return new Configuration().buildSessionFactory(serviceRegistry);

        /*
        
        return configuration.buildSessionFactory(new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
            .build());
            
            */
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
