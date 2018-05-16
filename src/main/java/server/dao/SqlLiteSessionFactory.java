package server.dao;

import server.model.Book;
import server.model.Borrowing;
import server.model.Client;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class SqlLiteSessionFactory {
    private static SessionFactory sessionFactoryObj;

    public static org.hibernate.SessionFactory buildSessionFactory() {
        Configuration config = new Configuration();
        config.addAnnotatedClass(Client.class)
                .addAnnotatedClass(Book.class)
                .addAnnotatedClass(Borrowing.class);
        config.configure("hiberncate.cfg.xml");
        ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
        sessionFactoryObj = config.buildSessionFactory(serviceRegistryObj);
        return sessionFactoryObj;
    }
}
