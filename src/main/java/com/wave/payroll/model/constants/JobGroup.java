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

	public static JobGroup convertToJobGroup(String groupName) {
		return switch (groupName) {
		case "A" -> A;
		case "B" -> B;
		default -> throw new RuntimeException("Invalid Job Group");
		};
	}

}
