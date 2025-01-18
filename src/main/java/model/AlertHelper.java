package model;

import javafx.scene.control.Alert;

public class AlertHelper {

	public static void showAlert(String title, String header, String content) {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}
}