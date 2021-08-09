package com.appgate.operations.service;

import java.math.BigDecimal;
import java.util.Hashtable;
import java.util.Vector;

import com.appgate.operations.model.OperationData;

public interface OperationService {
	
	void addOperand(String idSession, BigDecimal operand);

	Hashtable<String, OperationData> getOperations();

	Vector<BigDecimal> getOperands(String idSession);

}
