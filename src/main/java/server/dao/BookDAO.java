package server.dao;

import server.model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class BookDAO {
    private static SessionFactory sessionFactory = SqlLiteSessionFactory.buildSessionFactory();

    public static void save(Book book) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(book);
        session.getTransaction().commit();
    }

    public static void update(Book book) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(book);
        session.getTransaction().commit();
    }

    public static void delete(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Book book = (Book) session.get(Book.class, id);
        session.delete(book);
        session.getTransaction().commit();
    }

    public static List<Book> search(String type, String value){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Book> data;
        String sqlString = String.format("SELECT * FROM Book WHERE %s IS '%s'",type, value);

        Query query = session.createSQLQuery(sqlString)
                .addEntity(Book.class);
        data = query.list();

        return data;
    }

    public static Book read(int id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Book book = (Book) session.get(Book.class, id);
        session.getTransaction().commit();
        return book;
    }

    public static List<Book> load(){
        Session session = sessionFactory.openSession();
        List<Book> data;
        data = session.createCriteria(Book.class).list();
        session.close();
        return data;
    }

}
