package com.spring.model;

import java.util.LinkedList;

public interface ContactService {
	
	public Contact getContact(int id);
	public LinkedList<Contact> getAllContacts();
	public void createContact(Contact contact);
	public void deleteContact(Contact contact);
	public void updateContact(Contact contact);
	public Contact searchContact(Contact contact);
}
