package client.view;

import com.google.gson.Gson;
import javafx.scene.control.Alert;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import server.services.BookService;
import server.services.BorrowingService;
import server.services.ClientService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import server.model.Book;
import server.model.Client;

import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AdminController extends BaseController<Object> {
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
    private HttpHelper helper = new HttpHelper();

    int getClientId() {
        try {
            int cId = Integer.parseInt(clientId.getText());

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Input is not an int value").showAndWait();
        }
        return Integer.parseInt(clientId.getText());
    }

    String getAddress() {
        return address.getText();
    }

    String getEmail() {
        return email.getText();
    }

    String getName() {
        return name.getText();
    }

    String getPassword() {
        return password.getText();
    }

    String getPhone() {
        return phone.getText();
    }

    String getUsername() {
        return username.getText();
    }

    int getBookId() {
        try {
            int bId = Integer.parseInt(bookId.getText());

        } catch (NumberFormatException e) {
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
        Map<String, String> parameters = new HashMap<String, String>() {{
            put("bookId", Integer.toString(getBookId()));
            put("author", getAuthor());
            put("editor", getEditor());
            put("genre", getGenre());
            put("title", getTitle());
        }};
        helper.post("http://localhost:8080/admin/addBook", parameters);
//        HttpClient client = new DefaultHttpClient();
//        String url = "http://localhost:8080/admin/addBook";
//        HttpPost post = new HttpPost(url);
//
//        post.setHeader("User-Agent", "Mozilla/5.0");
//        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
//        urlParameters.add(new BasicNameValuePair("bookId", Integer.toString(getBookId())));
//        urlParameters.add(new BasicNameValuePair("author", getAuthor()));
//        urlParameters.add(new BasicNameValuePair("editor", getEditor()));
//        urlParameters.add(new BasicNameValuePair("genre", getGenre()));
//        urlParameters.add(new BasicNameValuePair("title", getTitle()));
//
//        try {
//            post.setEntity(new UrlEncodedFormEntity(urlParameters));
//
//            HttpResponse response = client.execute(post);
//
//            System.out.println("\nSending 'POST' request to URL : " + url);
//            System.out.println("Post parameters : " + post.getEntity());
//            System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
//
//            BufferedReader rd = new BufferedReader(
//                    new InputStreamReader(response.getEntity().getContent()));
//
//            StringBuffer result = new StringBuffer();
//            String line = "";
//            while ((line = rd.readLine()) != null) {
//                result.append(line);
//            }
//
//        } catch (IOException ex) {
//
//        }
    }
//
//    public void createBook() {
//        bookService.createBook(getBookId(), getAuthor(), getEditor(), getGenre(), getTitle());
//        loadBook();
//    }

    public void updateBook() {
//        bookService.updateBook(getBookId(), getAuthor(), getEditor(), getGenre(), getTitle());
//        loadBook();
        HttpClient client = new DefaultHttpClient();
        String url = "http://localhost:8080/admin/updateBook";
        HttpPost post = new HttpPost(url);

        post.setHeader("User-Agent", "Mozilla/5.0");
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("bookId", Integer.toString(getBookId())));
        urlParameters.add(new BasicNameValuePair("author", getAuthor()));
        urlParameters.add(new BasicNameValuePair("editor", getEditor()));
        urlParameters.add(new BasicNameValuePair("genre", getGenre()));
        urlParameters.add(new BasicNameValuePair("title", getTitle()));

        try {
            post.setEntity(new UrlEncodedFormEntity(urlParameters));

            HttpResponse response = client.execute(post);

            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + post.getEntity());
            System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

        } catch (IOException ex) {

        }
    }

    public void deleteBook() {
//        bookService.deleteBook(getBookId());
//        loadBook();
        HttpClient client = new DefaultHttpClient();
        String url = "http://localhost:8080/admin/deleteBook";
        HttpPost post = new HttpPost(url);

        post.setHeader("User-Agent", "Mozilla/5.0");
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("bookId", Integer.toString(getBookId())));

        try {
            post.setEntity(new UrlEncodedFormEntity(urlParameters));

            HttpResponse response = client.execute(post);

            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + post.getEntity());
            System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
        } catch (IOException ex) {

        }
    }

    public void loadBook() {

        ObservableList<Book> lb;

        lb = FXCollections.observableArrayList(loadBook1());

        populateTable(lb);

    }


    public List<Book> loadBook1() {
        HttpClient client = new DefaultHttpClient();
        String url = "http://localhost:8080/admin/loadAllBooks";
        HttpPost post = new HttpPost(url);

        post.setHeader("User-Agent", "Mozilla/5.0");
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();

        try {
            post.setEntity(new UrlEncodedFormEntity(urlParameters));

            HttpResponse response = client.execute(post);

            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + post.getEntity());
            System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            Type listType = new TypeToken<ArrayList<Book>>() {
            }.getType();
            List<Book> bookList = new Gson().fromJson(result.toString(), listType);
            return bookList;
        } catch (IOException ex) {

        }
        return new ArrayList<>();
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
//        ClientService.createClient(getClientId(), getAddress(), getEmail(), getName(), getPassword(), getPhone(), getUsername());
//        loadClient();
        HttpClient client = new DefaultHttpClient();
        String url = "http://localhost:8080/admin/addClient";
        HttpPost post = new HttpPost(url);

        post.setHeader("User-Agent", "Mozilla/5.0");
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("clientId", Integer.toString(getClientId())));
        urlParameters.add(new BasicNameValuePair("address", getAddress()));
        urlParameters.add(new BasicNameValuePair("email", getEmail()));
        urlParameters.add(new BasicNameValuePair("name", getName()));
        urlParameters.add(new BasicNameValuePair("password", getPassword()));
        urlParameters.add(new BasicNameValuePair("phone", getPhone()));
        urlParameters.add(new BasicNameValuePair("username", getUsername()));

        try {
            post.setEntity(new UrlEncodedFormEntity(urlParameters));

            HttpResponse response = client.execute(post);

            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + post.getEntity());
            System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

        } catch (IOException ex) {

        }
    }


    public void updateClient() {
//        ClientService.updateClient(getClientId(), getAddress(), getEmail(), getName(), getPassword(), getPhone(), getUsername());
//        loadClient();
        HttpClient client = new DefaultHttpClient();
        String url = "http://localhost:8080/admin/updateClient";
        HttpPost post = new HttpPost(url);

        post.setHeader("User-Agent", "Mozilla/5.0");
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("clientId", Integer.toString(getClientId())));
        urlParameters.add(new BasicNameValuePair("address", getAddress()));
        urlParameters.add(new BasicNameValuePair("email", getEmail()));
        urlParameters.add(new BasicNameValuePair("name", getName()));
        urlParameters.add(new BasicNameValuePair("password", getPassword()));
        urlParameters.add(new BasicNameValuePair("phone", getPhone()));
        urlParameters.add(new BasicNameValuePair("username", getUsername()));

        try {
            post.setEntity(new UrlEncodedFormEntity(urlParameters));

            HttpResponse response = client.execute(post);

            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + post.getEntity());
            System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

        } catch (IOException ex) {

        }

    }

    public void deleteClient() {
//        ClientService.deleteClient(getClientId());
//        loadClient();
        HttpClient client = new DefaultHttpClient();
        String url = "http://localhost:8080/admin/deleteClient";
        HttpPost post = new HttpPost(url);

        post.setHeader("User-Agent", "Mozilla/5.0");
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("clientId", Integer.toString(getClientId())));
        try {
            post.setEntity(new UrlEncodedFormEntity(urlParameters));

            HttpResponse response = client.execute(post);

            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + post.getEntity());
            System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

        } catch (IOException ex) {

        }
    }

    public void loadClient() {
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

    public void reportFiles() {
        BorrowingService.reportFiles();
    }

    void setHttpHelper(HttpHelper helper) {
        this.helper = helper;
    }

    @Override
    protected void setModel(Object model) {

    }
}
