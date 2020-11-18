package com.pwc.addressbook.service;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import com.pwc.addressbook.entity.AddressBookEntity;
import com.pwc.addressbook.model.AddressBookDto;
import com.pwc.addressbook.repository.AddressBookRepository;
import com.pwc.addressbook.util.AddressBookConstants;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class AddressBookServiceTest {

	@MockBean
	private AddressBookRepository addressBookRepository;
	
	@Autowired
	private AddressBookService addressBookService;
	
	private static List<AddressBookEntity> adbEntityList = new ArrayList<AddressBookEntity>();
	
	@BeforeAll
    public static void setup() {
		adbEntityList.add(new AddressBookEntity("Another Name", "9081726300"));
		adbEntityList.add(new AddressBookEntity("Full Name", "9081726354"));
    }
	
	@Test
	public void testFetchAllInfoFromDBInAscendingOrderOfNameGivenCaseInsensitive() {
		//Assign
		List<AddressBookDto> adbDtoList = null;
		when(addressBookRepository.findAll(any(Sort.class))).thenReturn(adbEntityList);
		
		//Act
		adbDtoList=addressBookService.fetchAllInfo();
		
		System.out.println("====================>>>>>>>>>>>>"+addressBookService.fetchAllInfo());
		
		//Assert
		assertEquals(adbDtoList.get(0).getName(), adbEntityList.get(0).getName());
		assertEquals(adbDtoList.get(0).getPhnNumber(), adbEntityList.get(0).getPhnNumber());
		assertEquals(adbDtoList.get(1).getName(), adbEntityList.get(1).getName());
		assertEquals(adbDtoList.get(1).getPhnNumber(), adbEntityList.get(1).getPhnNumber());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testAddInfoToDB() {
		//Assign
		List<AddressBookDto> _adbDtoList = new ArrayList<AddressBookDto>();
		_adbDtoList.add(new AddressBookDto("Another Name", "9081726399"));
		_adbDtoList.add(new AddressBookDto("full Name", "9081726300"));
		_adbDtoList.add(new AddressBookDto("Cool Name", "9081726355"));
		List<AddressBookDto> adbDtoList = null;
		when(addressBookRepository.findAll()).thenReturn(adbEntityList);
		when(addressBookRepository.saveAll(any(ArrayList.class))).thenReturn(adbEntityList);
		
		//Act
		try {
			adbDtoList=addressBookService.addInfo(_adbDtoList);
		} catch (Exception e) {
		}
		
		System.out.println("====================>>>>>>>>>>>>"+adbDtoList.toString());
		
		//Assert
		assertEquals(adbDtoList.get(0).getStatus(), AddressBookConstants.SUCCESS_MSG);
		assertEquals(adbDtoList.get(1).getStatus(), AddressBookConstants.SUCCESS_MSG);
		assertEquals(adbDtoList.get(2).getStatus(), AddressBookConstants.SUCCESS_MSG);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testgetUniqueInfo() {
		//Assign
		List<AddressBookDto> _adbDtoList = new ArrayList<AddressBookDto>();
		_adbDtoList.add(new AddressBookDto("cool Name", "9081726399"));
		_adbDtoList.add(new AddressBookDto("full Name", "9081726300"));
		List<AddressBookDto> adbDtoList = null;
		

		List<AddressBookEntity> adbEntityListIn = new ArrayList<AddressBookEntity>();
		adbEntityListIn.add(new AddressBookEntity("Full Name", "9081726354"));
		
		List<AddressBookEntity> adbEntityListNotIn =  new ArrayList<AddressBookEntity>();
		adbEntityListNotIn.add(new AddressBookEntity("Another Name", "9081726300"));
		
		when(addressBookRepository.findByNamesIn(any(ArrayList.class))).thenReturn(adbEntityListIn);
		when(addressBookRepository.findByNamesNotIn(any(ArrayList.class))).thenReturn(adbEntityListNotIn);
		
		//Act
		try {
			adbDtoList=addressBookService.getUniqueInfo(_adbDtoList);
		} catch (Exception e) {
		}
		
		System.out.println("====================>>>>>>>>>>>>"+adbDtoList.toString());
		
		//Assert
		assertEquals(adbDtoList.size(), 2);
		assertEquals(adbDtoList.get(0).getName(), adbEntityList.get(0).getName());
		assertEquals(adbDtoList.get(0).getPhnNumber(), adbEntityList.get(0).getPhnNumber());
		assertEquals(adbDtoList.get(1).getName(), _adbDtoList.get(0).getName());
		assertEquals(adbDtoList.get(1).getPhnNumber(), _adbDtoList.get(0).getPhnNumber());
	}
}
