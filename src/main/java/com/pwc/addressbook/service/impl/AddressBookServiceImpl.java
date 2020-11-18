package com.pwc.addressbook.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pwc.addressbook.entity.AddressBookEntity;
import com.pwc.addressbook.model.AddressBookDto;
import com.pwc.addressbook.repository.AddressBookRepository;
import com.pwc.addressbook.service.AddressBookService;
import com.pwc.addressbook.util.AddressBookConstants;

@Service("addressBookService")
@Transactional
public class AddressBookServiceImpl implements AddressBookService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AddressBookRepository addressBookRepository;

	private AddressBookDto convertToDto(AddressBookEntity adbEntity) {
		AddressBookDto adbDto = modelMapper.map(adbEntity, AddressBookDto.class);
		return adbDto;
	}

	@Override
	public List<AddressBookDto> fetchAllInfo() {
		List<AddressBookDto> adbDtoList = null;
		List<AddressBookEntity> adbEntityList = null;
		Sort.Order order = new Sort.Order(Sort.Direction.ASC, "name").ignoreCase();
		adbEntityList = addressBookRepository.findAll(Sort.by(order));
		if (adbEntityList != null && adbEntityList.size() > 0) {
			adbDtoList = adbEntityList.stream().map(this::convertToDto).collect(Collectors.toList());
		}
		return adbDtoList;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public List<AddressBookDto> addInfo(List<AddressBookDto> addressBookDtoList) throws Exception {

		Map<String, AddressBookEntity> adbEntityListMap = new HashMap<String, AddressBookEntity>();
		Map<String, AddressBookEntity> adbEntityListMapPersist = new HashMap<String, AddressBookEntity>();
		List<AddressBookEntity> adbEntityList = null;
		List<AddressBookEntity> newAdbEntityList = null;
		adbEntityList = addressBookRepository.findAll();
		if (adbEntityList != null && adbEntityList.size() > 0) {
			adbEntityListMap = adbEntityList.stream()
					.collect(Collectors.toMap(a->a.getName().toUpperCase(), adbEntity -> adbEntity));
		}
		for (int index = 0; index < addressBookDtoList.size(); index++) {
			if (StringUtils.isNotBlank(addressBookDtoList.get(index).getName())
					&& StringUtils.isNotBlank(addressBookDtoList.get(index).getPhnNumber())) {
				if (AddressBookConstants.NAME_REGEX_PATTERN.matcher(addressBookDtoList.get(index).getName()).find()
						&& AddressBookConstants.PHN_NUMBER_REGEX_PATTERN
								.matcher(addressBookDtoList.get(index).getPhnNumber()).find()) {
					AddressBookEntity l_adbEntity = null;
					l_adbEntity = adbEntityListMap.get(addressBookDtoList.get(index).getName().toUpperCase());
					if (l_adbEntity != null && StringUtils.isNotBlank(l_adbEntity.getName())
							&& l_adbEntity.getId() != null && l_adbEntity.getId() > 0) {
						if (!StringUtils.equals(l_adbEntity.getPhnNumber(),
								addressBookDtoList.get(index).getPhnNumber())) {
							l_adbEntity.setName(addressBookDtoList.get(index).getName());
							l_adbEntity.setPhnNumber(addressBookDtoList.get(index).getPhnNumber());
						} else {
							l_adbEntity=null;
						}
					} else {
						l_adbEntity = new AddressBookEntity();
						l_adbEntity.setName(addressBookDtoList.get(index).getName());
						l_adbEntity.setPhnNumber(addressBookDtoList.get(index).getPhnNumber());
					}
					if(l_adbEntity!=null) {
						adbEntityListMapPersist.put(l_adbEntity.getName().toUpperCase(), l_adbEntity);
					}
					addressBookDtoList.get(index).setStatus(AddressBookConstants.SUCCESS_MSG);
				} else {
					addressBookDtoList.get(index).setStatus(AddressBookConstants.INVALID_MSG);
				}
			} else {
				addressBookDtoList.get(index).setStatus(AddressBookConstants.INVALID_MSG);
			}
		}
		if (adbEntityListMapPersist != null && adbEntityListMapPersist.size() > 0) {
			newAdbEntityList = new ArrayList<AddressBookEntity>(adbEntityListMapPersist.values());
			if(newAdbEntityList!=null && newAdbEntityList.size()>0) {
				addressBookRepository.saveAll(newAdbEntityList);
			}
		}

		return addressBookDtoList;

	}

	@Override
	public List<AddressBookDto> getUniqueInfo(List<AddressBookDto> addressBookDtoList) throws Exception {
		List<String> names = null;
		List<AddressBookEntity> adbEntityListIn = null;
		List<AddressBookEntity> adbEntityListNotIn = null;
		List<AddressBookDto> adbDtoList = null;
		AddressBookEntity l_addressBookEntity = null;
		Map<String, AddressBookEntity> adbEntityListMap = new HashMap<String, AddressBookEntity>();
		
		if (addressBookDtoList != null && addressBookDtoList.size() > 0) {
			names = addressBookDtoList.stream().map(adbDtoObj -> adbDtoObj.getName().toUpperCase()).collect(Collectors.toList());
		}

		if (names != null && names.size() > 0) {
			adbEntityListIn = addressBookRepository.findByNamesIn(names);
			adbEntityListNotIn = addressBookRepository.findByNamesNotIn(names);
		}
		
		if (adbEntityListIn != null && adbEntityListIn.size() > 0) {
			adbEntityListMap = adbEntityListIn.stream()
					.collect(Collectors.toMap(a->a.getName().toUpperCase(), adbEntity -> adbEntity));
		}
		if (adbEntityListNotIn != null && adbEntityListNotIn.size() > 0) {
			adbDtoList = adbEntityListNotIn.stream().map(this::convertToDto).collect(Collectors.toList());
		}
		if(adbDtoList==null) {
			adbDtoList = new ArrayList<AddressBookDto>();
		}
		for (AddressBookDto eachAdbDto : addressBookDtoList) {
			l_addressBookEntity = adbEntityListMap.get(eachAdbDto.getName().toUpperCase());
			if(l_addressBookEntity==null) {
				adbDtoList.add(eachAdbDto);
			}
		}
		if(adbDtoList!=null && adbDtoList.size()>0) {
		Collections.sort(adbDtoList, new Comparator<AddressBookDto>() {

			public int compare(AddressBookDto adbDto1, AddressBookDto adbDto2) {
			   String adbName1 = adbDto1.getName().toUpperCase();
			   String adbtName2 = adbDto2.getName().toUpperCase();

			   //ascending order
			   return adbName1.compareTo(adbtName2);
		    }});
		}
		return adbDtoList;
	}

}