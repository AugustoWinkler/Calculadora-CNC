package controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Calcular;

public class Controller {
	
	@FXML
    private TextField valorMaquina;
	@FXML
	private TextField valorResidual;
	@FXML
    private TextField vidaUtil;
	@FXML
	private TextField valorLaser;
	@FXML
    private TextField vidaUtilLaser;
	@FXML
	private TextField diasTrabalho;
	@FXML
    private TextField horasTrabalho;
	@FXML
	private TextField porcentagemGanho;
	@FXML
	private TextField salarioOperador;
	@FXML
	private TextField despezas;
	
	@FXML
	private Label lblResultado;
	@FXML
	private Label lblTotal;
	
	
	

    private Calcular calcular;
	
	
	
    @FXML
    public void initialize() {
        this.calcular = new Calcular(); 
        
    }
    
	@FXML
	public void updateText(ActionEvent event)  {
		lblResultado.setText("Total: " + calcular.calcTotal(valorMaquina, valorResidual, vidaUtil, valorLaser, vidaUtilLaser, horasTrabalho, diasTrabalho, salarioOperador, despezas));
		}
}
