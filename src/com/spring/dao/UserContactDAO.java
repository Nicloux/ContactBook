package com.spring.dao;

import java.util.LinkedList;

import com.spring.data.Data;
import com.spring.exception.ContactNotFoundException;
import com.spring.model.Contact;

/**
 * Provides access to data for a standard user
 * */
public class UserContactDAO implements ContactDAO {
	
	Data data;
	
	public UserContactDAO() {
		data = Data.getInstance();
	}

	@Override
	public void createContact(Contact contact) {
		this.data.createContact(contact);
	}

	@Override
	public void deleteContactById(int id) {
		data.deleteContactById(id);
	}

	@Override
	public Contact searchContactById(int id) {
		return data.searchContactById(id);
	}

	@Override
	public LinkedList<Contact> getAllContacts() {
		return data.getAllContacts();
	}

	@Override
	public void updateContact(Contact contact) {
		data.updateContactById(contact);
	}
}
