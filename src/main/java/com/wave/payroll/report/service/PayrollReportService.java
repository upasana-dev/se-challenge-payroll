package com.wave.payroll.report.service;

import com.wave.payroll.service.report.dto.PayrollReport;

/**
 * Performs logic specific to the generation of the Payroll Report
 *
 */
public interface PayrollReportService {

	/**
	 * Generates the Payroll Report based on all the recorded information to date
	 * 
	 * @return {@link PayrollReport}
	 */
	public PayrollReport generatePayrollReport();

}
