package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import model.Calcular;
import model.Machine;
import model.OpenTabs;
import model.SavedMachines;

public class Controller {
    @FXML
    private ComboBox<String> machineCombo;

    private Calcular calcular;
    private OpenTabs openTabs;

    private MachineInputController machineInputController; 
    @FXML
    public void initialize() {
        this.calcular = new Calcular(); 
        this.openTabs = new OpenTabs();
        updateMachineCombo();

    }
    
    public void refresh() {
    	this.updateMachineCombo();
    }
    public void updateMachineCombo() {
        machineCombo.getItems().clear();
        for (Machine machine : SavedMachines.getInstance().getMachines()) {
            machineCombo.getItems().add(machine.getName());
        }
    }
    


    @FXML
    private void addMachine(ActionEvent event) throws IOException {
        openTabs.openAddTab("/view/MachineInput.FXML", "Nova Máquina");
    }

    @FXML
    private void addMaterial(ActionEvent event) throws IOException {
        openTabs.openAddTab("/view/MaterialInput.FXML", "Novo Material");
    }

    @FXML
    private void addOperacional(ActionEvent event) throws IOException {
        openTabs.openAddTab("/view/OperacionalInput.FXML", "Novo Operacional");
    }
}
