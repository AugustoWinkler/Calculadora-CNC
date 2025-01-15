package controller;

import java.io.IOException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.AlertHelper;
import model.Calcular;
import model.ConfirmationHelper;
import model.DataBaseConnection;
import model.Machine;
import model.MachineDAO;
import model.Material;
import model.MaterialDAO;
import model.OpenTabs;
import model.Operacional;
import model.OperacionalDAO;
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
		List<Machine> machines = new MachineDAO(DataBaseConnection.connect()).getAllMachines();

		for (Machine machine : machines) {
			machineCombo.getItems().add(machine.getName());
		}
	}

	public void updateMaterialCombo() {
		materialCombo.getItems().clear();

		List<Material> materials = new MaterialDAO(DataBaseConnection.connect()).getAllMaterials();
		for (Material material : materials) {
			materialCombo.getItems().add(material.getName());
		}

	}

	public void updateOperacionalCombo() {
		operacionalCombo.getItems().clear();
		List<Operacional> operacionals = new OperacionalDAO(DataBaseConnection.connect()).getAllOperacionals();

		for (Operacional operacional : operacionals) {
			operacionalCombo.getItems().add(operacional.getDesc());
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
		boolean confirmed = ConfirmationHelper.showConfirmation("Remover Máquina", "essa ação não poderá ser desfeita.",
				"Tem certeza que deseja remover esta máquina?");
		if (confirmed) {
			MachineDAO.removeMachine(machineCombo.getValue());
			this.updateMachineCombo();
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
		boolean confirmed = ConfirmationHelper.showConfirmation("Remover Material", "essa ação não poderá ser desfeita",
				"Tem certeza de que deseja remover esta máquina?");
		if (confirmed) {
			MaterialDAO.removeMaterial(materialCombo.getValue());
			this.updateMaterialCombo();
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
		boolean confirmed = ConfirmationHelper.showConfirmation("Remover Operacional",
				"essa ação não poderá ser desfeita", "Tem certeza de que deseja remover este operacional?");
		if (confirmed) {
			OperacionalDAO.removeOperacional(operacionalCombo.getValue());
			this.updateOperacionalCombo();
		}
	}

	@FXML
	private void Calc(ActionEvent event) {
		double machineHour = 0;
		double minuteMachine = 0;
		double materialCostVar;
		double productionValueCost;
		double estimatedProfitValue;
		double total;

		Machine machine = MachineDAO.findMachine(machineCombo.getValue());
		Material material = MaterialDAO.findMaterial(materialCombo.getValue());
		Operacional operacional = OperacionalDAO.findOperacional(operacionalCombo.getValue());

		System.out.println(machine.toString());
		if (machine.getName().equals("(Nenhum)")) {
			AlertHelper.showAlert("Nenhuma máquina selecionada", "Você precisa selecionar uma máquina",
					"caso não exista nenhuma máquina você pode criar uma apertando no botão adicionar");
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
