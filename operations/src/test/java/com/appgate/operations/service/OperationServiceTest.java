package com.appgate.operations.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.appgate.operations.service.impl.OperationServiceImpl;

@SpringBootTest
public class OperationServiceTest {
	
	
	@Autowired
	private OperationServiceImpl operationServiceImpl;
	
	@Test
	public void shouldAddOperand() {
		
		operationServiceImpl.addOperand("1", new BigDecimal("8"));
		operationServiceImpl.addOperand("2", new BigDecimal("5"));
		operationServiceImpl.addOperand("3", new BigDecimal("16"));
		operationServiceImpl.addOperand("1", new BigDecimal("3"));
		
		assertThat(operationServiceImpl.getOperations().size()).isEqualTo(3);
		assertThat(operationServiceImpl.getOperands("1").size()).isEqualTo(2);

		
	}

}
