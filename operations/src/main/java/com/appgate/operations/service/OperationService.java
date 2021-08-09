package com.appgate.operations.service;

import java.math.BigDecimal;

import com.appgate.operations.exceptions.OperationWithoutSessionException;
import com.appgate.operations.model.OperationData;

public interface OperationService {
	
	OperationData addOperand(String idSession, BigDecimal operand) throws OperationWithoutSessionException;

}
