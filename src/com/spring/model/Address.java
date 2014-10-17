package com.spring.model;

public class Address {
	
	public final static String BILLING = "BILLING";
	public final static String DELIVRERY = "DELIVRERY";
	public final static String BOTH = "BOTH";
	
	private int contactId;
	private String type;
	private String street;
	private String zip;
	private String city;
	private String country;

	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getPostalCode() {
		return zip;
	}
	
	public void setPostalCode(String postalCode) {
		this.zip = postalCode;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	@Override
	public String toString() {
		return "Address [contactId=" + contactId + ", type=" + type
				+ ", street=" + street + ", zip=" + zip
				+ ", city=" + city + ", country=" + country + "]";
	}
}
