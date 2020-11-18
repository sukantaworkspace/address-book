package com.pwc.addressbook.service;

import java.util.List;

import com.pwc.addressbook.model.AddressBookDto;

public interface AddressBookService {
	public List<AddressBookDto> fetchAllInfo();
	public List<AddressBookDto> addInfo(List<AddressBookDto> addressBookDtoList) throws Exception;
	public List<AddressBookDto> getUniqueInfo(List<AddressBookDto> addressBookDtoList) throws Exception;
}