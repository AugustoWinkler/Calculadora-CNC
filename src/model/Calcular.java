package model;

import javafx.scene.control.TextField;

public class Calcular {
	
	
	
	
	public Double calcTotal(
			TextField valorMaquinaTf,
			TextField valorResidualTf,
			TextField vidaUtilTf,
			TextField valorLaserTf,
			TextField vidaUtilLaserTf,
			TextField horasTrabalhoTf,
			TextField diasTrabalhoTf,
			TextField salarioOperadorTf,
			TextField despezasTf) {
		

		
		double maquina = (this.calcDepreciacaoMaquina(valorMaquinaTf, valorResidualTf, vidaUtilTf) / 12) / this.calcHorasTrabalho(horasTrabalhoTf, diasTrabalhoTf);
		
		
		double laser = this.calcDepreciacaoLaser(valorLaserTf, vidaUtilLaserTf);
		double trabalho =this.calcHorasTrabalho(horasTrabalhoTf, diasTrabalhoTf);
		double salario = this.calcSalarioOperadorEDespezas(salarioOperadorTf, despezasTf)/this.calcHorasTrabalho(horasTrabalhoTf, diasTrabalhoTf);
		
		System.out.println(maquina+laser+salario);
		return maquina + laser + salario;
	}
	
	
	
	public Double calcDepreciacaoMaquina(TextField valorMaquinaTf, TextField valorResidualTf, TextField vidaUtilTf) {
		double valorMaquina = Double.parseDouble(valorMaquinaTf.getText());
		double valorResidual = Double.parseDouble(valorResidualTf.getText());
		double vidaUtil = Double.parseDouble(vidaUtilTf.getText());
		
		double valor = (valorMaquina - valorResidual) / vidaUtil;
		
		
		System.out.println("calcDepreciacaoMaquina" + valor);
		return valor;
	}
	
	public Double calcDepreciacaoLaser(TextField valorLaserTf, TextField vidaUtilLaserTf) {
		double valorLaser = Double.parseDouble(valorLaserTf.getText());
		double vidaUtil = Double.parseDouble(vidaUtilLaserTf.getText());
		
		System.out.println("calcDepreciacaolaser" + (valorLaser/vidaUtil));
		return valorLaser/vidaUtil;
	}
	
	public Double calcHorasTrabalho(TextField horasTrabalhoTf, TextField diasTrabalhoTf) {
		double horasTrabalho = Double.parseDouble(horasTrabalhoTf.getText());
		double diasTrabalho = Double.parseDouble(diasTrabalhoTf.getText());
		
		System.out.println("calcHorasTrabalho" + (horasTrabalho*diasTrabalho));
		return horasTrabalho * diasTrabalho;
	}
	
	public double calcSalarioOperadorEDespezas(TextField salarioOperadorTf,TextField despezasTf) {
		double salarioOperador = Double.parseDouble(salarioOperadorTf.getText());
		double despezas = Double.parseDouble(despezasTf.getText());
		
		System.out.println("salarioOperador" + (salarioOperador + despezas));
		return salarioOperador + despezas;
	}

}
