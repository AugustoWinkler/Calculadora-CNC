package application;

public class Function {
	
	public int HoursWorked(int days, int hours) {
		int hoursWorked = hours * days;
		return hoursWorked;
	}
	
	public int CalcOperatingHours(int expenses, int operator , int hoursWorked) {
		int resultOperatingHours = ((expenses + operator)/hoursWorked);
		
		return resultOperatingHours;
	}
	
	public int CalcMachine(int machineValue, int machineLifespan, int residualValue, int laserValue, int laserLifespan) {
		int calcMachineLifespan = ((machineValue - residualValue) / machineLifespan);
		int calcLaserLifespan = (laserValue/laserLifespan);
		
		
		return calcMachineLifespan + calcLaserLifespan;
	}
	
	public int calcMaterial(int price, int amount) {
		
		return price / amount;
	}
}
