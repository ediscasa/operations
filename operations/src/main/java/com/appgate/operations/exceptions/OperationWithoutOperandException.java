package com.appgate.operations.exceptions;

public class OperationWithoutOperandException extends Exception {

	private static final long serialVersionUID = 9002673785590899579L;

	public OperationWithoutOperandException() {
		super();
	}

	public OperationWithoutOperandException(String message) {
		super(message);
	}

}
