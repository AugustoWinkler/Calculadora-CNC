package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
	private Label horaMaquinaValor;

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
		double horaMaquina = calcular.calcTeste(machine.getValue());
		horaMaquinaValor.setText(Double.toString(horaMaquina));
		
	}
	
}
