package client.view;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import server.services.LoginService;
import server.services.option;

public class LoginController extends BaseController<Object> implements Initializable{

    private LoginService loginService = new LoginService();

    @FXML
    public TextField username;
    @FXML
    public PasswordField password;

    @FXML
    public Button login;

    @FXML
    public ComboBox<option> combobox;

    public void initialize(URL url, ResourceBundle rb) {
        this.combobox.setItems(FXCollections.observableArrayList(option.values()));
    }

    @FXML
    public void login(ActionEvent event){
        try{
            if(loginRequest()){
                Stage stage = (Stage) this.login.getScene().getWindow();
                stage.close();
                switch (((option) this.combobox.getValue()).toString()){
                    case "Admin":
                        adminLogin();
                        break;
                    case "Client":
                        clientLogin(this.username.getText());
                        break;
                }
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public boolean loginRequest(){
        HttpClient client = new DefaultHttpClient();
        String url = "http://localhost:8080/login";
        HttpPost post = new HttpPost(url);

        post.setHeader("User-Agent", "Mozilla/5.0");
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("username", username.getText() ));
        urlParameters.add(new BasicNameValuePair("password", password.getText()));
        urlParameters.add(new BasicNameValuePair("option",  combobox.getValue().toString()));

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

             return Boolean.valueOf(result.toString());

        }catch (IOException ex ) {

        }
        return false;
    }

    public void clientLogin(String username){
        showView("ClientFXML.fxml", username);
    }

    public void adminLogin(){
        showView("AdminFXML.fxml", new Object());
    }

    @Override
    protected void setModel(Object model) {

    }
}
