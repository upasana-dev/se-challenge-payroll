package com.wave.payroll.service.dto;

public class EmployeeReport {

	private Long employeeId;

	private PayoutPeriod payPeriod;

	private double amountPaid;

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public PayoutPeriod getPayPeriod() {
		return payPeriod;
	}

	public void setPayPeriod(PayoutPeriod payPeriod) {
		this.payPeriod = payPeriod;
	}

	public double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public EmployeeReport(Long employeeId, PayoutPeriod payPeriod, double amountPaid) {
		super();
		this.employeeId = employeeId;
		this.payPeriod = payPeriod;
		this.amountPaid = amountPaid;
	}

	public EmployeeReport() {
		super();
	}
}
