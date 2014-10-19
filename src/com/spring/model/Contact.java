package com.spring.model;

import java.util.LinkedList;

import com.google.gson.annotations.Expose;

public class Contact {
	
	@Expose private int id;
	@Expose private String firstName;
	@Expose private String lastName;
	@Expose private String birthDate;
	@Expose private String phoneNumber;
	@Expose private String email;
	@Expose(serialize = false) private LinkedList<Address> addresses;
	@Expose private String isActive;

	public Contact() {
		addresses = new LinkedList<Address>();
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public LinkedList<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(LinkedList<Address> addresses) {
		this.addresses = addresses;
	}
	
	public void addAddress(Address addresse) {
		this.addresses.add(addresse);
	}
	
	public String isActive() {
		return isActive;
	}

	public void setActive(String isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", birthDate=" + birthDate + ", phoneNumber="
				+ phoneNumber + ", email=" + email + ", isActive=" + isActive + "]";
	}
}