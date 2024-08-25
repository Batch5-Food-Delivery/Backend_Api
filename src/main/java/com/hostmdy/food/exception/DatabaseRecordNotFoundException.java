package com.hostmdy.food.exception;


public class DatabaseRecordNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DatabaseRecordNotFoundException(String message) {
		super(message);
	}
}
