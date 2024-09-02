package com.krushit.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PlanControllerAdvice {
	@ExceptionHandler(IllegalAccessException.class)
	public ResponseEntity<?> handleIAE(IllegalAccessException iae) {
		return new ResponseEntity<>(iae.getMessage(), HttpStatus.OK);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleALLE(Exception e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
	}
}
