package com.wave.payroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wave.payroll.info.service.PayrollInfoService;
import com.wave.payroll.report.service.PayrollReportService;

@RestController
@RequestMapping("/payroll")
public class PayrollController {

	@Autowired
	private PayrollReportService reportService;

	@Autowired
	private PayrollInfoService employeeEffortService;

	@SuppressWarnings("rawtypes")
	@GetMapping("/report")
	public ResponseEntity generatePayrollReport() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(reportService.generatePayrollReport());
		} catch (Exception anyEx) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An internal error was encountered while generating the report");
		}
	}

	@SuppressWarnings("rawtypes")
	@PostMapping("/data")
	public ResponseEntity importPayrollData(@RequestParam("file") MultipartFile importData) {

		try {
			employeeEffortService.importEmployeeEffortInfo(importData);
			return ResponseEntity.status(HttpStatus.CREATED).body("Employee Effort Info successfully imported");
		} catch (Exception anyEx) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
					"An internal error was encountered while importing the data - " + anyEx.getLocalizedMessage());
		}

	}

}
