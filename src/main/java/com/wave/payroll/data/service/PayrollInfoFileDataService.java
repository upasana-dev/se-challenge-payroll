package com.wave.payroll.data.service;

public interface PayrollInfoFileDataService {

	/**
	 * Registers the file with the given name into the DB
	 * 
	 * @param fileName Name of file to be inserted
	 */
	void registerFile(String fileName);

	/**
	 * Checks if a file with the given name already exists
	 * 
	 * @param fileName Name of the file for which the check is being made
	 * @return true if the file exists, false otherwise
	 */
	boolean doesFileExist(String fileName);

}
