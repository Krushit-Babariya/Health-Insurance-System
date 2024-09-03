package com.krushit.service;

import java.util.List;

import com.krushit.model.CaseWorker;

public interface ICaseWorkerService {
    String addCaseWorker(CaseWorker caseWorker);
    List<CaseWorker> getAllCaseWorkers();
}