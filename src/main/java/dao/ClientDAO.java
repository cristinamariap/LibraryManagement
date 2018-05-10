package dao;

import model.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ClientDAO {

    private static SessionFactory sessionFactory = SqlLiteSessionFactory.buildSessionFactory();

    public static void save(Client client) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(client);
        session.getTransaction().commit();
    }

    public static void update(Client client) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(client);
        session.getTransaction().commit();
    }

    public static void delete(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Client client = (Client) session.get(Client.class, id);
        session.delete(client);
        session.getTransaction().commit();
    }

    public static Client read(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Client client = (Client) session.get(Client.class, id);
        session.getTransaction().commit();
        return client;
    }

    public static Client getClient(String username){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Client client = (Client) session.createCriteria(Client.class).add(Restrictions.eq("username", username)).uniqueResult();
        session.getTransaction().commit();
        return client;
    }

    public static List<Client> load(){
        Session session = sessionFactory.openSession();
        List<Client> data;
        data = session.createCriteria(Client.class).list();
        session.close();
        return data;
    }

}