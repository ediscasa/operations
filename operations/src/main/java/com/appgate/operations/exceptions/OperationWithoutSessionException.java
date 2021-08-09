package com.appgate.operations.exceptions;

public class OperationWithoutSessionException extends Exception {

	private static final long serialVersionUID = 1L;

	public OperationWithoutSessionException() {
		super();
	}

	public OperationWithoutSessionException(String message) {
		super(message);
	}

}
