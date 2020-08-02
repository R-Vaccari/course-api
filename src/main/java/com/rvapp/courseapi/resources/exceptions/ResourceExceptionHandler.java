package com.rvapp.courseapi.resources.exceptions;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rvapp.courseapi.exceptions.DuplicateObjectException;
import com.rvapp.courseapi.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");


	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandartError> resourceNotFound(ObjectNotFoundException e,
			HttpServletRequest request) {
		String error = "Resource not found.";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandartError se = new StandartError(sdf.format(new Date()), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(se);
	}
	
	@ExceptionHandler(DuplicateObjectException.class)
	public ResponseEntity<StandartError> objectAlreadyExists(DuplicateObjectException e,
			HttpServletRequest request) {
		String error = "Object already exists. Change id for posting or use PUT.";
		HttpStatus status = HttpStatus.CONFLICT;
		StandartError se = new StandartError(sdf.format(new Date()), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(se);
	}
	
}
