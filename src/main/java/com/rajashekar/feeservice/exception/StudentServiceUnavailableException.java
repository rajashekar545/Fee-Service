package com.rajashekar.feeservice.exception;

public class StudentServiceUnavailableException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public StudentServiceUnavailableException(String message) {
        super(message);
    }
}
