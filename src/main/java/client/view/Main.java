package client.view;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
    private static String view;
        public void start (Stage stage) throws Exception {

        Parent root = (Parent) FXMLLoader.load(getClass().getClassLoader().getResource(view));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Library Management System");
        stage.show();
        }

        public static void main (String[]args){
            view = args[0];
            launch(args);
         }
    }
