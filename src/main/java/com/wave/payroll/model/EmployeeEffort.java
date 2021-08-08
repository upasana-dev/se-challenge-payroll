package com.wave.payroll.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.wave.payroll.model.constants.JobGroup;

/**
 * Represents the job effort for a specific {@link Employee} on a specific day
 */
@Entity
public class EmployeeEffort {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	/**
	 * This is an internal ID that will not be exposed to the customer
	 */
	private Long id;
	/**
	 * The date for which the job effort is recorded
	 */
	private LocalDate effortDate;

	/**
	 * Number of hours of worked by the employee
	 */
	private double hoursWorked;

	/**
	 * Category of work performed by the Employee
	 */
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
