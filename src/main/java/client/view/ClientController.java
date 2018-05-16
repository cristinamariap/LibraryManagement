package client.view;

import javafx.scene.control.*;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import server.services.BookService;
import server.services.BorrowingService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import server.model.Book;
import server.model.Client;
import server.services.ClientService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClientController extends BaseController<String>{
    public static Client client;
    private String username;
    private BookService bookService = new BookService();
    @FXML
    private TextField val;
    @FXML
    private TextField searchType;
    @FXML
    private TextField idBook;
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private TableColumn idBookCol;
    @FXML
    private TableColumn titleCol;
    @FXML
    private TableColumn authorCol;
    @FXML
    private TableColumn editorCol;
    @FXML
    private TableColumn genreCol;

    @FXML
    public void search(){
        ObservableList<Book> lb;

        lb = FXCollections.observableArrayList(BookService.search(searchType.getText(), val.getText()));

        setUpTable(lb);
    }
    @FXML
    private TableView<Book> bookTable;

    public void setClient(){
        this.client = ClientService.getClient(username);
    }

    int getBookId() {
        try{
            int bId = Integer.parseInt(idBook.getText());

        }catch(NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Input is not an int value").showAndWait();
        }
        return Integer.parseInt(idBook.getText());
    }

    LocalDate getStartDate(){
        return startDate.getValue();
    }

    LocalDate getEndDate(){
        return endDate.getValue();
    }


    @FXML
    public void borrow(){
        setClient();
        LocalDate startDt = startDate.getValue();
        LocalDate endDt = endDate.getValue();

        if (startDt.isAfter(endDt) || startDt.isBefore(LocalDate.now()))
            new Alert(Alert.AlertType.ERROR, "Invalid Dates").showAndWait();

        BorrowingService.borrow(getBookId(), getStartDate(), getEndDate(), client.getId());
    }

    @FXML
    public void returnBook(){
        setClient();
        BorrowingService.returnBook(getBookId(), client.getId());
    }

    @FXML
    public void loadAllBooks(){
        ObservableList<Book> lb;

        lb = FXCollections.observableArrayList(bookService.loadBook());

        setUpTable(lb);
    }

    private void setUpTable(ObservableList<Book> data){
        TableView<Book> table = new TableView<>();
        table.setItems(data);

        idBookCol.setCellValueFactory(new PropertyValueFactory<Book, String>("id"));
        titleCol.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        authorCol.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        editorCol.setCellValueFactory(new PropertyValueFactory<Book, String>("editor"));
        genreCol.setCellValueFactory(new PropertyValueFactory<Book, String>("genre"));

        this.bookTable.setItems(data);
    }

    @Override
    protected void setModel(String model) {
        username = model;
    }
}
