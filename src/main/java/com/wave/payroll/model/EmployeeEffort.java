package com.wave.payroll.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.wave.payroll.model.constants.JobGroup;

@Entity
public class EmployeeEffort {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	/**
	 * This is an internal ID that will not be exposed to the customer
	 */
	private Long id;

	private LocalDate effortDate;

	private double hoursWorked;

	@Enumerated(EnumType.STRING)
	private JobGroup wageGroup;

	public LocalDate getEffortDate() {
		return effortDate;
	}

	public void setEffortDate(LocalDate date) {
		this.effortDate = date;
	}

	public double getHoursWorked() {
		return hoursWorked;
	}

	public void setHoursWorked(double hoursWorked) {
		this.hoursWorked = hoursWorked;
	}

	public JobGroup getWageGroup() {
		return wageGroup;
	}

	public void setWageGroup(JobGroup wageGroup) {
		this.wageGroup = wageGroup;
	}

}
