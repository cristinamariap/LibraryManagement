package view;

import javafx.scene.control.Alert;
import services.BookService;
import services.BorrowingService;
import services.ClientService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Book;
import model.Client;

public class AdminController extends BaseController<Object>{
    @FXML
    private TextField bookId;
    @FXML
    private TextField author;
    @FXML
    private TextField editor;
    @FXML
    private TextField genre;
    @FXML
    private TextField title;


    @FXML
    private TextField clientId;
    @FXML
    private TextField address;
    @FXML
    private TextField email;
    @FXML
    private TextField name;
    @FXML
    private TextField password;
    @FXML
    private TextField phone;
    @FXML
    private TextField username;

    @FXML
    private TableView<Book> bookTable;

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
    private TableView<Client> clientTable;

    @FXML
    private TableColumn idClientCol;
    @FXML
    private TableColumn nameCol;
    @FXML
    private TableColumn addressCol;
    @FXML
    private TableColumn emailCol;
    @FXML
    private TableColumn phoneCol;
    @FXML
    private TableColumn usernameCol;
    @FXML
    private TableColumn passwordCol;

    private BookService bookService = new BookService();

    int getClientId1() {
        try{
            int cId = Integer.parseInt(clientId.getText());

        }catch(NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Input is not an int value").showAndWait();
        }
        return Integer.parseInt(clientId.getText());
    }

    int getClientId() {
        return Integer.parseInt(clientId.getText());
    }
    String getAddress(){
        return address.getText();
    }

    String getEmail(){
        return email.getText();
    }

    String getName(){
        return name.getText();
    }

    String getPassword(){
        return password.getText();
    }

    String getPhone(){
        return phone.getText();
    }

    String getUsername(){
        return username.getText();
    }

    int getBookId() {
        try{
            int bId = Integer.parseInt(bookId.getText());

        }catch(NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Input is not an int value").showAndWait();
        }
        return Integer.parseInt(bookId.getText());
    }

    String getAuthor() {
        return author.getText();
    }

    String getEditor() {
        return editor.getText();
    }

    String getGenre() {
        return genre.getText();
    }

    String getTitle() {
        return title.getText();
    }

    public void createBook() {
        bookService.createBook(getBookId(), getAuthor(), getEditor(), getGenre(), getTitle());
        loadBook();
    }

    public void updateBook() {
        bookService.updateBook(getBookId(), getAuthor(), getEditor(), getGenre(), getTitle());
        loadBook();
    }

    public void deleteBook() {

        bookService.deleteBook(getBookId());
        loadBook();
    }

    public void loadBook() {

        ObservableList<Book> lb;

        lb = FXCollections.observableArrayList(bookService.loadBook());

        populateTable(lb);

    }

    void populateTable(ObservableList<Book> lb) {
        idBookCol.setCellValueFactory(new PropertyValueFactory<Book, String>("id"));
        titleCol.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        authorCol.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        editorCol.setCellValueFactory(new PropertyValueFactory<Book, String>("editor"));
        genreCol.setCellValueFactory(new PropertyValueFactory<Book, String>("genre"));

        this.bookTable.setItems(lb);
    }

    public void createClient() {
        ClientService.createClient(getClientId(), getAddress(), getEmail(), getName(), getPassword(), getPhone(), getUsername());
        loadClient();
    }


    public void updateClient(){
        ClientService.updateClient(getClientId(), getAddress(), getEmail(), getName(), getPassword(), getPhone(), getUsername());
        loadClient();
    }

    public void deleteClient(){
        ClientService.deleteClient(getClientId());
        loadClient();
    }

    public void loadClient(){
        ObservableList<Client> lc;

        lc = FXCollections.observableArrayList(ClientService.loadClient());

        TableView<Client> table = new TableView<Client>();
        table.setItems((ObservableList<Client>) lc);


        idClientCol.setCellValueFactory(new PropertyValueFactory<Book, String>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Book, String>("name"));
        addressCol.setCellValueFactory(new PropertyValueFactory<Book, String>("address"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<Book, String>("phone"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<Book, String>("username"));
        passwordCol.setCellValueFactory(new PropertyValueFactory<Book, String>("password"));
        emailCol.setCellValueFactory(new PropertyValueFactory<Book, String>("email"));

        this.clientTable.setItems(lc);
    }

    public void reportFiles(){
        BorrowingService.reportFiles();
    }

    void setBookService(BookService service) {
        this.bookService = service;
    }

    @Override
    protected void setModel(Object model) {

    }
}
