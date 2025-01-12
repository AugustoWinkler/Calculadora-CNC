package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Calcular;
import model.Machine;
import model.Material;
import model.OpenTabs;
import model.Operacional;
import model.SavedMachines;
import model.SavedMaterials;
import model.SavedOperacional;

public class Controller {
	@FXML
	private ComboBox<String> machineCombo;
	@FXML
	private ComboBox<String> materialCombo;
	@FXML
	private ComboBox<String> operacinalCombo;
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

	private MachineInputController machineInputController;
	private MaterialInputController materialInputController;
	private OperacionalInputController operacionalInputController;

	@FXML
	public void initialize() {
		this.calcular = new Calcular();
		this.openTabs = new OpenTabs();
		updateMachineCombo();
		updateMaterialCombo();
		updateOperacionalCombo();
		
		

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
		operacinalCombo.getItems().clear();
		for (Operacional operacional : SavedOperacional.getInstance().getOperacional()) {
			operacinalCombo.getItems().add(operacional.getDesc());
			operacinalCombo.setValue(operacional.getDesc());
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
	private void addMaterial(ActionEvent event) throws IOException {
		
		materialInputController = openTabs.openAddTab("/view/MaterialInput.FXML", "Novo Material");
		
		if (materialInputController != null) {
			materialInputController.setMainController(this);
		} else {
			System.out.println("MaterialInputController está nulo!");
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
	private void Calc(ActionEvent event) {
		Machine machine = calcular.findMachine(machineCombo.getValue());
		Material material = calcular.findMaterial(materialCombo.getValue());
		Operacional operacional = calcular.findOperacional(operacinalCombo.getValue());
		double machineHour = calcular.calcTotal(
				machine.getValue(), 
				machine.getResidualValue(), 
				machine.getUsefulLife(), 
				machine.getLaserValue(),
				machine.getLaserUsefulLife(),
				operacional.getHoursPerDay(), 
				operacional.getDays(), 
				operacional.getOperatorValue(), 
				operacional.getOperacionalCost());
		hourMachinePrice.setText(String.format("%.2f R$", machineHour));
		double minuteMachine = machineHour/60;
		minuteMachinePrice.setText(String.format("%.2f R$", minuteMachine));
		double materialCostVar = calcular.calcMaterial(Double.parseDouble(materialHeigth.getText()), 
				Double.parseDouble(materialWidth.getText()), material);
		materialCost.setText(String.format("%.2f R$", materialCostVar));
		double productionValueCost = (machineHour * Double.parseDouble(hoursProduction.getText()) + materialCostVar);
		productionValue.setText(String.format("%.2f R$", productionValueCost));
		double estimatedProfitValue = (productionValueCost * Double.parseDouble(profit.getText())/100);
		estimatedProfit.setText(String.format("%.2f R$", estimatedProfitValue));
		double totalValueVar = productionValueCost + estimatedProfitValue;
		totalValue.setText(String.format("%.2f R$", totalValueVar));
	}
	
}
