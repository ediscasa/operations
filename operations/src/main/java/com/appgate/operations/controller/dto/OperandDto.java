package com.appgate.operations.controller.dto;

import java.math.BigDecimal;

public class OperandDto {
	
	private String idSession;
	
	private BigDecimal operand;

	public String getIdSession() {
		return idSession;
	}

	public void setIdSession(String idSession) {
		this.idSession = idSession;
	}

	public BigDecimal getOperand() {
		return operand;
	}

	public void setOperand(BigDecimal operand) {
		this.operand = operand;
	}

}
