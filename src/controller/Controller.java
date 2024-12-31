package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import model.Calcular;
import model.Machine;
import model.Material;
import model.OpenTabs;
import model.SavedMachines;
import model.SavedMaterials;

public class Controller {
	@FXML
	private ComboBox<String> machineCombo;
	@FXML
	private ComboBox<String> materialCombo;

	private Calcular calcular;
	private OpenTabs openTabs;

	private MachineInputController machineInputController;
	private MaterialInputController materialInputController;

	@FXML
	public void initialize() {
		this.calcular = new Calcular();
		this.openTabs = new OpenTabs();
		updateMachineCombo();
		updateMaterialCombo();

	}


	public void updateMachineCombo() {
		machineCombo.getItems().clear();
		for (Machine machine : SavedMachines.getInstance().getMachines()) {
			machineCombo.getItems().add(machine.getName());
		}
	}
	public void updateMaterialCombo() {
		materialCombo.getItems().clear();
		for (Material material : SavedMaterials.getInstance().getMaterial()) {
			materialCombo.getItems().add(material.getName());
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
		openTabs.openAddTab("/view/OperacionalInput.FXML", "Novo Operacional");
	}
}
