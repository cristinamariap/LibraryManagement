package dao;

import model.Borrowing;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
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

    public static Borrowing getBorrow(int bookId, int clientId){
        Session session = sessionFactory.openSession();
        session.beginTransaction();

//// To get records matching with AND conditions
//        LogicalExpression andExp = Restrictions.and(salary, name);
//        cr.add( andExp );
//
//        List results = cr.list();

        Criteria cr = session.createCriteria(Borrowing.class);
        Criterion clientCriterion = Restrictions.gt("clientId", clientId);
        Criterion bookCriterion = Restrictions.ilike("bookId",bookId);
        LogicalExpression andExp = Restrictions.and(clientCriterion, bookCriterion);

        cr.add(andExp);

        Borrowing borrowing = (Borrowing) cr.uniqueResult();

//        Borrowing borrowing = (Borrowing) session.createCriteria(Borrowing.class).add(Restrictions.eq("bookId", bookId)).uniqueResult();
//        Borrowing borrowing = (Borrowing) session.get(Borrowing.class, bookId);
        System.out.println("");
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
