package com.spring.model;

import java.util.HashMap;
import java.util.LinkedList;

public class Contact {
	
	private int id;
	private String firstName;
	private String lastName;
	private String birthDate;
	private String phoneNumber;
	private String email;
	private LinkedList<Address> addresses;
	private boolean isActive;
	
	public Contact(String firstName, String lastName, String birthDate, String phoneNumber, String email, boolean isActive) {
		addresses = new LinkedList<Address>();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.isActive = isActive;
	}

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
	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", birthDate=" + birthDate + ", phoneNumber="
				+ phoneNumber + ", email=" + email + ", isActive=" + isActive + "]";
	}
}