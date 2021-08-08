package com.wave.payroll.info.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Performs operations relating to information employee payroll info
 */
public interface PayrollInfoService {

	/**
	 * Accepts a file containing employee job information and imports the
	 * information into the database
	 * 
	 * @param importedData The file containing the job information
	 */
	public void importEmployeeEffortInfo(MultipartFile importedData);

}
