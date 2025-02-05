package com.mitocode.exception;

//@ResponseStatus(HttpStatus.NOT_FOUND) ya no porque se creo el handler
public class ModelNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ModelNotFoundException(String mesage) {
		super(mesage);
	}

	
}
