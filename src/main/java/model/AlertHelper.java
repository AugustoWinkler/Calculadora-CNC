package model;

import application.App;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AlertHelper {

    public static void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(content);

        // Configurando o ícone
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(App.class.getResourceAsStream("/assets/LaserIco.png")));

        alert.showAndWait();
    }

    public static void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        // Configurando o ícone
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(App.class.getResourceAsStream("/assets/LaserIco.png")));

        alert.showAndWait();
    }
}
