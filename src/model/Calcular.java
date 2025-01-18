package model;

public class Calcular {

	public Double calcTotal(double machineValue, double residualValue, double usefulLife, double laserValue,
			double laserUsefulLife, double hoursWork, double daysWork, double operatorSalary, double expenses) {

		double maquina = (this.calcDepreciacaoMaquina(machineValue, residualValue, usefulLife) / 12)
				/ this.calcHoursOfWork(hoursWork, daysWork);
		double laser = this.calcDepreciacaoLaser(laserValue, laserUsefulLife);
		double salario = this.calcOperatorSalaryAndExpenses(operatorSalary, expenses)
				/ this.calcHoursOfWork(hoursWork, daysWork);
		System.out.println(maquina);
		System.out.println(laser);
		System.out.println(salario);
		return maquina + laser + salario;
	}

	public Double calcDepreciacaoMaquina(double machineValue, double residualValue, double usefulLife) {

		return (machineValue - residualValue) / usefulLife;
	}

	public Double calcDepreciacaoLaser(double laserValue, double laserUsefulLife) {
		if (laserValue <= 0 || laserUsefulLife <= 0) {
			return (double) 0;
		}
		return laserValue / laserUsefulLife;
	}

	public Double calcHoursOfWork(double hoursWork, double daysWork) {

		return hoursWork * daysWork;
	}

	public double calcOperatorSalaryAndExpenses(double operatorSalary, double expenses) {
		return operatorSalary + expenses;
	}

	public double calcMaterial(double heigth, double width, Material material) {
		return ((heigth / 1000) * (width / 1000)) * material.getPrice();
	}

}
