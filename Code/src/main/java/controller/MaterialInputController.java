package controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.AlertHelper;
import model.MaterialDAO;
import model.validateTextField;

public class MaterialInputController {
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
	public void addMaterial(Event e) {

		try {
			String name = txtMaterialName.getText().trim();

			if (name.isEmpty()) {
				AlertHelper.showAlert("Falha ao adicionar material", "Campo nome está vazio",
						"Por favcor, preencha o campo Nome com um valor válido.");
				return;
			}

			double price = Double.parseDouble(txtPriceMaterial.getText());

			MaterialDAO.insertMaterial(name, price);
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
	}

}
