package com.appgate.operations.service.impl;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appgate.operations.exceptions.OperationWithoutSessionException;
import com.appgate.operations.model.OperationData;
import com.appgate.operations.repository.OperationDataRepository;
import com.appgate.operations.service.OperationService;

@Service
public class OperationServiceImpl implements OperationService {
	
	@Autowired
	private OperationDataRepository operationDataRepository;
	
	public String startOperation() {
		String id = UUID.randomUUID().toString();
		
		OperationData operationData = new OperationData(id, new Vector<BigDecimal>());
		operationDataRepository.save(operationData);
		
		return id;
	}

	@Override
	public OperationData addOperand(String idSession, BigDecimal operand) throws OperationWithoutSessionException {
		
		Optional<OperationData> operationDataOpt = operationDataRepository.findById(idSession);
		
		Vector<BigDecimal> operandList;
		if( !operationDataOpt.isEmpty()) {
			operandList = operationDataOpt.map(OperationData::getOperandList).get();
		} else {
			throw new OperationWithoutSessionException();
		}
		
		operandList.add(operand);
		OperationData operationData = new OperationData(idSession, operandList);
		operationDataRepository.save(operationData);
		
		return operationData;
	}
	
	@Override
	public BigDecimal sumOperation(String idSession) throws OperationWithoutSessionException {
		Optional<OperationData> operationDataOpt = operationDataRepository.findById(idSession);
		
		if(operationDataOpt.isEmpty()) {
			throw new OperationWithoutSessionException();
		}
		
		Vector<BigDecimal> operandList = operationDataOpt.map(OperationData::getOperandList).get();
		BigDecimal result = operandList.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
		
		return result;
	}

}
