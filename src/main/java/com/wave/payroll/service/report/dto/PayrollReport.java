package com.wave.payroll.service.report.dto;

import java.util.List;

/**
 * Structure containing the payroll data ({@link EmployeeReport} of all
 * applicable employees
 */
public class PayrollReport {

	private List<EmployeeReport> employeeReports;

	public List<EmployeeReport> getEmployeeReports() {
		return employeeReports;
	}

	public void setEmployeeReports(List<EmployeeReport> employeeReports) {
		this.employeeReports = employeeReports;
	}
}
