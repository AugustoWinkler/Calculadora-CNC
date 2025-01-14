package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.AlertHelper;
import model.Calcular;
import model.ConfirmationHelper;
import model.Machine;
import model.Material;
import model.OpenTabs;
import model.Operacional;
import model.SavedMachines;
import model.SavedMaterials;
import model.SavedOperacional;
import model.validateTextField;

public class Controller {
	@FXML
	private ComboBox<String> machineCombo;
	@FXML
	private ComboBox<String> materialCombo;
	@FXML
	private ComboBox<String> operacionalCombo;
	@FXML
	private Label hourMachinePrice;
	@FXML
	private Label minuteMachinePrice;
	@FXML
	private Label materialCost;
	@FXML
	private Label productionValue;
	@FXML
	private Label estimatedProfit;
	@FXML
	private Label totalValue;

	@FXML
	private TextField materialHeigth;
	@FXML
	private TextField materialWidth;
	@FXML
	private TextField hoursProduction;
	@FXML
	private TextField profit;

	private Calcular calcular;
	private OpenTabs openTabs;
	private validateTextField validate;

	private MachineInputController machineInputController;
	private MaterialInputController materialInputController;
	private OperacionalInputController operacionalInputController;

	@FXML
	public void initialize() {

		this.calcular = new Calcular();
		this.openTabs = new OpenTabs();
		this.validate = new validateTextField();
		updateMachineCombo();
		updateMaterialCombo();
		updateOperacionalCombo();
		validate.validateTextInt(materialHeigth);
		validate.validateTextInt(materialWidth);
		validate.validateTextInt(hoursProduction);
		validate.validateTextInt(hoursProduction);

	}

	public void updateMachineCombo() {
		machineCombo.getItems().clear();
		for (Machine machine : SavedMachines.getInstance().getMachines()) {
			machineCombo.getItems().add(machine.getName());
			machineCombo.setValue(machine.getName());
		}
	}

	public void updateMaterialCombo() {
		materialCombo.getItems().clear();
		for (Material material : SavedMaterials.getInstance().getMaterial()) {
			materialCombo.getItems().add(material.getName());
			materialCombo.setValue(material.getName());
		}
	}

	public void updateOperacionalCombo() {
		operacionalCombo.getItems().clear();
		for (Operacional operacional : SavedOperacional.getInstance().getOperacional()) {
			operacionalCombo.getItems().add(operacional.getDesc());
			operacionalCombo.setValue(operacional.getDesc());
		}
	}

	@FXML
	private void addMachine(ActionEvent event) throws IOException {

		machineInputController = openTabs.openAddTab("/view/MachineInput.fxml", "Nova Máquina");

		if (machineInputController != null) {
			machineInputController.setMainController(this);
		} else {
			System.out.println("MachineInputController está nulo!");
		}
	}

	@FXML
	private void removeMachine(ActionEvent event) {
		if (machineCombo.getValue() != "(Nenhum)") {
			boolean confirmed = ConfirmationHelper.showConfirmation("Confirmação de Exclusão",
					"Deseja realmente excluir esta maquina?", "Esta ação não pode ser desfeita.");

			if (confirmed) {
				Machine machine = calcular.findMachine(machineCombo.getValue());
				SavedMachines.getInstance().removeMachine(machine);
				this.updateMachineCombo();
			}

		} else {
			AlertHelper.showAlert("Erro ao Remover Maquina", "Não foi possível remover está máquina",
					"Reinicie a aplicação e tente novamente, se o problema persistir contatar o suporte.");
		}

	}

	@FXML
	private void addMaterial(ActionEvent event) throws IOException {

		materialInputController = openTabs.openAddTab("/view/MaterialInput.FXML", "Novo Material");

		if (materialInputController != null) {
			materialInputController.setMainController(this);
		} else {
			System.out.println("MaterialInputController está nulo!");
		}
	}

	@FXML
	private void removeMaterial(ActionEvent event) {
		if (materialCombo.getValue() != "(Nenhum)") {
			boolean confirmed = ConfirmationHelper.showConfirmation("Confirmação de Exclusão",
					"Deseja realmente excluir este material?", "Esta ação não pode ser desfeita.");

			if (confirmed) {
				Material material = calcular.findMaterial(materialCombo.getValue());
				SavedMaterials.getInstance().removeMaterial(material);
				;
				this.updateMaterialCombo();
			}
		} else {
			AlertHelper.showAlert("Erro ao remover Material", "Não foi possivel remover este material",
					"Reinicie a aplicação se o erro persistir contatar o suporte.");
		}
	}

