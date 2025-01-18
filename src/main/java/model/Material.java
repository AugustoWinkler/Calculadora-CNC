package model;



public class Material {
	private int id;
	private String name;
	private double price;



	public Material(String name, double price) {
		this.name = name;
		this.price = price;
	}
	public Material(int id,String name, double price) {
		this.id = id;
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

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setName(String name) {
		this.name = name;
	}


}
