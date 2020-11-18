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
	
	public AddressBookEntity() {}
	
	public AddressBookEntity(String name, String phnNumber) {
		super();
		this.name = name;
		this.phnNumber = phnNumber;
	}

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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phnNumber == null) ? 0 : phnNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddressBookEntity other = (AddressBookEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phnNumber == null) {
			if (other.phnNumber != null)
				return false;
		} else if (!phnNumber.equals(other.phnNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AddressBookEntity [id=" + id + ", name=" + name + ", phnNumber=" + phnNumber + "]";
	}
	

	
}