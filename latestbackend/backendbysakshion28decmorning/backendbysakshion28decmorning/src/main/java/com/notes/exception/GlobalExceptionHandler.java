package com.notes.exception;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.notes.custom_exception.ApiException;
import com.notes.custom_exception.AuthenticationException;
import com.notes.custom_exception.ResourceNotFoundException;
import com.notes.dto.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse(e.getMessage(), "Failed"));
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleInvalidInputException(ApiException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse(e.getMessage(), "Failed"));
    }

    @ExceptionHandler(AuthenticationException.class)  // ✔ FIXED
    public ResponseEntity<?> handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ApiResponse(e.getMessage(), "Failed"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse(e.getMessage(), "Failed"));
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		System.out.println("in validation failure - req body ");
		// 1. Get the list of field errors
		List<FieldError> fieldErrors = e.getFieldErrors();
		// 2. Covert List<FieldError> -> Map<Key : fieldName: String , Value- err mesg :
		// String
		/*
		 * Traditional (non FP) - imperative
		 */
//		Map<String, String> errorMap = new HashMap<>();
//		for (FieldError field : fieldErrors) {
//			errorMap.put(field.getField(), field.getDefaultMessage());
//		}
		Map<String, String> errorMap = fieldErrors.stream() //Stream<FieldError>
		.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST) //SC 400
				.body(errorMap); //rejected field - err mesg
	}

}
