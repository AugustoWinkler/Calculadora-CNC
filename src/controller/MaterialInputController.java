package controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
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
		String name = txtMaterialName.getText().trim();

		if (name.isEmpty()) {
			AlertHelper.showAlert("Falha ao adicionar material", "Campo nome está vazio",
					"Por favcor, preencha o campo Nome com um valor válido.");
			return;
		}

		try {
			double price = Double.parseDouble(txtPriceMaterial.getText());

			MaterialDAO.insertMaterial(name, price);
			
			System.out.println("Material Adicionado");

			if (mainController != null) {
				mainController.updateMaterialCombo();
			} else {
				System.out.println("mainController está nulo!");
			}
		} catch (NumberFormatException ex) {
			AlertHelper.showAlert("Falha ao adicionar material", "Valores inválidos",
					"Certifique-se de que todos os campos numéricos possuem valores válidos.");
		
		}

	}

	public void setMainController(Controller mainController) {
		this.mainController = mainController;
	}

}
