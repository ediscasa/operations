package com.appgate.operations.service;

import java.math.BigDecimal;

import com.appgate.operations.exceptions.OperationWithoutOperandException;
import com.appgate.operations.exceptions.OperationWithoutSessionException;
import com.appgate.operations.model.OperationData;

public interface OperationService {
	
	OperationData addOperand(String idSession, BigDecimal operand) throws OperationWithoutSessionException;
	
	BigDecimal sumOperation(String idSession) throws OperationWithoutSessionException, OperationWithoutOperandException;

	BigDecimal multiplicationOperation(String idSession) throws OperationWithoutSessionException, OperationWithoutOperandException;

	BigDecimal divisionOperation(String idSession) throws OperationWithoutSessionException, OperationWithoutOperandException;

	BigDecimal subtractionOperation(String idSession)
			throws OperationWithoutSessionException, OperationWithoutOperandException;

	BigDecimal exponentiationOperation(String idSession)
			throws OperationWithoutSessionException, OperationWithoutOperandException;

	String startOperation();

}
