package com.wave.payroll.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.wave.payroll.service.dto.PayoutPeriod;

public class PayrollDataServiceTest {

	private PayrollDataService service = new PayrollDataService();

	@Test
	public void testCalculateTotalWagesForPayoutPeriod_onlyEntryForPayoutPeriod_otherPreexistingPeriods() {

		PayoutPeriod currentPayoutPeriod = new PayoutPeriod(LocalDate.now(), LocalDate.now().plusDays(3));
		PayoutPeriod existingPayoutPeriod = new PayoutPeriod(LocalDate.now().minusMonths(2),
				LocalDate.now().minusMonths(2).plusDays(3));

		// The map already has a pre-existing mapping for another period, but none for
		// the current period
		Map<PayoutPeriod, Double> workToPeriodMapping = new HashMap<>();
		workToPeriodMapping.put(existingPayoutPeriod, 25.60);

		service.calculateTotalWagesForPayoutPeriod(workToPeriodMapping, currentPayoutPeriod, 36.17);

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

		service.calculateTotalWagesForPayoutPeriod(workToPeriodMapping, currentPayoutPeriod, 36.17);

		assertEquals(2, workToPeriodMapping.size());
		assertEquals(25.60, workToPeriodMapping.get(existingPayoutPeriod));
		assertEquals(100, workToPeriodMapping.get(currentPayoutPeriod));
	}

	@Test
	public void testCalculateTotalWagesForPayoutPeriod_onlyEntryForPayoutPeriod_emptyMap() {

		PayoutPeriod currentPayoutPeriod = new PayoutPeriod(LocalDate.now(), LocalDate.now().plusDays(3));

		// Empty map
		Map<PayoutPeriod, Double> workToPeriodMapping = new HashMap<>();

		service.calculateTotalWagesForPayoutPeriod(workToPeriodMapping, currentPayoutPeriod, 36.17);

		assertEquals(1, workToPeriodMapping.size());
		assertEquals(36.17, workToPeriodMapping.get(currentPayoutPeriod));
	}

}
