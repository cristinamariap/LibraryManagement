package server.services;

import javafx.collections.ObservableList;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import server.model.Book;
import server.model.Client;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
@EnableAutoConfiguration
public class SampleController {

    private LoginService loginService = new LoginService();
    private BookService bookService = new BookService();
    private ClientService clientService = new ClientService();
    private BorrowingService borrowService = new BorrowingService();

    @RequestMapping("/login")
    @ResponseBody
    boolean isLogin(@RequestParam final Map<String, String> body) {
        return loginService.isLogin(body.get("username"), body.get("password"), body.get("option"));
    }

    @RequestMapping("/admin/addBook")
    @ResponseBody
    void addBook(@RequestParam final Map<String, String> body) {
        bookService.createBook(Integer.parseInt(body.get("bookId")), body.get("author"), body.get("editor"), body.get("genre"), body.get("title"));
    }

    @RequestMapping("/admin/updateBook")
    @ResponseBody
    void updateBook(@RequestParam final Map<String, String> body) {
        bookService.updateBook(Integer.parseInt(body.get("bookId")), body.get("author"), body.get("editor"), body.get("genre"), body.get("title"));
    }

    @RequestMapping("/admin/deleteBook")
    @ResponseBody
    void deleteBook(@RequestParam final Map<String, String> body) {
        bookService.deleteBook(Integer.parseInt(body.get("bookId")));
    }

    @RequestMapping("/admin/loadAllBooks")
    @ResponseBody
    List<Book> loadAllBooks() {
        return bookService.loadBook();
    }

    @RequestMapping("/admin/loadAllClients")
    @ResponseBody
    List<Client> loadAllClients() {
        return clientService.loadClient();
    }

    @RequestMapping("/admin/addClient")
    @ResponseBody
    void addClient(@RequestParam final Map<String, String> body) {
        clientService.createClient(Integer.parseInt(body.get("clientId")), body.get("address"), body.get("email"), body.get("name"), body.get("password"), body.get("phone"), body.get("username"));
    }

    @RequestMapping("/admin/updateClient")
    @ResponseBody
    void updateClient(@RequestParam final Map<String, String> body) {
        clientService.updateClient(Integer.parseInt(body.get("clientId")), body.get("address"), body.get("email"), body.get("name"), body.get("password"), body.get("phone"), body.get("username"));
    }

    @RequestMapping("/admin/deleteClient")
    @ResponseBody
    void deleteClient(@RequestParam final Map<String, String> body) {
        clientService.deleteClient(Integer.parseInt(body.get("clientId")));
    }

    @RequestMapping("/client/loadAllBooks")
    @ResponseBody
    void loadAllBooks2(@RequestParam final Map<String, String> body) {
        bookService.loadBook();
    }

    @RequestMapping("/client/addBorrow")
    @ResponseBody
    void addBorrow(@RequestParam final Map<String, String> body) {
        borrowService.borrow(Integer.parseInt(body.get("idBook")), LocalDate.parse(body.get("startDate")), LocalDate.parse(body.get("endDate")), Integer.parseInt(body.get("client.getId()")));
    }

    @RequestMapping("/client/returnBook")
    @ResponseBody
    void returnBook(@RequestParam final Map<String, String> body) {
        borrowService.returnBook(Integer.parseInt(body.get("bookId")), Integer.parseInt(body.get("client.getId()")));
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleController.class, args);
    }
}
