package model;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;


public class LoginApp extends Application{

    public void start(Stage stage) throws Exception{

        Parent root = (Parent) FXMLLoader.load(getClass().getResource("LoginFXML.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Bank Management System");
        stage.show();
    }

    public static void main(String[] args){

        launch(args);
    }

}


