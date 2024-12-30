package controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Machine;
import model.SavedMachines;

public class MachineInputController {

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
    

    @FXML
    public void addMachine(Event e) {
        Machine newMachine = new Machine(
        		txtNameMachine,
        		txtValueMachine,
        		txtUsefulLife,
        		txtResidualValue,
        		txtLaserValue,
        		txtLaserUsefulLife
        );
        SavedMachines.getInstance().addMachine(newMachine);
        
        System.out.println("Máquina adicionada: " + newMachine.getName());

    }

}
