package com.wave.payroll.info.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Performs operations relating to information about employee job effort
 */
public interface PayrollInfoService {

	/**
	 * Accepts a file containing employee job information and imports the
	 * information
	 * 
	 * @param importedData The file containing the job information
	 */
	public void importEmployeeEffortInfo(MultipartFile importedData);

}
