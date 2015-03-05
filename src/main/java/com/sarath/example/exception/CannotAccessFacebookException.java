package com.sarath.example.exception;

public class CannotAccessFacebookException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7493268145123074410L;

	public CannotAccessFacebookException(String message){
		super(message);
	}
	
}
