package com.spring.model;

import java.util.LinkedList;

import org.springframework.stereotype.Component;

import com.spring.dao.ContactDAO;
import com.spring.dao.UserContactDAO;

@Component
public class UserContactService implements ContactService {
	
	private ContactDAO contactDAO = new UserContactDAO();

	@Override
	public Contact getContact(int id) {
		Contact contact = contactDAO.searchContactById(id);
		return contact;
	}

	@Override
	public LinkedList<Contact> getAllContacts() {
		LinkedList<Contact> allContacts = contactDAO.getAllContacts();
		return allContacts;
	}

	@Override
	public void createContact(Contact contact) {
		contactDAO.createContact(contact);	
	}

	@Override
	public void deleteContact(Contact contact) {
		contactDAO.deleteContactById(contact.getId());	
	}

	@Override
	public Contact searchContact(Contact contact) {
		return contactDAO.searchContactById(contact.getId());
	}

	@Override
	public void updateContact(Contact contact) {
		contactDAO.updateContact(contact);
	}
}
