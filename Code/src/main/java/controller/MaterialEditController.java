package controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.AlertHelper;
import model.Material;
import model.MaterialDAO;
import model.validateTextField;

public class MaterialEditController {
	private Controller mainController;
	@FXML
	private TextField txtMaterialName;
	@FXML
	private TextField txtPriceMaterial;

	private validateTextField validate;

	@FXML
	public void initialize() {
		this.validate = new validateTextField();
		validate.validateTextInt(txtPriceMaterial);
	}

	@FXML
	public void editMaterial(Event e) {
		try {
			String oldName = mainController.getSelectedMaterial();
			String newName = txtMaterialName.getText();
			double value = Double.parseDouble(txtPriceMaterial.getText());

			MaterialDAO.editMaterial(oldName, newName, value);
			mainController.updateMaterialCombo();


		} catch (NumberFormatException ex) {
			AlertHelper.showAlert("Falha ao adicionar material", "Valores inválidos",
					"Certifique-se de que todos os campos numéricos possuem valores válidos.");

		}
		Node source = (Node) e.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();

	}

	public void setMainController(Controller mainController) {
		this.mainController = mainController;
		this.showMaterial();
	}

	public void showMaterial() {
		String selectedMaterial = mainController.getSelectedMaterial();
		Material material = MaterialDAO.findMaterial(selectedMaterial);

		txtMaterialName.setText(material.getName());
		txtPriceMaterial.setText(String.valueOf(material.getPrice()));
	}
}
