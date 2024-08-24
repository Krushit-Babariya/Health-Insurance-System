package com.krushit_ed.advice_ed;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.krushit_ed.exception_ed.PlanAlreadyAppliedException;

@RestControllerAdvice
public class EligibilityDeterminationAdvice {
	
	@ExceptionHandler(PlanAlreadyAppliedException.class)
    public ResponseEntity<String> handlePlanAlreadyAppliedException(PlanAlreadyAppliedException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleAllException(Exception e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
	}
}
