package model;

import javafx.scene.control.TextField;

public class Machine {
	private String name;
	private double value;
	private double usefulLife;
	private double residualValue;
	private double laserValue;
	private double laserUsefulLife;
	
	
	public Machine (TextField name, 
					TextField value, 
					TextField usefulLife,
					TextField residualValue,
					TextField laserValue,
					TextField laserUsefulLife) {
		
		this.name = name.getText();;
		this.value = Double.parseDouble(value.getText());
		this.usefulLife = Double.parseDouble(usefulLife.getText());
		this.residualValue = Double.parseDouble(residualValue.getText());
		this.laserValue = Double.parseDouble(laserValue.getText());
		this.laserUsefulLife = Double.parseDouble(laserUsefulLife.getText());
	}
	
	public Machine(String name, double value, double usefulLife, double residualValue, double laserValue,
			double laserUsefulLife) {
		super();
		this.name = name;
		this.value = value;
		this.usefulLife = usefulLife;
		this.residualValue = residualValue;
		this.laserValue = laserValue;
		this.laserUsefulLife = laserUsefulLife;
	}

	
	
	
	
	@Override
	public String toString() {
		return "Machine [name=" + name + ", value=" + value + ", usefulLife=" + usefulLife + ", residualValue="
				+ residualValue + ", laserValue=" + laserValue + ", laserUsefulLife=" + laserUsefulLife + "]";
	}

	//getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double getUsefulLife() {
		return usefulLife;
	}

	public void setUsefulLife(double usefulLife) {
		this.usefulLife = usefulLife;
	}

	public double getResidualValue() {
		return residualValue;
	}

	public void setResidualValue(double residualValue) {
		this.residualValue = residualValue;
	}

	public double getLaserValue() {
		return laserValue;
	}

	public void setLaserValue(double laserValue) {
		this.laserValue = laserValue;
	}

	public double getLaserUsefulLife() {
		return laserUsefulLife;
	}

	public void setLaserUsefulLife(double laserUsefulLife) {
		this.laserUsefulLife = laserUsefulLife;
	}
	

	
}
