package com.connection.emobile.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	@ExceptionHandler(CommonException.class)
	public ResponseEntity<ResponseError> commonException(Exception e) {
	
		ResponseError error = new ResponseError(e.getMessage(), HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(error, HttpStatus.OK);
	}

	
	  @ExceptionHandler(InvalidTrackIdException.class) public
	  ResponseEntity<ResponseError> invalidTrackId(InvalidTrackIdException e,
	  WebRequest request) {
	  
	  ResponseError error = new ResponseError(e.getMessage(),
	  HttpStatus.NOT_FOUND.value()); return new ResponseEntity<>(error,
	  HttpStatus.OK); }
	 
}