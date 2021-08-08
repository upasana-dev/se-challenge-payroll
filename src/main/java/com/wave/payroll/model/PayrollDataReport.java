package com.wave.payroll.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PayrollDataReport {

	public PayrollDataReport(String reportName) {
		super();
		this.reportName = reportName;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	/**
	 * This is an internal ID that will not be exposed to the customer
	 */
	private Long id;

	private String reportName;

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

}
