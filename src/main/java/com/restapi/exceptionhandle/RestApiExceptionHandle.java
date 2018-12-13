package com.restapi.exceptionhandle;

import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.restapi.commons.ErrorResponse;

@ControllerAdvice //Controller que observa toda aplicação
public class RestApiExceptionHandle extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messagePropertie; // Get file messages.properties 
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setUserMessage(messagePropertie.getMessage("invalid.message", null, LocaleContextHolder.getLocale()));
		errorResponse.setDeveloperMessage(null != ex.getCause() ? ex.getCause().toString() : ex.toString());
		
		return super.handleExceptionInternal(ex, errorResponse, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return super.handleExceptionInternal(ex, createErrorList(ex.getBindingResult()) ,headers, HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler({EmptyResultDataAccessException.class}) // I can pass a list of exception that this handle need to capture
	//@ResponseStatus(HttpStatus.NOT_FOUND)
	//private void handleEmptyResultDataAccessException(RuntimeException ex) {
	private ResponseEntity<Object> handleEmptyResultDataAccessException(RuntimeException ex, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setUserMessage(messagePropertie.getMessage("resource.notfound", null, LocaleContextHolder.getLocale()));
		errorResponse.setDeveloperMessage(ex.toString());
		
		return super.handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler({DataIntegrityViolationException.class})
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request){
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setUserMessage(messagePropertie.getMessage("operation-not-allowed", null, LocaleContextHolder.getLocale()));
		errorResponse.setDeveloperMessage(org.flywaydb.core.internal.util.ExceptionUtils.getRootCause(ex).getMessage());
		//errorResponse.setDeveloperMessage(ExceptionUtils.getRootCauseMessage(ex)); TODO WITH COMMONS-LANG3
		return this.handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	private List<ErrorResponse> createErrorList(BindingResult bindingResult){
		List<ErrorResponse> errors = new ArrayList<>();
		
		bindingResult.getFieldErrors().forEach(errorField ->{
			String userMessage = messagePropertie.getMessage(errorField, LocaleContextHolder.getLocale());
			String developerMessage = errorField.toString();
			errors.add(new ErrorResponse(userMessage, developerMessage));			
		});
		return errors;
	}
}
