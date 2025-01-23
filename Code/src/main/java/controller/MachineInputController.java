package controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.AlertHelper;
import model.MachineDAO;
import model.validateTextField;

public class MachineInputController {
	private Controller mainController;
	@FXML
	private TextField txtNameMachine;
	@FXML
	private TextField txtValueMachine;
	@FXML
	private TextField txtUsefulLife;
	@FXML
	private TextField txtResidualValue;
	@FXML
	private TextField txtLaserValue;
	@FXML
	private TextField txtLaserUsefulLife;

	private validateTextField validate;

	@FXML
	public void initialize() {
		this.validate = new validateTextField();
		validate.validateTextInt(txtValueMachine);
		validate.validateTextInt(txtUsefulLife);
		validate.validateTextInt(txtLaserUsefulLife);
		validate.validateTextInt(txtResidualValue);
		validate.validateTextInt(txtLaserValue);
		validate.validateTextInt(txtLaserUsefulLife);
	}

	@FXML
	public void addMachine(Event e) {
		String name = txtNameMachine.getText().trim();

		if (name.isEmpty()) {
			AlertHelper.showAlert("Falha ao adicionar máquina", "Campo Nome vazio",
					"Por favor, preencha o campo Nome com um valor válido.");
			return;
		}

		try {

			double value = Double.parseDouble(txtValueMachine.getText());
			double usefulLife = Double.parseDouble(txtUsefulLife.getText());
			double residualValue = Double.parseDouble(txtResidualValue.getText());
			double laserValue = Double.parseDouble(txtLaserValue.getText());
			double laserUsefulLife = Double.parseDouble(txtLaserUsefulLife.getText());

			MachineDAO.insertMachine(name, value, usefulLife, residualValue, laserValue, laserUsefulLife);
			mainController.updateMachineCombo();

		} catch (NumberFormatException ex) {
			AlertHelper.showAlert("Falha ao adicionar máquina", "Valores inválidos",
					"Certifique-se de que todos os campos numéricos possuem valores válidos.");
		}
		Node source = (Node) e.getSource(); Stage
		stage = (Stage) source.getScene().getWindow(); stage.close();
	}

	public void setMainController(Controller mainController) {
		this.mainController = mainController;
	}

}
