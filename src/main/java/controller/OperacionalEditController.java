package controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.AlertHelper;
import model.Operacional;
import model.OperacionalDAO;
import model.validateTextField;

public class OperacionalEditController {
	private Controller mainController;
	@FXML
	private TextField txtDescOperacional;
	@FXML
	private TextField txtDaysOfWork;
	@FXML
	private TextField txtHoursPerDay;
	@FXML
	private TextField txtOperacionalCosts;
	@FXML
	private TextField txtOperatorValue;

	private validateTextField validate;

	@FXML
	public void initialize() {
		this.validate = new validateTextField();
		validate.validateTextInt(txtDaysOfWork);
		validate.validateTextInt(txtHoursPerDay);
		validate.validateTextInt(txtOperacionalCosts);
		validate.validateTextInt(txtOperatorValue);
	}

	@FXML
	public void editOperacional(Event e) {

		try {

			String oldName = mainController.getSelectedOperacional();
			String newName = txtDescOperacional.getText();
			double daysOfWork = Double.parseDouble(txtDaysOfWork.getText());
			double hoursPerDay = Double.parseDouble(txtHoursPerDay.getText());
			double operacionalCosts = Double.parseDouble(txtOperacionalCosts.getText());
			double operatorValue = Double.parseDouble(txtOperatorValue.getText());

			OperacionalDAO.editOperacional(oldName, newName, daysOfWork, hoursPerDay, operacionalCosts, operatorValue);
			mainController.updateOperacionalCombo();

		} catch (NumberFormatException ex) {
			AlertHelper.showAlert("Falha ao adicionar operacional", "Valores inválidos",
					"Certifique-se de que todos os campos numéricos possuem valores válidos.");
		}

		Node source = (Node) e.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}

	public void setMainController(Controller mainController) {
		this.mainController = mainController;
		this.showOperacional();
	}

	public void showOperacional() {
		String selectedOperacional = mainController.getSelectedOperacional();
		Operacional operacional = OperacionalDAO.findOperacional(selectedOperacional);

		txtDescOperacional.setText(operacional.getDesc());
		txtDaysOfWork.setText(String.valueOf(operacional.getDays()));
		txtHoursPerDay.setText(String.valueOf(operacional.getHoursPerDay()));
		txtOperacionalCosts.setText(String.valueOf(operacional.getOperacionalCost()));
		txtOperatorValue.setText(String.valueOf(operacional.getOperatorValue()));
	}
}
