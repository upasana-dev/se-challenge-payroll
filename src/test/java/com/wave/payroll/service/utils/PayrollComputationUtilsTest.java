package com.wave.payroll.service.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.wave.payroll.model.constants.JobGroup;
import com.wave.payroll.service.dto.PayoutPeriod;

public class PayrollComputationUtilsTest {

	@Test
	public void testCalculateWages() {
		assertEquals(180, PayrollComputationUtils.calculateWages(9, JobGroup.A));
		assertEquals(270, PayrollComputationUtils.calculateWages(9, JobGroup.B));
		assertEquals(680, PayrollComputationUtils.calculateWages(34, JobGroup.A));
		assertEquals(2610, PayrollComputationUtils.calculateWages(87, JobGroup.B));
	}

	@Test
	public void testFindApplicablePayoutPeriod_firstHalfOfTheMonth() {

		LocalDate jobDate = LocalDate.of(2021, 5, 5);

		LocalDate expectedStartDate = LocalDate.of(2021, 5, 1);
		LocalDate expectedEndDate = LocalDate.of(2021, 5, 15);

		PayoutPeriod payoutPeriod = PayrollComputationUtils.findApplicablePayoutPeriod(jobDate);

		assertNotNull(payoutPeriod);
		assertEquals(expectedStartDate, payoutPeriod.getStartDate());
		assertEquals(expectedEndDate, payoutPeriod.getEndDate());
	}

	@Test
	public void testFindApplicablePayoutPeriod_secondHalfOfTheMonth_nonFebruaryMonth() {

		LocalDate jobDate = LocalDate.of(2000, 8, 20);

		LocalDate expectedStartDate = LocalDate.of(2000, 8, 16);
		LocalDate expectedEndDate = LocalDate.of(2000, 8, 31);

		PayoutPeriod payoutPeriod = PayrollComputationUtils.findApplicablePayoutPeriod(jobDate);

		assertNotNull(payoutPeriod);
		assertEquals(expectedStartDate, payoutPeriod.getStartDate());
		assertEquals(expectedEndDate, payoutPeriod.getEndDate());
	}

	@Test
	public void testFindApplicablePayoutPeriod_secondHalfOfTheMonth_february_leapYear() {

		LocalDate jobDate = LocalDate.of(2000, 2, 20);

		LocalDate expectedStartDate = LocalDate.of(2000, 2, 16);
		LocalDate expectedEndDate = LocalDate.of(2000, 2, 29);

		PayoutPeriod payoutPeriod = PayrollComputationUtils.findApplicablePayoutPeriod(jobDate);

		assertNotNull(payoutPeriod);
		assertEquals(expectedStartDate, payoutPeriod.getStartDate());
		assertEquals(expectedEndDate, payoutPeriod.getEndDate());
	}

	@Test
	public void testFindApplicablePayoutPeriod_secondHalfOfTheMonth_february_regularYear() {

		LocalDate jobDate = LocalDate.of(1001, 2, 20);

		LocalDate expectedStartDate = LocalDate.of(1001, 2, 16);
		LocalDate expectedEndDate = LocalDate.of(1001, 2, 28);

		PayoutPeriod payoutPeriod = PayrollComputationUtils.findApplicablePayoutPeriod(jobDate);

		assertNotNull(payoutPeriod);
		assertEquals(expectedStartDate, payoutPeriod.getStartDate());
		assertEquals(expectedEndDate, payoutPeriod.getEndDate());
	}

	@Test
	public void testFindApplicablePayoutPeriod_secondHalfOfTheMonth_atStartDate() {

		LocalDate jobDate = LocalDate.of(1001, 9, 16);

		LocalDate expectedStartDate = LocalDate.of(1001, 9, 16);
		LocalDate expectedEndDate = LocalDate.of(1001, 9, 30);

		PayoutPeriod payoutPeriod = PayrollComputationUtils.findApplicablePayoutPeriod(jobDate);

		assertNotNull(payoutPeriod);
		assertEquals(expectedStartDate, payoutPeriod.getStartDate());
		assertEquals(expectedEndDate, payoutPeriod.getEndDate());
	}

	@Test
	public void testFindApplicablePayoutPeriod_firstHalfOfTheMonth_atEndDate() {

		LocalDate jobDate = LocalDate.of(1001, 9, 15);

		LocalDate expectedStartDate = LocalDate.of(1001, 9, 1);
		LocalDate expectedEndDate = LocalDate.of(1001, 9, 15);

		PayoutPeriod payoutPeriod = PayrollComputationUtils.findApplicablePayoutPeriod(jobDate);

		assertNotNull(payoutPeriod);
		assertEquals(expectedStartDate, payoutPeriod.getStartDate());
		assertEquals(expectedEndDate, payoutPeriod.getEndDate());
	}

}
