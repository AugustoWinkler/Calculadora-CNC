package model;



public class Material {
	private String name;
	private double price;



	public Material(String name, double price) {
		this.name = name;
		this.price = price;
	}

	@Override
	public String toString() {
		return "Material [name=" + name + ", price=" + price + "]";
	}

	// getters and setters
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
