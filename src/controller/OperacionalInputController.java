package controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Operacional;
import model.SavedOperacional;

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
  
    

    @FXML
    public void addOperacional(Event e) {
    	Operacional newOperacional = new Operacional(
    			txtDescOperacional,
    			txtDaysOfWork,
    			txtHoursPerDay,
    			txtOperacionalCosts,
    			txtOperatorValue		
         );
        SavedOperacional.getInstance().addOperacional(newOperacional);
        System.out.println("Material adicionado: " + newOperacional.getDesc());
        
        if (mainController != null) {
        	mainController.updateOperacionalCombo(); 
		} else {
			System.out.println("mainController está nulo!");
		}
	}


	public void setMainController(Controller mainController) {
		this.mainController = mainController;	
	}

}
