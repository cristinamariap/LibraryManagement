package server.services;

import server.dao.BookDAO;
import server.model.Book;

import java.util.List;

public class BookService {

    public static List<Book> search(String searchType, String val){
       return BookDAO.search(searchType, val);
    }

    public void createBook(int id, String author, String editor, String genre, String title){
        BookDAO.save(new Book(id, title, author, genre, editor));
    }

    public void updateBook(int id, String author, String editor, String genre, String title){
        BookDAO.update(new Book(id, title, author, genre, editor));
    }

    public void deleteBook(int id){
        BookDAO.delete(id);
    }

    public Book readBook(int id){
        return BookDAO.read(id);
    }

    public List<Book> loadBook(){
        return BookDAO.load();
    }


}
