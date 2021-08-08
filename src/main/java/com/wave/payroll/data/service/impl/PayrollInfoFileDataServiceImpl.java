package com.wave.payroll.data.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wave.payroll.data.service.PayrollInfoFileDataService;
import com.wave.payroll.model.PayrollInfoFile;
import com.wave.payroll.repository.PayrollInfoFileRepository;

@Service
public class PayrollInfoFileDataServiceImpl implements PayrollInfoFileDataService {

	@Autowired
	private PayrollInfoFileRepository fileRepository;

	@Override
	public void registerFile(String fileName) {
		if (StringUtils.isEmpty(fileName)) {
			throw new RuntimeException("File name cannot be empty!");
		}
		fileRepository.save(new PayrollInfoFile(fileName));
	}

	@Override
	public boolean doesFileExist(String fileName) {
		if (StringUtils.isEmpty(fileName)) {
			throw new RuntimeException("File name cannot be empty!");
		}

		return fileRepository.existsByFileName(fileName);
	}

}
