package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Controller {

    @FXML
    public void handleAddMachine() {
        try {
            // Load the MachineInput.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MachineInput.fxml"));
            Parent root = loader.load();

            // Create a new stage for the popup
            Stage stage = new Stage();
            stage.setTitle("Adicionar Máquina");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void handleAddMaterial() {
        try {
            // Load the MachineInput.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MaterialInput.fxml"));
            Parent root = loader.load();

            // Create a new stage for the popup
            Stage stage = new Stage();
            stage.setTitle("Adicionar Máquina");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void handleAddOperacional() {
        try {
            // Load the MachineInput.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/OperacionalInput.fxml"));
            Parent root = loader.load();

            // Create a new stage for the popup
            Stage stage = new Stage();
            stage.setTitle("Adicionar Máquina");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public Label lblResultTotal;

    @FXML
    public void handleCalcular() {
    	lblResultTotal.setText("10,0R$");
    }
}
