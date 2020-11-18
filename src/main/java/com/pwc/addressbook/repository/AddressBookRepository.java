package com.pwc.addressbook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pwc.addressbook.entity.AddressBookEntity;

@Repository("addressBookRepository")
public interface AddressBookRepository extends JpaRepository<AddressBookEntity, Integer> {
    @Query("select adbEntity from AddressBookEntity adbEntity where UPPER(adbEntity.name) IN (:names)")
    public List<AddressBookEntity> findByNamesIn(@Param("names") List<String> names);
    @Query("select adbEntity from AddressBookEntity adbEntity where UPPER(adbEntity.name) NOT IN (:names)")
    public List<AddressBookEntity> findByNamesNotIn(List<String> names);
	public long countById(Long id);
}