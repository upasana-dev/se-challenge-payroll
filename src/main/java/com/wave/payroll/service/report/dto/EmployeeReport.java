package com.wave.payroll.service.report.dto;

public class EmployeeReport {

	private Long employeeId;

	private PayoutPeriod payPeriod;

	private String amountPaid;

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

	public String getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(String amountPaid) {
		this.amountPaid = amountPaid;
	}

	public EmployeeReport(Long employeeId, PayoutPeriod payPeriod, String amountPaid) {
		super();
		this.employeeId = employeeId;
		this.payPeriod = payPeriod;
		this.amountPaid = amountPaid;
	}

	public EmployeeReport() {
		super();
	}

	public static String convertAmountPaid(double amount) {
		StringBuilder builder = new StringBuilder();
		builder.append('$');
		builder.append(String.format("%.2f", amount));

		return builder.toString();
	}
}
