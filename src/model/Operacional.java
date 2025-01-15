package model;

public class Operacional {
	private int id;
	private String desc;
	private double days;
	private double hoursPerDay;
	private double operacionalCost;
	private double operatorValue;
	
	
	public Operacional(int id, String desc, double days, double hoursPerDay, double operacionalCost, double operatorValue) {
		this.id = id;
		this.desc = desc;
		this.days = days;
		this.hoursPerDay = hoursPerDay;
		this.operacionalCost = operacionalCost;
		this.operatorValue = operatorValue;

	}

	public Operacional(String desc, double days, double hoursPerDay, double operacionalCost, double operatorValue) {
		this.desc = desc;
		this.days = days;
		this.hoursPerDay = hoursPerDay;
		this.operacionalCost = operacionalCost;
		this.operatorValue = operatorValue;

	}

	@Override
	public String toString() {
		return "Operacional [desc=" + desc + ", days=" + days + ", hoursPerDay=" + hoursPerDay + ", operacionalCost="
				+ operacionalCost + ", operatorValue=" + operatorValue + "]";
	}

	// getters and setters
	
	
	
	public String getDesc() {
		return desc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public double getDays() {
		return days;
	}

	public void setDays(double days) {
		this.days = days;
	}

	public double getHoursPerDay() {
		return hoursPerDay;
	}

	public void setHoursPerDay(double hoursPerDay) {
		this.hoursPerDay = hoursPerDay;
	}

	public double getOperacionalCost() {
		return operacionalCost;
	}

	public void setOperacionalCost(double operacionalCost) {
		this.operacionalCost = operacionalCost;
	}

	public double getOperatorValue() {
		return operatorValue;
	}

	public void setOperatorValue(double operatorValue) {
		this.operatorValue = operatorValue;
	}

}
