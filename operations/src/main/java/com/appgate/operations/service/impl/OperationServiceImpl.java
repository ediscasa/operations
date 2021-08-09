package com.appgate.operations.service.impl;

import java.math.BigDecimal;
import java.util.Hashtable;
import java.util.Vector;

import org.springframework.stereotype.Service;

import com.appgate.operations.model.OperationData;
import com.appgate.operations.service.OperationService;

@Service
public class OperationServiceImpl implements OperationService {
	
	public Hashtable<String, OperationData> operationsData = new Hashtable<String, OperationData>();

	@Override
	public void addOperand(String idSession, BigDecimal operand) {
		
		OperationData operationData = operationsData.get(idSession);
		
		Vector<BigDecimal> operandList;
		if( operationData != null) {
			operandList = operationData.getOperandList();
			operandList.add(operand);
			operationData.setOperandList(operandList);
			
		} else {
			operandList = new Vector<BigDecimal>();
			operandList.add(operand);
			operationData = new OperationData(idSession, operandList);
		}

		operationsData.put(idSession, operationData);
	}

	@Override
	public Hashtable<String, OperationData> getOperations() {
		return	operationsData;
	}

	@Override
	public Vector<BigDecimal> getOperands(String idSession) {
		return	operationsData.get(idSession).getOperandList();
	}

}
