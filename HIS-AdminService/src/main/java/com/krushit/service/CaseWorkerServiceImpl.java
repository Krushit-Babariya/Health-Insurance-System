package com.krushit.service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krushit.entity.CaseWorkerEntity;
import com.krushit.model.CaseWorker;
import com.krushit.repository.CaseWorkerRepository;

@Service
public class CaseWorkerServiceImpl implements ICaseWorkerService {

	@Autowired
	private CaseWorkerRepository caseWorkerRepository;

	@Override
	public String addCaseWorker(CaseWorker caseWorker) {
		CaseWorkerEntity caseWorkerEntity = new CaseWorkerEntity();
		BeanUtils.copyProperties(caseWorker, caseWorkerEntity);

		String generatedPassword = generatePassword();
		caseWorkerEntity.setPassword(generatedPassword);

		CaseWorkerEntity entity = caseWorkerRepository.save(caseWorkerEntity);
		return entity != null ? "Caseworker in ragistered with ID val :: " + entity.getAccountId()
				: "OOPS..!! Something went wrong";
	}

	@Override
	public List<CaseWorker> getAllCaseWorkers() {

		List<CaseWorkerEntity> caseWorkerEntityList = caseWorkerRepository.findAll();
		List<CaseWorker> caseWorkersList = new ArrayList<CaseWorker>();

		caseWorkerEntityList.forEach(entity -> {
			CaseWorker worker = new CaseWorker();
			BeanUtils.copyProperties(entity, worker);
			caseWorkersList.add(worker);
		});
		return caseWorkersList;
	}

	private static String generatePassword() {
		int n = 8;
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {
			SecureRandom random = new SecureRandom();
			float randomValue = random.nextFloat();
			int index = (int) (AlphaNumericString.length() * randomValue);
			sb.append(AlphaNumericString.charAt(index));
		}
		return sb.toString();
	}

}