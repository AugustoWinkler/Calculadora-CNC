package model;

import javafx.scene.control.TextField;

public class Calcular {
	
	

	
	
	public Machine findMachine(String name) {
		for(Machine machine : SavedMachines.getInstance().getMachines()) {
			if(machine.getName().equals(name)) {
				System.out.println(machine.toString());
				return machine;
			}
		}
		return null;
	}
	public Material findMaterial(String name) {
		for(Material material : SavedMaterials.getInstance().getMaterial()) {
			if(material.getName().equals(name)) {
				System.out.println(material.toString());
				return material;
			}
		}
		return null;
	}
	public Operacional findOperacional(String name) {
		for(Operacional operacional : SavedOperacional.getInstance().getOperacional()) {
			if(operacional.getDesc().equals(name)) {
				System.out.println(operacional.toString());
				return operacional;
			}
		}
		return null;
	}
	
	
	public Double calcTotal(
			double machineValue,
			double residualValue,
			double usefulLife,
			double laserValue,
			double laserUsefulLife,
			double hoursWork,
			double daysWork,
			double operatorSalary,
			double expenses) {
		

		
		double maquina = (this.calcDepreciacaoMaquina(machineValue, residualValue, usefulLife) / 12) / this.calcHoursOfWork(hoursWork, daysWork);
		double laser = this.calcDepreciacaoLaser(laserValue, laserUsefulLife);
		double salario = this.calcOperatorSalaryAndExpenses(operatorSalary, expenses)/this.calcHoursOfWork(hoursWork, daysWork);
		
		return maquina + laser + salario;
	}
	
	
	
	public Double calcDepreciacaoMaquina(double machineValue, double residualValue , double usefulLife) {
	
		return (machineValue - residualValue) / usefulLife;
	}
	
	public Double calcDepreciacaoLaser(double laserValue, double laserUsefulLife) {
		
		return laserValue/laserUsefulLife;
	}
	
	public Double calcHoursOfWork(double hoursWork, double daysWork) {
		
		
		return hoursWork * daysWork;
	}
	
	public double calcOperatorSalaryAndExpenses(double operatorSalary,double expenses) {
		
		return operatorSalary + expenses;
	}
	
	public double calcMaterial(double heigth, double width, Material material ) {
		return ((heigth/1000) * (width /1000)) * material.getValue();
	}

}
