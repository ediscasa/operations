package com.appgate.operations.service.impl;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appgate.operations.model.OperationData;
import com.appgate.operations.repository.OperationDataRepository;
import com.appgate.operations.service.OperationService;

@Service
public class OperationServiceImpl implements OperationService {
	
	@Autowired
	private OperationDataRepository operandDataRepository;	

	@Override
	public OperationData addOperand(String idSession, BigDecimal operand) {
		
		Optional<OperationData> operationDataOpt = operandDataRepository.findById(idSession);
		
		Vector<BigDecimal> operandList;
		if( !operationDataOpt.isEmpty()) {
			operandList = operationDataOpt.map(OperationData::getOperandList).get();
			operandList.add(operand);
		} else {
			operandList = new Vector<BigDecimal>();
			operandList.add(operand);
		}
		
		OperationData operationData = new OperationData(idSession, operandList);
		operandDataRepository.save(operationData);
		
		return operationData;
		
	}

}
