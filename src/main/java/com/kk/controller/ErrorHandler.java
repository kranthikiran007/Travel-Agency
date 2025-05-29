package com.kk.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.kk.dtos.ErrorResponse;
import com.kk.exception.CustomerNotFoundException;
import com.kk.exception.TourNotFoundException;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class ErrorHandler {
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(CustomerNotFoundException ex) {
		ErrorResponse r = new ErrorResponse();
		r.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(r, HttpStatusCode.valueOf(404));
	}
	@ExceptionHandler(TourNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleTourNotFoundException(TourNotFoundException ex) {
		ErrorResponse r = new ErrorResponse();
		r.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(r, HttpStatusCode.valueOf(404));
	}

	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
		StringBuilder s = new StringBuilder();
		for (FieldError f : ex.getBindingResult().getFieldErrors()) {
			s.append(f.getField() + ":" + f.getDefaultMessage() );
			s.append(" --------------");
		}
		ErrorResponse r = new ErrorResponse();
		r.setMessage(s.toString());
		return ResponseEntity.badRequest().body(r);
	}
	 @ExceptionHandler(ConstraintViolationException.class)
	    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
	        List<String> errors = ex.getConstraintViolations()
	                .stream()
	                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
	                .collect(Collectors.toList());
	        ErrorResponse r = new ErrorResponse();
	        r.setMessage(String.join(" -------------- ", errors));
	        return new ResponseEntity<>(r, HttpStatus.BAD_REQUEST);
	    }
}
