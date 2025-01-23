package controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.AlertHelper;
import model.OperacionalDAO;
import model.validateTextField;

public class OperacionalInputController {
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
	public void addOperacional(Event e) {
		try {
			String name = txtDescOperacional.getText();
			if (name.isEmpty()) {
				AlertHelper.showAlert("Falha ao adicionar operacional", "Campo nome está vazio",
						"Por favcor, preencha o campo Nome com um valor válido.");
				return;
			}

			double daysOfWork = Double.parseDouble(txtDaysOfWork.getText());
			double hoursPerDay = Double.parseDouble(txtHoursPerDay.getText());
			double operacionalCosts = Double.parseDouble(txtOperacionalCosts.getText());
			double operatorValue = Double.parseDouble(txtOperatorValue.getText());

			OperacionalDAO.insertOperacional(name, daysOfWork, hoursPerDay, operacionalCosts, operatorValue);
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
	}

}
