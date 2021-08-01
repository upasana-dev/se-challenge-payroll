package com.wave.payroll.model.constants;

/**
 * Represents the different type of job groups an employee can be part of and
 * the corresponding wages
 *
 */
public enum JobGroup {

	A(20), B(30);

	private double hourlyWage;

	private JobGroup(double hourlyWage) {
		this.hourlyWage = hourlyWage;
	}

	public double getHourlyWage() {
		return hourlyWage;
	}

}
