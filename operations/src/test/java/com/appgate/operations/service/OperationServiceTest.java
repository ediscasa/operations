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
	public void shouldAddOperand() {
		Vector<BigDecimal> operandList = new Vector<BigDecimal>();
		operandList.add(new BigDecimal("8"));
		OperationData operationData = new OperationData("1", operandList);
		Optional<OperationData> operationDataOpt = Optional.of(operationData);
		when(operationDataRepository.findById("1")).thenReturn(operationDataOpt);
		
		OperationData operationDataSave =  operationServiceImpl.addOperand("1", new BigDecimal("3"));
		
		assertThat(operationDataSave.getOperandList().size()).isEqualTo(2);
	}

}
