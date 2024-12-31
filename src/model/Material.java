package model;

import javafx.scene.control.TextField;

public class Material {
	private String name;
	private double price;

	
	
	public Material (TextField name, 
					TextField price) {
		
		this.name = name.getText();;
		this.price = Double.parseDouble(price.getText());
	}
	public Material(String name, double price) {
		super();
		this.name =  name;
		this.price = price;
	}
	
	
	
	//getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getValue() {
		return price;
	}

	public void setValue(double value) {
		this.price = value;
	}


	

	
}
