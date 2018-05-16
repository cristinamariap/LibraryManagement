package client.view;

import client.view.AdminController;
import javafx.collections.ObservableList;
import server.model.Book;
import org.junit.Test;
import org.mockito.Mockito;
import server.services.BookService;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class AdminControllerTest {

    private AdminController adminController;

//    @Test(expected = InvalidInputException.class)
//    public void throwInvalidInputExceptionForNullFieldsOnBook() {
//        adminController = new AdminControllerAllNull();
//        adminController.setBookService(new BookServiceMock());
//        adminController.createBook();
//    }
//
//    @Test
//    public void createBookFromValidData() {
//        adminController = new AdminControllerValidData();
//        BookServiceMock bookService = new BookServiceMock();
//        adminController.setBookService(bookService);
//        adminController.createBook();
//        assertTrue(bookService.booksLoaded);
//    }

    @Test(expected = InvalidInputException.class)
    public void throwInvalidInputExceptionForNullFieldsOnBookCreate() {
        adminController = new AdminControllerAllNull();
        HttpHelper helper = mock(HttpHelper.class);
        doThrow(InvalidInputException.class).when(helper.post("http://localhost:8080/admin/addBook", new HashMap<String, String>() {{
            put("bookId", "0");
            put("author", null);
            put("editor", null);
            put("genre", null);
            put("title", null);
        }}));
//        BookService bookService = Mockito.mock(BookService.class);
//        doThrow(InvalidInputException.class).when(bookService).createBook(0, null, null, null, null);
//        adminController.setBookService(bookService);
        adminController.createBook();
    }

    @Test
    public void createBookFromValidData() {
        final boolean[] booksLoaded = {false};
        adminController = new AdminControllerValidData();
        BookService bookService = mock(BookService.class);
        doAnswer(invocationOnMock -> booksLoaded[0] = true).when(bookService).createBook(anyInt(), anyString(), anyString(), anyString(), anyString());
        adminController.setBookService(bookService);

        adminController.createBook();

        assertTrue(booksLoaded[0]);
    }

    @Test(expected = InvalidInputException.class)
    public void throwInvalidInputExceptionForNullFieldsOnBookUpdate() {
        adminController = new AdminControllerAllNull();
        BookService bookService = Mockito.mock(BookService.class);
        doThrow(InvalidInputException.class).when(bookService).updateBook(0, null, null, null, null);
        adminController.setBookService(bookService);
        adminController.updateBook();
    }

    @Test
    public void updateBookFromValidData() {
        final boolean[] booksLoaded = {false};
        adminController = new AdminControllerValidData();
        BookService bookService = mock(BookService.class);
        doAnswer(invocationOnMock -> booksLoaded[0] = true).when(bookService).updateBook(anyInt(), anyString(), anyString(), anyString(), anyString());
        adminController.setBookService(bookService);

        adminController.updateBook();

        assertTrue(booksLoaded[0]);
    }

    @Test(expected = InvalidInputException.class)
    public void throwInvalidInputExceptionForNullFieldsOnBookDelete() {
        adminController = new AdminControllerAllNull();
        BookService bookService = Mockito.mock(BookService.class);
        doThrow(InvalidInputException.class).when(bookService).deleteBook(0);
        adminController.setBookService(bookService);
        adminController.deleteBook();
    }

    @Test
    public void deleteBookFromValidData() {
        final boolean[] booksLoaded = {false};
        adminController = new AdminControllerValidData();
        BookService bookService = mock(BookService.class);
        doAnswer(invocationOnMock -> booksLoaded[0] = true).when(bookService).deleteBook(anyInt());
        adminController.setBookService(bookService);

        adminController.deleteBook();

        assertTrue(booksLoaded[0]);
    }

//    private class BookServiceMock extends BookService {
//        boolean booksLoaded = false;
//
//        @Override
//        public void createBook(int id, String author, String editor, String genre, String title) {
//            if (author == null || editor == null || genre == null || title == null)
//                throw new InvalidInputException();
//        }
//
//        @Override
//        public List<Book> loadBook() {
//            booksLoaded = true;
//            return new ArrayList<>();
//        }
//    }

    private class AdminControllerAllNull extends AdminController {
        @Override
        int getBookId() {
            return 0;
        }

        @Override
        String getAuthor() {
            return null;
        }

        @Override
        String getEditor() {
            return null;
        }

        @Override
        String getGenre() {
            return null;
        }

        @Override
        String getTitle() {
            return null;
        }
    }

    private class InvalidInputException extends RuntimeException {
    }

    private class AdminControllerValidData extends AdminController {
        @Override
        int getBookId() {
            return 0;
        }

        @Override
        String getAuthor() {
            return "Agatha";
        }

        @Override
        String getEditor() {
            return "RAO";
        }

        @Override
        String getGenre() {
            return "Crime";
        }

        @Override
        String getTitle() {
            return "Conacul dintre dealuri";
        }

        @Override
        void populateTable(ObservableList<Book> lb) {
        }
    }
}
