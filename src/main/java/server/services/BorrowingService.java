package server.services;

import server.dao.BorrowingDAO;
import server.model.Book;
import server.model.Borrowing;
import server.model.Client;
import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class BorrowingService {

    public static void borrow(int id, LocalDate startDate, LocalDate endDate, int clientId){
//        Borrowing borrow = new Borrowing(startDate, endDate, new Client(){{setId(clientId);}}, new Book(){{setId(id);}});
        Borrowing borrow = new Borrowing(startDate, endDate, clientId, id);
        BorrowingDAO.save(borrow);
    }

    public static void returnBook(int bookId, int clientId){
        Borrowing borrowing = BorrowingDAO.getBorrow(bookId, clientId);
        BorrowingDAO.returnBook(borrowing);
    }

    public static void reportFiles(){

        JFileChooser chooser = new JFileChooser(  );
        chooser.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
        chooser.showSaveDialog( null );
        System.out.println( chooser.getSelectedFile() );

    }

    public static List<Borrowing> getAllBorrowings(){
        return BorrowingDAO.load();
    }

}
