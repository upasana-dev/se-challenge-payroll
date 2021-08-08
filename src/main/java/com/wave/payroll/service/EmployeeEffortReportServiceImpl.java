package com.wave.payroll.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.wave.payroll.model.PayrollDataReport;
import com.wave.payroll.repository.PayrollDataReportRepository;
import com.wave.payroll.service.dto.EmployeeEffortData;

@Service
public class EmployeeEffortReportServiceImpl {

	@Autowired
	private EmployeeEffortDataServiceImpl employeeEffortService;

	@Autowired
	private PayrollDataReportRepository reportRepository;

	@Transactional
	public void importPayrollData(MultipartFile importedData) {

		// Validate the file - store it in the DB
		validateReport(importedData.getOriginalFilename());

		List<EmployeeEffortData> effortDataList = extractDataFromFile(importedData);

		processUploadedData(effortDataList);
	}

	/**
	 * Checks if this report has already been uploaded, if not, creates a new entry
	 * for the report in the database
	 * 
	 * @param fileName Name of the file
	 */
	private void validateReport(String fileName) {
		if (reportRepository.existsByReportName(fileName)) {
			throw new RuntimeException(
					"Effort Report with name " + fileName + " has already been processed. Aborting upload");
		} else {
			reportRepository.save(new PayrollDataReport(fileName));
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	List<EmployeeEffortData> extractDataFromFile(MultipartFile importedData) {
		List<EmployeeEffortData> effortDataList = new ArrayList<EmployeeEffortData>();

		try (Reader reader = new BufferedReader(new InputStreamReader(importedData.getInputStream()))) {

			CsvToBean<EmployeeEffortData> csvToBean = new CsvToBeanBuilder(reader).withType(EmployeeEffortData.class)
					.withIgnoreLeadingWhiteSpace(true).build();

			effortDataList = csvToBean.parse();

		} catch (IOException ioException) {
			throw new RuntimeException("An issue was encountered while processing the report, exiting process",
					ioException);
		}

		return effortDataList;
	}

	private void processUploadedData(List<EmployeeEffortData> effortData) {

		Map<Long, List<EmployeeEffortData>> map = effortData.stream()
				.collect(Collectors.groupingBy(EmployeeEffortData::getEmployeeBusinessId));

		for (Long employeeBusinessId : map.keySet()) {
			// Add employee effort by employee
			employeeEffortService.updateEmployeeEffortData(employeeBusinessId, effortData);
		}

	}

}
