package com.appgate.operations.model;

import java.math.BigDecimal;
import java.util.Vector;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("OperationData")
public class OperationData {
	
	private String id;
	private Vector<BigDecimal> operandList;
	 
	public OperationData(String id, Vector<BigDecimal> operandList) {
		super();
		this.id = id;
		this.operandList = operandList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Vector<BigDecimal> getOperandList() {
		return operandList;
	}

	public void setOperandList(Vector<BigDecimal> operandList) {
		this.operandList = operandList;
	}
}
