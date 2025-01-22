package controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.AlertHelper;
import model.Machine;
import model.MachineDAO;
import model.validateTextField;

public class MachineEditController {
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
	public void editMachine(Event e) {
		try {
			String oldName = mainController.getSelectedMachine();
			String newName = txtNameMachine.getText();
			double value = Double.parseDouble(txtValueMachine.getText());
			double usefulLife = Double.parseDouble(txtUsefulLife.getText());
			double residualValue = Double.parseDouble(txtResidualValue.getText());
			double laserValue = Double.parseDouble(txtLaserValue.getText());
			double laserUsefulLife = Double.parseDouble(txtLaserUsefulLife.getText());

			MachineDAO.editMachine(oldName, newName, value, usefulLife, residualValue, laserValue, laserUsefulLife);
			mainController.updateMachineCombo();


		} catch (NumberFormatException ex) {
			AlertHelper.showAlert("Falha ao adicionar máquina", "Valores inválidos",
					"Certifique-se de que todos os campos numéricos possuem valores válidos.");
		}

		Node source = (Node) e.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}

	public void setMainController(Controller mainController) {
		this.mainController = mainController;
		this.showMachine();
	}

	public void showMachine() {
		String selectedMachine = mainController.getSelectedMachine();
		Machine machine = MachineDAO.findMachine(selectedMachine);

		txtNameMachine.setText(machine.getName());
		txtValueMachine.setText(Double.toString(machine.getValue()));
		txtUsefulLife.setText(String.valueOf(machine.getUsefulLife()));
		txtResidualValue.setText(String.valueOf(machine.getResidualValue()));
		txtLaserValue.setText(String.valueOf(machine.getLaserValue()));
		txtLaserUsefulLife.setText(String.valueOf(machine.getLaserUsefulLife()));
	}
}
