package com.socialmediaApplication.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.socialmediaApplication.Payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler({ResourceNotFoundException.class})
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionhandler(ResourceNotFoundException ex){
		String msg = ex.getMessage();
		ApiResponse apiResponse  = new ApiResponse(msg,false);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({NoResourceFoundException.class})
	public ResponseEntity<String> NoResourcefoundhandler(NoResourceFoundException ex){
		String str = "url not correct. please correct your url and try again ";
		return new  ResponseEntity<String>(str,HttpStatus.BAD_REQUEST);
	}

}
