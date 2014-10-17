package com.spring.dao;

import java.util.LinkedList;

import com.spring.model.Contact;

public interface ContactDAO {
	
	void createContact(Contact contact);
	void deleteContactById(int id);
	void updateContact(Contact contact);
	Contact searchContactById(int id);
	LinkedList<Contact> getAllContacts();
}