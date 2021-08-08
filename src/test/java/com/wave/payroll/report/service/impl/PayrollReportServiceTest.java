package com.wave.payroll.report.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.wave.payroll.service.report.dto.EmployeeReport;
import com.wave.payroll.service.report.dto.PayoutPeriod;

public class PayrollReportServiceTest {

	private PayrollReportServiceImpl service = new PayrollReportServiceImpl();

	@Test
	public void testGenerateEmployeeReport_decimalWage() {
		Long businessId = 3L;

		PayoutPeriod payoutPeriod = new PayoutPeriod(LocalDate.now().minusDays(15), LocalDate.now());

		EmployeeReport generatedReport = service.generateEmployeeReport(payoutPeriod, 48.50, businessId);
		assertNotNull(generatedReport);
		assertEquals(businessId, generatedReport.getEmployeeId());
		assertEquals(payoutPeriod, generatedReport.getPayPeriod());
		assertEquals("$48.50", generatedReport.getAmountPaid());
	}

	@Test
	public void testGenerateEmployeeReport_wholeNumberWage() {
		Long businessId = 35L;

		PayoutPeriod payoutPeriod = new PayoutPeriod(LocalDate.now().minusDays(15), LocalDate.now());

		EmployeeReport generatedReport = service.generateEmployeeReport(payoutPeriod, 100, businessId);
		assertNotNull(generatedReport);
		assertEquals(businessId, generatedReport.getEmployeeId());
		assertEquals(payoutPeriod, generatedReport.getPayPeriod());
		assertEquals("$100.00", generatedReport.getAmountPaid());
	}

}
