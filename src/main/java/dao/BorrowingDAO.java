package dao;

import model.Borrowing;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;


public class BorrowingDAO {
    private static SessionFactory sessionFactory = SqlLiteSessionFactory.buildSessionFactory();

    public static void save(Borrowing borrowing) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(borrowing);
        session.getTransaction().commit();
    }

    public static void returnBook(Borrowing borrowing) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        borrowing.setReturned();
        session.update(borrowing);
        session.getTransaction().commit();
    }

    public static Borrowing getBorrow(int bookId){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Borrowing borrowing = (Borrowing) session.createCriteria(Borrowing.class).add(Restrictions.eq("bookId", bookId)).uniqueResult();
        session.getTransaction().commit();
        return borrowing;
    }


    public static List<Borrowing> load(){
        Session session = sessionFactory.openSession();
        List<Borrowing> data;
        data = session.createCriteria(Borrowing.class).list();
        session.close();
        return data;
    }

}
