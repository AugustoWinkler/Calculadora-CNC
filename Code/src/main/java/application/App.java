package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.DatabaseSetup;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
        	DatabaseSetup.createTables();
            Parent root = FXMLLoader.load(App.class.getResource("/view/Main.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setTitle("Calculadora CNC");
            Image icon = new Image(App.class.getResourceAsStream("/assets/LaserIco.png"));
            primaryStage.getIcons().add(icon);

            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
