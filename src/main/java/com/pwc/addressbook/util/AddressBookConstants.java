package com.pwc.addressbook.util;

import java.util.regex.Pattern;

public class AddressBookConstants {

	public static final String SUCCESS_MSG = "Saved Successfully";
	
	public static final String INVALID_MSG = "Wrong formatted name/phone number is given";
	
	public static final String NAME_REGEX  = "^[a-zA-Z]{4,24}(?: [a-zA-Z]{1,24}+){0,2}$";
	
	public static final String PHN_NUMBER_REGEX  = "^\\d{10}+$";
	
	public static final Pattern NAME_REGEX_PATTERN = Pattern.compile(NAME_REGEX);
	
	public static final Pattern PHN_NUMBER_REGEX_PATTERN = Pattern.compile(PHN_NUMBER_REGEX);
	
}
