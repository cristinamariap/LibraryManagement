package view;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import services.LoginService;
import services.option;

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
            if(this.loginService.isLogin(this.username.getText(), this.password.getText(), this.combobox.getValue().toString())){
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
