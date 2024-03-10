package com.kartik.blogapi.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kartik.blogapi.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException exception){
		
		ApiResponse response = new ApiResponse(exception.getMessage(), false);
		
		return new ResponseEntity<ApiResponse>(response , HttpStatus.NOT_FOUND);		
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception){
		
		Map<String, String> map = new HashMap<>();
		
		exception.getBindingResult().getAllErrors().forEach((error) -> {
			
			String field = ((FieldError)error).getField();
			String message = error.getDefaultMessage();
			
			map.put(field, message);
		});
		
		return new ResponseEntity<Map<String,String>>(map , HttpStatus.BAD_REQUEST);		
	}

}
