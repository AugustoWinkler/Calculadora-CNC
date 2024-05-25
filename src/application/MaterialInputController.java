package application;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MaterialInputController {

	@FXML
	public TextField txtMaterial;
	@FXML
	public ComboBox<String> comboMaterial;
	
	
	
	public void handleSaveMaterial() {
		String material = txtMaterial.getText();
		comboMaterial.getItems().add(material);
		comboMaterial.getSelectionModel().selectFirst();
		
	}

}
