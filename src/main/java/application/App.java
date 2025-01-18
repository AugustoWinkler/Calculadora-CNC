package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.DatabaseSetup;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Carrega o arquivo FXML
            Parent root = FXMLLoader.load(App.class.getResource("/view/Main.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setTitle("Calculadora CNC");

            // Criação das tabelas no banco de dados SQLite
            DatabaseSetup.createTables();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
