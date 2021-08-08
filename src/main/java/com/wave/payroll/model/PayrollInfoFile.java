package com.wave.payroll.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PayrollInfoFile {

	public PayrollInfoFile(String reportName) {
		super();
		this.fileName = reportName;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	/**
	 * This is an internal ID that will not be exposed to the customer
	 */
	private Long id;

	private String fileName;

	public String getFileName() {
		return fileName;
	}

	public void setReportName(String fileName) {
		this.fileName = fileName;
	}

}
