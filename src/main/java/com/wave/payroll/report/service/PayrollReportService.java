package com.wave.payroll.report.service;

import com.wave.payroll.service.dto.PayrollReport;

/**
 * Performs logic specific to the generation of the payroll report
 *
 */
public interface PayrollReportService {

	/**
	 * Generates the Payroll Report based on recorded information
	 * 
	 * @return {@link PayrollReport}
	 */
	public PayrollReport generatePayrollReport();

}