	@FXML
	private void addOperacional(ActionEvent event) throws IOException {

		operacionalInputController = openTabs.openAddTab("/view/OperacionalInput.FXML", "Novo Horario");

		if (operacionalInputController != null) {
			operacionalInputController.setMainController(this);
		} else {
			System.out.println("Operacinal InputController está nulo!");
		}
	}

	@FXML
	private void removeOperacional(ActionEvent envet) {
		if (operacionalCombo.getValue() != "(Padrão)") {
			boolean confirmed = ConfirmationHelper.showConfirmation("Confirmação de Exclusão",
					"Deseja realmente excluir este operacional?", "Esta ação não pode ser desfeita.");

			if (confirmed) {
				Operacional operacional = calcular.findOperacional(operacionalCombo.getValue());
				SavedOperacional.getInstance().removeOperacional(operacional);
				this.updateOperacionalCombo();
			}

		} else {
			AlertHelper.showAlert("Erro ao remover Operacional", "Não foi possível remover esse Operacinal",
					"Reinicie a aplicação e tente novamente se o erro persistir contatar o suporte.");
		}
	}

	@FXML
	private void Calc(ActionEvent event) {
		double machineHour;
		double minuteMachine;
		double materialCostVar;
		double productionValueCost;
		double estimatedProfitValue;
		double total;

		Machine machine = calcular.findMachine(machineCombo.getValue());
		Material material = calcular.findMaterial(materialCombo.getValue());
		Operacional operacional = calcular.findOperacional(operacionalCombo.getValue());

		if (machine.getName().equals("(Nenhum)")) {
			machineHour = 0;
			minuteMachine = 0;
		} else if (machine.getLaserValue() <= 0 || machine.getLaserUsefulLife() <= 0) {
			machineHour = calcular.calcTotal(machine.getValue(), machine.getResidualValue(), machine.getUsefulLife(),
					operacional.getHoursPerDay(), operacional.getDays(), operacional.getOperatorValue(),
					operacional.getOperacionalCost());

			minuteMachine = machineHour / 60;
		} else {
			machineHour = calcular.calcTotal(machine.getValue(), machine.getResidualValue(), machine.getUsefulLife(),
					machine.getLaserValue(), machine.getLaserUsefulLife(), operacional.getHoursPerDay(),
					operacional.getDays(), operacional.getOperatorValue(), operacional.getOperacionalCost());

			minuteMachine = machineHour / 60;
		}

		if (material.getName().equals("(Nenhum)") || materialWidth.getText().equals("")
				|| materialWidth.getText().equals("")) {
			materialCostVar = 0;
		} else {
			materialCostVar = calcular.calcMaterial(Double.parseDouble(materialHeigth.getText()),
					Double.parseDouble(materialWidth.getText()), material);

		}

		productionValueCost = (machineHour * Double.parseDouble(hoursProduction.getText()) + materialCostVar);

		if (profit.getText().equals("") || Double.parseDouble(profit.getText()) <= 0) {
			estimatedProfitValue = 0;
		} else {
			estimatedProfitValue = (productionValueCost * Double.parseDouble(profit.getText()) / 100);
		}

		total = productionValueCost + estimatedProfitValue;

		this.updateLabels(machineHour, minuteMachine, materialCostVar, productionValueCost, estimatedProfitValue,
				total);
	}

	public void updateLabels(double machineHour, double minuteMachine, double materialCostVar, double productionCost,
			double profit, double total) {

		hourMachinePrice.setText(String.format("%.2f R$", machineHour));
		minuteMachinePrice.setText(String.format("%.2f R$", minuteMachine));
		materialCost.setText(String.format("%.2f R$", materialCostVar));
		productionValue.setText(String.format("%.2f R$", productionCost));
		estimatedProfit.setText(String.format("%.2f R$", profit));
		totalValue.setText(String.format("%.2f R$", total));
	}

}
