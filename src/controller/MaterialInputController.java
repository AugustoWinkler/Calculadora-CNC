package controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Material;
import model.SavedMaterials;

public class MaterialInputController {
	private Controller mainController;
    @FXML
    private TextField txtMaterialName;
    @FXML
    private TextField txtPriceMaterial;
  
    

    @FXML
    public void addMaterial(Event e) {
        Material newMaterial = new Material(
        		txtMaterialName,
        		txtPriceMaterial
         );
        SavedMaterials.getInstance().addMaterial(newMaterial);
        System.out.println("Material adicionado: " + newMaterial.getName());
        
        if (mainController != null) {
        	mainController.updateMaterialCombo(); 
		} else {
			System.out.println("mainController está nulo!");
		}
	}


	public void setMainController(Controller mainController) {
		this.mainController = mainController;	
	}

}
