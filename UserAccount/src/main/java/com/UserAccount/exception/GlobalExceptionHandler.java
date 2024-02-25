package com.UserAccount.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value= {UserNotFoundException.class})
	public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException userNotFoundException) {
		{
			UserException userException = new UserException(userNotFoundException.getMessage(),
					userNotFoundException.getCause(), HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(userException, HttpStatus.NOT_FOUND);
		}

	}
	
	@ExceptionHandler(value= {UserDetailAlreadyExist.class})
	public ResponseEntity<Object> handleUserDetailAlreadyExist(UserDetailAlreadyExist userDetailAlreadyExist) {
		{
			UserException userException = new UserException(userDetailAlreadyExist.getMessage(),
					userDetailAlreadyExist.getCause(), HttpStatus.BAD_REQUEST);
			return new ResponseEntity<>(userException, HttpStatus.BAD_REQUEST);
		}

	}
	
	@ExceptionHandler(value= {UserDetailsMissingException.class})
	public ResponseEntity<Object> handleUserDetailsMissingException(UserDetailsMissingException userDetailsMissingException) {
		{
			UserException userException = new UserException(userDetailsMissingException.getMessage(),
					userDetailsMissingException.getCause(), HttpStatus.BAD_REQUEST);
			return new ResponseEntity<>(userException, HttpStatus.BAD_REQUEST);
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
