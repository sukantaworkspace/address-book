package com.pwc.addressbook.rest.common;


import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.pwc.addressbook.rest.event.ResponseEvent;
import com.pwc.addressbook.rest.exception.ErrorResponse;
import com.pwc.addressbook.rest.exception.RestException;

/**
 * 
 * @author Sukanta
 * @since 30-Nov-2018
 * @Purpose This object will return the custom exception in the rest call
 */
@RestController("commonExceptionHandlingController")
public class CommonExceptionHandlingController {
	
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CommonExceptionHandlingController.class);
	
	@ExceptionHandler(RestException.class)
	public ResponseEntity<ResponseEvent<ErrorResponse>> exceptionHandler(Exception ex) {
		LOGGER.info(" Inside CommonExceptionHandlingController" + ex.getClass());
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		String stackTrace = sw.toString();
		LOGGER.error("Exception - " + stackTrace);
		RestException restException = (RestException) ex;
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(restException.getErrorCode().value());
		error.setErrorMessage(restException.getErrorMessage());
		LOGGER.info(restException.getErrorMessage());
		return new ResponseEntity<ResponseEvent<ErrorResponse>>(ResponseEvent.errors(error), restException.getErrorCode());
	}

	
	

}
