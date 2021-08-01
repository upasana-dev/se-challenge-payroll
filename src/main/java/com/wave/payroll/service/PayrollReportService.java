package com.wave.payroll.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wave.payroll.model.Employee;
import com.wave.payroll.repository.EmployeeRepository;
import com.wave.payroll.service.dto.EmployeeReport;
import com.wave.payroll.service.dto.PayoutPeriod;
import com.wave.payroll.service.dto.PayrollReport;

/**
 * Service performing logic specific to the generation of the payroll report
 *
 */
@Service
public class PayrollReportService {

	@Autowired
	private PayrollDataService payrollDataService;

	@Autowired
	private EmployeeRepository employeeRepository;

	public PayrollReport generatePayrollReport() {

//		List<Employee> applicableEmployees = employeeRepository.findAllByOrderByBusinessId();
		List<Employee> applicableEmployees = generateDummyEmployees();

		List<EmployeeReport> employeereports = new ArrayList<>();

		for (Employee employee : applicableEmployees) {

//			Map<PayoutPeriod, Double> employeePayrollData = payrollDataService
//					.computeEmployeeEfforts(employee.getEmployeeEfforts());
			Map<PayoutPeriod, Double> employeePayrollData = generateDummyMap();

			for (PayoutPeriod payoutPeriod : employeePayrollData.keySet()) {

				employeereports.add(generateEmployeeReport(payoutPeriod, employeePayrollData.get(payoutPeriod),
						employee.getBusinessId()));
			}

		}

		PayrollReport payrollReport = new PayrollReport();
		payrollReport.setEmployeeReports(employeereports);

		return payrollReport;

	}

	EmployeeReport generateEmployeeReport(PayoutPeriod payoutPeriod, double totalWages, Long employeeId) {
		return new EmployeeReport(employeeId, payoutPeriod, totalWages);
	}

	private List<Employee> generateDummyEmployees() {
		List<Employee> employeeList = new ArrayList<Employee>();

		Employee e1 = new Employee();
		e1.setBusinessId(2L);

		Employee e2 = new Employee();
		e2.setBusinessId(4L);

		employeeList.add(e1);
		employeeList.add(e2);

		return employeeList;

	}

	private Map<PayoutPeriod, Double> generateDummyMap() {
		PayoutPeriod currentPayoutPeriod = new PayoutPeriod(LocalDate.now(), LocalDate.now().plusDays(3));
		PayoutPeriod existingPayoutPeriod = new PayoutPeriod(LocalDate.now().minusMonths(2),
				LocalDate.now().minusMonths(2).plusDays(3));

		// The map already has a pre-existing mapping for another period, and one for
		// the current payout period
		Map<PayoutPeriod, Double> workToPeriodMapping = new HashMap<>();
		workToPeriodMapping.put(existingPayoutPeriod, 25.60);
		workToPeriodMapping.put(currentPayoutPeriod, 63.83);

		return workToPeriodMapping;
	}

}
