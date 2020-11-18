package com.pwc.addressbook.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressBookDto implements Serializable {
	
	private static final long serialVersionUID = -5976898854489260010L;
	
	@NotBlank(message = "Name is mandatory")
	private String name;
	
	private String phnNumber;
	
	public AddressBookDto(){};
	public AddressBookDto(@NotBlank(message = "Name is mandatory") String name, String phnNumber) {
		super();
		this.name = name;
		this.phnNumber = phnNumber;
	}

	private String status;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "AddressBookDto [name=" + name + ", phnNumber=" + phnNumber + ", status=" + status + "]";
	}
	
}