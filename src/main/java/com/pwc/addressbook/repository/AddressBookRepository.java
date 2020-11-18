package com.pwc.addressbook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pwc.addressbook.entity.AddressBookEntity;

@Repository("addressBookRepository")
public interface AddressBookRepository extends JpaRepository<AddressBookEntity, Integer> {
    //@Query("select adbEntity from AddressBookEntity adbEntity where adbEntity.name NOT IN (:names) order by adbEntity.name")
    public List<AddressBookEntity> findByNameIn(List<String> names);
    public List<AddressBookEntity> findByNameNotIn(List<String> names);
	public long countById(Long id);
}