package com.spring.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.mock.staticmock.MockStaticEntityMethods;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.spring.exception.ContactNotFoundException;
import com.spring.model.Address;
import com.spring.model.Contact;

/**
 * This class simulate a database
 */
public class Data {
	
	private static Data INSTANCE = null;
	private LinkedList<Contact> listOfContacts;
	private int index = 0;
	Gson gson = new Gson(); 
	
	private Data(){ init(); }
 
	private void init() {
		//Resource resource = new FileSystemResource("C:\\Users\\Ultra-Polo\\Dropbox\\Spring project\\ContactBook\\src\\MOCK_DATA.json");
		
		listOfContacts = new LinkedList<Contact>();
		    
		System.out.println("Loading JSON files");  
		System.out.println("------------ Load contacts ------------"); 
		
		InputStream inputStream = Data.class.getResourceAsStream("CONTACTS.json");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));			

		Type collectionType = new TypeToken<LinkedList<Contact>>() {}.getType();
		listOfContacts = gson.fromJson(bufferedReader, collectionType);
		
		System.out.println("------------ End ------------"); 
		   
		for (int i = 0; i < listOfContacts.size(); i++) {  
			System.out.println(listOfContacts.get(i).toString());  
		}
		
		System.out.println("\n------------ Load billing addresses ------------");
		
		LinkedList<Address> billingAddressOfContact = new LinkedList<Address>();
		
		inputStream = Data.class.getResourceAsStream("BILLING_ADDRESSES.json");
		bufferedReader = new BufferedReader(new InputStreamReader(inputStream));			

		collectionType = new TypeToken<LinkedList<Address>>() {}.getType();
		billingAddressOfContact = gson.fromJson(bufferedReader, collectionType);
		
		for (int i = 0; i < listOfContacts.size(); i++) {
			listOfContacts.get(i).addAddress(billingAddressOfContact.get(i));
			System.out.println(billingAddressOfContact.get(i).toString());
		}
		
		System.out.println("------------ End ------------"); 
	}

	public static Data getInstance() {	
		if (INSTANCE == null) {
			INSTANCE = new Data();	
		}
		return INSTANCE;
	}
	
	public int getCurrentId() {
		return this.index;
	}
	
	public LinkedList<Contact> getAllContacts() {
		return this.listOfContacts;
	}
	
	public void createContact(Contact contact) {
		contact.setId(index++);
		listOfContacts.add(contact);
	}
	
	public void deleteContactById(int id) {
		Contact contact = searchContactById(id);
		this.listOfContacts.remove(contact);
	}
	
	public Contact searchContactById(int id) {
		try {
			for (Contact contact : this.listOfContacts) {
				if(contact.getId() == id) {
					return contact;
				}
			}
			throw new ContactNotFoundException("Le contact recherchée est introuvable.");
		} catch (ContactNotFoundException e) {
			return null;
		}
	}
	
	public Contact updateContactById(Contact contact) {
		try {
			for (Contact currentContact : this.listOfContacts) {
				if(currentContact.getId() == contact.getId()) {
					currentContact = contact;
				}
			}
			throw new ContactNotFoundException("Le contact recherchée est introuvable.");
		} catch (ContactNotFoundException e) {
			return null;
		}
	}
	
	public LinkedList<Contact> searchContactByFirstName(String FirstName) {
		LinkedList<Contact> machingNamesList = new LinkedList<Contact>();
		try {
			for (Contact contact : this.listOfContacts) {
				if(contact.getFirstName() == FirstName) {
					machingNamesList.add(contact);
				}
			}
			if(machingNamesList.isEmpty()) {
				throw new ContactNotFoundException("Le contact recherchée est introuvable.");
			}
			return machingNamesList;
		} catch (ContactNotFoundException e) {
			return null;
		}
	}
}
