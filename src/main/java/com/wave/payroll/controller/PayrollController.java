package com.wave.payroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wave.payroll.service.EmployeeEffortReportServiceImpl;
import com.wave.payroll.service.PayrollReportService;
import com.wave.payroll.service.dto.PayrollReport;

@RestController
@RequestMapping("/payroll")
public class PayrollController {

	@Autowired
	private PayrollReportService reportService;

	@Autowired
	private EmployeeEffortReportServiceImpl employeeEffortService;

	@GetMapping("/report")
	public PayrollReport generatePayrollReport() {
		return reportService.generatePayrollReport();
	}

	@PostMapping("/data")
	public void uploadDataReport(@RequestParam("file") MultipartFile file) {

		if (file == null) {
			throw new RuntimeException("Invalid file passed in parameter!");
		}
		employeeEffortService.importPayrollData(file);
	}

}
