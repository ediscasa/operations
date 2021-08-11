package com.appgate.operations.service.impl;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appgate.operations.exceptions.OperationWithoutOperandException;
import com.appgate.operations.exceptions.OperationWithoutSessionException;
import com.appgate.operations.model.OperationData;
import com.appgate.operations.repository.OperationDataRepository;
import com.appgate.operations.service.OperationService;

@Service
public class OperationServiceImpl implements OperationService {
	
	private static final String NO_SE_ENCONTRO_OPERANDO = "No se encontro operando";

	private static final String NO_SE_ENCONTRO_LA_SESION = "No se encontro la sesion";
	
	@Autowired
	private OperationDataRepository operationDataRepository;
	
	@Override
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
			throw new OperationWithoutSessionException(NO_SE_ENCONTRO_LA_SESION);
		}
		
		operandList.add(operand);
		OperationData operationData = new OperationData(idSession, operandList);
		operationDataRepository.save(operationData);
		
		return operationData;
	}
	
	@Override
	public BigDecimal sumOperation(String idSession) throws OperationWithoutSessionException, OperationWithoutOperandException {
		Optional<OperationData> operationDataOpt = operationDataRepository.findById(idSession);
		
		if(operationDataOpt.isEmpty()) {
			throw new OperationWithoutSessionException(NO_SE_ENCONTRO_LA_SESION);
		}
		
		Vector<BigDecimal> operandList = operationDataOpt.map(OperationData::getOperandList).get();
		if(operandList.isEmpty()) {
			throw new OperationWithoutOperandException(NO_SE_ENCONTRO_OPERANDO);
		}
		
		BigDecimal result = operandList.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
		
		return result;
	}
	
	@Override
	public BigDecimal multiplicationOperation(String idSession) throws OperationWithoutSessionException, OperationWithoutOperandException {
		Optional<OperationData> operationDataOpt = operationDataRepository.findById(idSession);
		
		if(operationDataOpt.isEmpty()) {
			throw new OperationWithoutSessionException(NO_SE_ENCONTRO_LA_SESION);
		}
		
		Vector<BigDecimal> operandList = operationDataOpt.map(OperationData::getOperandList).get();
		if(operandList.isEmpty()) {
			throw new OperationWithoutOperandException(NO_SE_ENCONTRO_OPERANDO);
		}		
		BigDecimal result = operandList.stream().reduce(BigDecimal.ONE, BigDecimal::multiply);
		
		return result;
	}

	@Override
	public BigDecimal divisionOperation(String idSession) throws OperationWithoutSessionException, OperationWithoutOperandException {
		Optional<OperationData> operationDataOpt = operationDataRepository.findById(idSession);
		
		if(operationDataOpt.isEmpty()) {
			throw new OperationWithoutSessionException(NO_SE_ENCONTRO_LA_SESION);
		}
		
		Vector<BigDecimal> operandList = operationDataOpt.map(OperationData::getOperandList).get();
		Optional<BigDecimal> result = operandList.stream().reduce(BigDecimal::divide);
		
		if(result.isEmpty()) {
			throw new OperationWithoutOperandException(NO_SE_ENCONTRO_OPERANDO);
		}
		
		return result.get();
	}

	@Override
	public BigDecimal subtractionOperation(String idSession) throws OperationWithoutSessionException, OperationWithoutOperandException {
		Optional<OperationData> operationDataOpt = operationDataRepository.findById(idSession);
		
		if(operationDataOpt.isEmpty()) {
			throw new OperationWithoutSessionException(NO_SE_ENCONTRO_LA_SESION);
		}
		
		Vector<BigDecimal> operandList = operationDataOpt.map(OperationData::getOperandList).get();
		Optional<BigDecimal> result = operandList.stream().reduce(BigDecimal::subtract);
		
		if(result.isEmpty()) {
			throw new OperationWithoutOperandException(NO_SE_ENCONTRO_OPERANDO);
		}
		
		return result.get();
	}

	@Override
	public BigDecimal exponentiationOperation(String idSession) throws OperationWithoutSessionException, OperationWithoutOperandException {
		Optional<OperationData> operationDataOpt = operationDataRepository.findById(idSession);
		
		if(operationDataOpt.isEmpty()) {
			throw new OperationWithoutSessionException(NO_SE_ENCONTRO_LA_SESION);
		}
		
		Vector<BigDecimal> operandList = operationDataOpt.map(OperationData::getOperandList).get();
		if(operandList.isEmpty()) {
			throw new OperationWithoutOperandException(NO_SE_ENCONTRO_OPERANDO);
		}		
		Optional<BigDecimal> result = operandList.stream().reduce((subtotal, element) -> subtotal.pow(element.intValue()));
		
		return result.get();
	}

}
