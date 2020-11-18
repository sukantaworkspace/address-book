package com.pwc.addressbook.rest.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pwc.addressbook.model.AddressBookDto;
import com.pwc.addressbook.rest.common.CommonExceptionHandlingController;
import com.pwc.addressbook.rest.event.ResponseEvent;
import com.pwc.addressbook.rest.exception.RestException;
import com.pwc.addressbook.service.AddressBookService;

@RestController("addressBookController")
@RequestMapping("/address-book")
public class AddressBookController extends CommonExceptionHandlingController{ 
	
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AddressBookController.class);
	
	@Autowired
	private AddressBookService addressBookService;
	
	@Autowired
	private HttpServletRequest request;

	/**
	 * 
	 * @return ResponseEntity as list of category, all null values in object will be ignored
	 * @throws Exception
	 */
	@RequestMapping(value={"/v1.0/get-all-info", "/v1.1/get-all-info"},method= RequestMethod.GET, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent<List<AddressBookDto>>> getAllInfo() throws Exception { 
		LOGGER.info("sessionID: "+request.getHeader("AUTH_HEADER")+ "=====> get-all-info");
		List<AddressBookDto> adbDtoList = null;
		adbDtoList=addressBookService.fetchAllInfo();
		if(adbDtoList==null) {
			RestException restException = new RestException();
			restException.setErrorCode(HttpStatus.NO_CONTENT);
			restException.setErrorMessage("No information found");
			throw restException;
		}
		return new ResponseEntity<ResponseEvent<List<AddressBookDto>>>(ResponseEvent.response(adbDtoList), HttpStatus.OK);
		
	}
	
	/**
	 * 
	 * @return ResponseEntity as list of category, all null values in object will be ignored
	 * @throws Exception
	 */
	@RequestMapping(value={"/v1.1/add-info"},method= RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent<List<AddressBookDto>>> addInfo(@RequestBody List<AddressBookDto> adbDtoList) throws Exception {
		LOGGER.info("sessionID: "+request.getHeader("AUTH_HEADER")+ "=====> add-info");
		if(adbDtoList==null) {
			RestException restException = new RestException();
			restException.setErrorCode(HttpStatus.BAD_REQUEST);
			restException.setErrorMessage("Data can not be empty !!");
			throw restException;
		}
		adbDtoList=addressBookService.addInfo(adbDtoList);
		if(adbDtoList==null) {
			RestException restException = new RestException();
			restException.setErrorCode(HttpStatus.EXPECTATION_FAILED);
			restException.setErrorMessage("Failed to save information");
			throw restException;
		}
		return new ResponseEntity<ResponseEvent<List<AddressBookDto>>>(ResponseEvent.response(adbDtoList), HttpStatus.OK);
		
	}
	
	/**
	 * 
	 * @return ResponseEntity as list of category, all null values in object will be ignored
	 * @throws Exception
	 */
	@RequestMapping(value={"/v1.1/get-unique-info"},method= RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent<List<AddressBookDto>>> getUniqueInfo(@RequestBody List<AddressBookDto> adbDtoList) throws Exception {
		LOGGER.info("sessionID: "+request.getHeader("AUTH_HEADER")+ "=====> get-unique-info");
		if(adbDtoList==null) {
			RestException restException = new RestException();
			restException.setErrorCode(HttpStatus.BAD_REQUEST);
			restException.setErrorMessage("Data can not be empty !!");
			throw restException;
		}
		adbDtoList=addressBookService.getUniqueInfo(adbDtoList);
		if(adbDtoList==null) {
			RestException restException = new RestException();
			restException.setErrorCode(HttpStatus.NOT_FOUND);
			restException.setErrorMessage("No union of the relative complements data is found");
			throw restException;
		}
		return new ResponseEntity<ResponseEvent<List<AddressBookDto>>>(ResponseEvent.response(adbDtoList), HttpStatus.OK);
		
	}
}