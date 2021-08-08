package com.wave.payroll.report.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.wave.payroll.model.constants.JobGroup;
import com.wave.payroll.service.dto.PayoutPeriod;

public class PayrollDataServiceTest {

	private PayrollReportDataServiceImpl service = new PayrollReportDataServiceImpl();

	@Test
	public void testCalculateTotalWagesForPayoutPeriod_onlyEntryForPayoutPeriod_otherPreexistingPeriods() {

		PayoutPeriod currentPayoutPeriod = new PayoutPeriod(LocalDate.now(), LocalDate.now().plusDays(3));
		PayoutPeriod existingPayoutPeriod = new PayoutPeriod(LocalDate.now().minusMonths(2),
				LocalDate.now().minusMonths(2).plusDays(3));

		// The map already has a pre-existing mapping for another period, but none for
		// the current period
		Map<PayoutPeriod, Double> workToPeriodMapping = new HashMap<>();
		workToPeriodMapping.put(existingPayoutPeriod, 25.60);

		service.addWagesForPayoutPeriod(workToPeriodMapping, currentPayoutPeriod, 36.17);

		assertEquals(2, workToPeriodMapping.size());
		assertEquals(25.60, workToPeriodMapping.get(existingPayoutPeriod));
		assertEquals(36.17, workToPeriodMapping.get(currentPayoutPeriod));
	}

	@Test
	public void testCalculateTotalWagesForPayoutPeriod_multipleEntriesForPayoutPeriod_otherPreexistingPeriods() {

		PayoutPeriod currentPayoutPeriod = new PayoutPeriod(LocalDate.now(), LocalDate.now().plusDays(3));
		PayoutPeriod existingPayoutPeriod = new PayoutPeriod(LocalDate.now().minusMonths(2),
				LocalDate.now().minusMonths(2).plusDays(3));

		// The map already has a pre-existing mapping for another period, and one for
		// the current payout period
		Map<PayoutPeriod, Double> workToPeriodMapping = new HashMap<>();
		workToPeriodMapping.put(existingPayoutPeriod, 25.60);
		workToPeriodMapping.put(currentPayoutPeriod, 63.83);

		service.addWagesForPayoutPeriod(workToPeriodMapping, currentPayoutPeriod, 36.17);

		assertEquals(2, workToPeriodMapping.size());
		assertEquals(25.60, workToPeriodMapping.get(existingPayoutPeriod));
		assertEquals(100, workToPeriodMapping.get(currentPayoutPeriod));
	}

	@Test
	public void testCalculateTotalWagesForPayoutPeriod_onlyEntryForPayoutPeriod_emptyMap() {

		PayoutPeriod currentPayoutPeriod = new PayoutPeriod(LocalDate.now(), LocalDate.now().plusDays(3));

		// Empty map
		Map<PayoutPeriod, Double> workToPeriodMapping = new HashMap<>();

		service.addWagesForPayoutPeriod(workToPeriodMapping, currentPayoutPeriod, 36.17);

		assertEquals(1, workToPeriodMapping.size());
		assertEquals(36.17, workToPeriodMapping.get(currentPayoutPeriod));
	}

	@Test
	public void testCalculateWages() {
		assertEquals(180, service.calculateWages(9, JobGroup.A));
		assertEquals(270, service.calculateWages(9, JobGroup.B));
		assertEquals(680, service.calculateWages(34, JobGroup.A));
		assertEquals(2610, service.calculateWages(87, JobGroup.B));
	}

	@Test
	public void testFindApplicablePayoutPeriod_firstHalfOfTheMonth() {

		LocalDate jobDate = LocalDate.of(2021, 5, 5);

		LocalDate expectedStartDate = LocalDate.of(2021, 5, 1);
		LocalDate expectedEndDate = LocalDate.of(2021, 5, 15);

		PayoutPeriod payoutPeriod = service.findApplicablePayoutPeriod(jobDate);

		assertNotNull(payoutPeriod);
		assertEquals(expectedStartDate, payoutPeriod.getStartDate());
		assertEquals(expectedEndDate, payoutPeriod.getEndDate());
	}

	@Test
	public void testFindApplicablePayoutPeriod_secondHalfOfTheMonth_nonFebruaryMonth() {

		LocalDate jobDate = LocalDate.of(2000, 8, 20);

		LocalDate expectedStartDate = LocalDate.of(2000, 8, 16);
		LocalDate expectedEndDate = LocalDate.of(2000, 8, 31);

		PayoutPeriod payoutPeriod = service.findApplicablePayoutPeriod(jobDate);

		assertNotNull(payoutPeriod);
		assertEquals(expectedStartDate, payoutPeriod.getStartDate());
		assertEquals(expectedEndDate, payoutPeriod.getEndDate());
	}

	@Test
	public void testFindApplicablePayoutPeriod_secondHalfOfTheMonth_february_leapYear() {

		LocalDate jobDate = LocalDate.of(2000, 2, 20);

		LocalDate expectedStartDate = LocalDate.of(2000, 2, 16);
		LocalDate expectedEndDate = LocalDate.of(2000, 2, 29);

		PayoutPeriod payoutPeriod = service.findApplicablePayoutPeriod(jobDate);

		assertNotNull(payoutPeriod);
		assertEquals(expectedStartDate, payoutPeriod.getStartDate());
		assertEquals(expectedEndDate, payoutPeriod.getEndDate());
	}

	@Test
	public void testFindApplicablePayoutPeriod_secondHalfOfTheMonth_february_regularYear() {

		LocalDate jobDate = LocalDate.of(1001, 2, 20);

		LocalDate expectedStartDate = LocalDate.of(1001, 2, 16);
		LocalDate expectedEndDate = LocalDate.of(1001, 2, 28);

		PayoutPeriod payoutPeriod = service.findApplicablePayoutPeriod(jobDate);

		assertNotNull(payoutPeriod);
		assertEquals(expectedStartDate, payoutPeriod.getStartDate());
		assertEquals(expectedEndDate, payoutPeriod.getEndDate());
	}

	@Test
	public void testFindApplicablePayoutPeriod_secondHalfOfTheMonth_atStartDate() {

		LocalDate jobDate = LocalDate.of(1001, 9, 16);

		LocalDate expectedStartDate = LocalDate.of(1001, 9, 16);
		LocalDate expectedEndDate = LocalDate.of(1001, 9, 30);

		PayoutPeriod payoutPeriod = service.findApplicablePayoutPeriod(jobDate);

		assertNotNull(payoutPeriod);
		assertEquals(expectedStartDate, payoutPeriod.getStartDate());
		assertEquals(expectedEndDate, payoutPeriod.getEndDate());
	}

	@Test
	public void testFindApplicablePayoutPeriod_firstHalfOfTheMonth_atEndDate() {

		LocalDate jobDate = LocalDate.of(1001, 9, 15);

		LocalDate expectedStartDate = LocalDate.of(1001, 9, 1);
		LocalDate expectedEndDate = LocalDate.of(1001, 9, 15);

		PayoutPeriod payoutPeriod = service.findApplicablePayoutPeriod(jobDate);

		assertNotNull(payoutPeriod);
		assertEquals(expectedStartDate, payoutPeriod.getStartDate());
		assertEquals(expectedEndDate, payoutPeriod.getEndDate());
	}

}
