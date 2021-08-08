package com.wave.payroll.info.service.impl;

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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.wave.payroll.data.service.EmployeeEffortDataService;
import com.wave.payroll.data.service.PayrollInfoFileDataService;
import com.wave.payroll.info.service.PayrollInfoService;
import com.wave.payroll.service.info.dto.EmployeeEffortData;

@Service
public class PayrollInfoServiceImpl implements PayrollInfoService {

	@Autowired
	private EmployeeEffortDataService employeeEffortService;

	@Autowired
	private PayrollInfoFileDataService infoFileService;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void importEmployeeEffortInfo(MultipartFile importedData) {

		String importedFileName = importedData.getOriginalFilename();

		validateReport(importedFileName);

		List<EmployeeEffortData> effortDataList = extractDataFromFile(importedData);

		processUploadedData(effortDataList);

		// Register the file as processed once the data has been processed
		infoFileService.registerFile(importedFileName);
	}

	/**
	 * Validates if the file is okay for processing
	 * 
	 * @param fileName Name of the file
	 */
	private void validateReport(String fileName) {
		if (infoFileService.doesFileExist(fileName)) {
			throw new RuntimeException(
					"Effort Report with name " + fileName + " has already been processed. Aborting upload");
		}
	}

	/**
	 * Extracts the information from the given file and transforms it into one
	 * {@link EmployeeEffortData} per row
	 * 
	 * @param importedData File with the imported info
	 * @return List of {@link EmployeeEffortData}
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<EmployeeEffortData> extractDataFromFile(MultipartFile importedData) {
		List<EmployeeEffortData> effortDataList = new ArrayList<EmployeeEffortData>();

		try (Reader reader = new BufferedReader(new InputStreamReader(importedData.getInputStream()))) {

			CsvToBean<EmployeeEffortData> csvToBean = new CsvToBeanBuilder(reader).withType(EmployeeEffortData.class)
					.withIgnoreLeadingWhiteSpace(true).build();

			effortDataList = csvToBean.parse();

		} catch (IOException ioException) {
			throw new RuntimeException(
					"An issue was encountered while extracting data from the payroll info file, exiting process",
					ioException);
		}

		return effortDataList;
	}

	/**
	 * Processes the extracted data and adds it to the database
	 * 
	 * @param effortData Data to be processed
	 */
	private void processUploadedData(List<EmployeeEffortData> effortData) {

		Map<Long, List<EmployeeEffortData>> map = effortData.stream()
				.collect(Collectors.groupingBy(EmployeeEffortData::getEmployeeBusinessId));

		for (Long employeeBusinessId : map.keySet()) {
			// Add employee effort by employee
			employeeEffortService.updateEmployeeEffortData(employeeBusinessId, effortData);
		}

	}

}
