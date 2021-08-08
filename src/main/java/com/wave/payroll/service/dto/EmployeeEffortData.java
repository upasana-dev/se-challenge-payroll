package com.wave.payroll.service.dto;

import java.time.LocalDate;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

public class EmployeeEffortData {

	public EmployeeEffortData() {
		super();
	}

	public EmployeeEffortData(LocalDate effortDate, double hoursWorked, Long employeeBusinessId, String jobGroupName) {
		super();
		this.effortDate = effortDate;
		this.hoursWorked = hoursWorked;
		this.employeeBusinessId = employeeBusinessId;
		this.jobGroupName = jobGroupName;
	}

	@CsvDate(value = "d/M/yyyy")
	@CsvBindByName(column = "date")
	private LocalDate effortDate;

	@CsvBindByName(column = "hours worked")
	private double hoursWorked;

	@CsvBindByName(column = "employee id")
	private Long employeeBusinessId;

	@CsvBindByName(column = "job group")
	private String jobGroupName;

	public LocalDate getEffortDate() {
		return effortDate;
	}

	public void setEffortDate(LocalDate effortDate) {
		this.effortDate = effortDate;
	}

	public double getHoursWorked() {
		return hoursWorked;
	}

	public void setHoursWorked(double hoursWorked) {
		this.hoursWorked = hoursWorked;
	}

	public Long getEmployeeBusinessId() {
		return employeeBusinessId;
	}

	public void setEmployeeBusinessId(Long employeeBusinessId) {
		this.employeeBusinessId = employeeBusinessId;
	}

	public String getJobGroupName() {
		return jobGroupName;
	}

	public void setJobGroupName(String jobGroupName) {
		this.jobGroupName = jobGroupName;
	}

}
