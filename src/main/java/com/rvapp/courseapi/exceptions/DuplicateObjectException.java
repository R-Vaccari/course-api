package com.rvapp.courseapi.exceptions;

public class DuplicateObjectException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public DuplicateObjectException(String msg) {
		super(msg);
	}

}
