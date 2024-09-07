package com.krushit.service;

import com.krushit.model.CoSummary;

public interface ICorrospondanceMgmtService {
	public CoSummary processPendingTriggers() throws Exception;
	
}
