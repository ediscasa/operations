package com.appgate.operations.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Vector;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.appgate.operations.exceptions.OperationWithoutOperandException;
import com.appgate.operations.exceptions.OperationWithoutSessionException;
import com.appgate.operations.model.OperationData;
import com.appgate.operations.repository.OperationDataRepository;
import com.appgate.operations.service.impl.OperationServiceImpl;

@SpringBootTest
public class OperationServiceTest {
	
	@Autowired
	private OperationServiceImpl operationServiceImpl;
	
	@MockBean 
	private OperationDataRepository operationDataRepository;
	
	@Test
	public void shouldStartOperation() {
		String id =  operationServiceImpl.startOperation();
		
		assertThat(id).isNotEmpty();
	}

	@Test
	public void shouldAddOperand() throws OperationWithoutSessionException {
		Vector<BigDecimal> operandList = new Vector<BigDecimal>();
		operandList.add(new BigDecimal("8"));
		OperationData operationData = new OperationData("1", operandList);
		Optional<OperationData> operationDataOpt = Optional.of(operationData);
		when(operationDataRepository.findById("1")).thenReturn(operationDataOpt);
		
		OperationData operationDataSave =  operationServiceImpl.addOperand("1", new BigDecimal("3"));
		
		assertThat(operationDataSave.getOperandList().size()).isEqualTo(2);
	}

	@Test
	public void shouldSumOperands() throws OperationWithoutSessionException, OperationWithoutOperandException {
		Vector<BigDecimal> operandList = new Vector<BigDecimal>();
		operandList.add(new BigDecimal("8"));
		operandList.add(new BigDecimal("5"));
		OperationData operationData = new OperationData("1", operandList);
		Optional<OperationData> operationDataOpt = Optional.of(operationData);
		when(operationDataRepository.findById("1")).thenReturn(operationDataOpt);
		
		BigDecimal result =  operationServiceImpl.sumOperation("1");
		
		assertThat(result).isEqualTo(new BigDecimal("13"));
	}

	@Test
	public void shouldMultiplyOperands() throws OperationWithoutSessionException, OperationWithoutOperandException {
		Vector<BigDecimal> operandList = new Vector<BigDecimal>();
		operandList.add(new BigDecimal("8"));
		operandList.add(new BigDecimal("5"));
		OperationData operationData = new OperationData("1", operandList);
		Optional<OperationData> operationDataOpt = Optional.of(operationData);
		when(operationDataRepository.findById("1")).thenReturn(operationDataOpt);
		
		BigDecimal result =  operationServiceImpl.multiplicationOperation("1");
		
		assertThat(result).isEqualTo(new BigDecimal("40"));
	}

	@Test
	public void shouldDivideOperands() throws OperationWithoutSessionException, OperationWithoutOperandException {
		Vector<BigDecimal> operandList = new Vector<BigDecimal>();
		operandList.add(new BigDecimal("12"));
		operandList.add(new BigDecimal("3"));
		OperationData operationData = new OperationData("1", operandList);
		Optional<OperationData> operationDataOpt = Optional.of(operationData);
		when(operationDataRepository.findById("1")).thenReturn(operationDataOpt);
		
		BigDecimal result =  operationServiceImpl.divisionOperation("1");
		
		assertThat(result).isEqualTo(new BigDecimal("4"));
	}

	@Test
	public void shouldSubtractOperands() throws OperationWithoutSessionException, OperationWithoutOperandException {
		Vector<BigDecimal> operandList = new Vector<BigDecimal>();
		operandList.add(new BigDecimal("12"));
		operandList.add(new BigDecimal("3"));
		operandList.add(new BigDecimal("2"));
		OperationData operationData = new OperationData("1", operandList);
		Optional<OperationData> operationDataOpt = Optional.of(operationData);
		when(operationDataRepository.findById("1")).thenReturn(operationDataOpt);
		
		BigDecimal result =  operationServiceImpl.subtractionOperation("1");
		
		assertThat(result).isEqualTo(new BigDecimal("7"));
	}

	@Test
	public void shouldPowerOperands() throws OperationWithoutSessionException, OperationWithoutOperandException {
		Vector<BigDecimal> operandList = new Vector<BigDecimal>();
		operandList.add(new BigDecimal("2"));
		operandList.add(new BigDecimal("3"));
		operandList.add(new BigDecimal("2"));
		OperationData operationData = new OperationData("1", operandList);
		Optional<OperationData> operationDataOpt = Optional.of(operationData);
		when(operationDataRepository.findById("1")).thenReturn(operationDataOpt);
		
		BigDecimal result =  operationServiceImpl.exponentiationOperation("1");
		
		assertThat(result).isEqualTo(new BigDecimal("64"));
	}

}
