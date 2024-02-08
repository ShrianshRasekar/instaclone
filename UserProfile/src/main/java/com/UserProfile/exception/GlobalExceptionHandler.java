package com.UserProfile.exception;


import java.util.HashMap;
import java.util.Map;

import org.hibernate.query.NativeQuery.ReturnableResultNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value= {ProfileNotFoundException.class})
	public ResponseEntity<Object> handleProfileNotFoundException(ProfileNotFoundException profileNotFoundException) {
		{
			ProfileException profileException = new ProfileException(profileNotFoundException.getMessage(),
					profileNotFoundException.getCause(), HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(profileException, HttpStatus.NOT_FOUND);
		}

	}
	
	@ExceptionHandler(value= {ProfileDetailAlreadyExist.class})
	public ResponseEntity<Object> handleProfileDetailAlreadyExist(ProfileDetailAlreadyExist profileDetailAlreadyExist) {
		{
			ProfileException profileException = new ProfileException(profileDetailAlreadyExist.getMessage(),
					profileDetailAlreadyExist.getCause(), HttpStatus.BAD_REQUEST);
			return new ResponseEntity<>(profileException, HttpStatus.BAD_REQUEST);
		}

	}
	
	@ExceptionHandler(value= {ProfileDetailsMissingException.class})
	public ResponseEntity<Object> handleProfileDetailsMissingException(ProfileDetailsMissingException profileDetailsMissingException) {
		{
			ProfileException profileException = new ProfileException(profileDetailsMissingException.getMessage(),
					profileDetailsMissingException.getCause(), HttpStatus.BAD_REQUEST);
			return new ResponseEntity<>(profileException, HttpStatus.BAD_REQUEST);
		}

	}
	
	
	@ExceptionHandler(value= {MethodArgumentNotValidException.class})
	public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		{
			Map<String, String> resp=new HashMap<>();
			ex.getBindingResult().getAllErrors().forEach((error)-> {
				String fieldname = ((FieldError)error).getField();
				String message = ((FieldError)error).getDefaultMessage();
				resp.put(fieldname, message);
			});
			return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
		}

	}
	
	/*
	 * @ExceptionHandler(value= {HttpRequestMethodNotSupportedException.class})
	 * public String handleHttpRequestMethodNotSupportedException() { { return
	 * "Enter All field values correct"; }
	 * 
	 * }
	 * 
	 * @ExceptionHandler(value= {TransactionSystemException.class}) public String
	 * handleTransactionSystemException() { { return
	 * "Enter All field values correct"; }
	 * 
	 * }
	 */
}
