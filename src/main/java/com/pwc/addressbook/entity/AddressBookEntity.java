package com.pwc.addressbook.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="address_book")

public class AddressBookEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="adb_seq")
	@SequenceGenerator(name = "adb_seq", sequenceName = "adb_seq", initialValue = 1, allocationSize=1)
	@Column(name="id")
	private Long id;
	
	@Column(name="name", nullable = false, length = 50)
	private String name;
	
	@Column(name="phn_number", nullable = false, length = 10)
	private String phnNumber;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhnNumber() {
		return phnNumber;
	}

	public void setPhnNumber(String phnNumber) {
		this.phnNumber = phnNumber;
	}

	@Override
	public String toString() {
		return "AddressBookEntity [id=" + id + ", name=" + name + ", phnNumber=" + phnNumber + "]";
	}
	

	
}