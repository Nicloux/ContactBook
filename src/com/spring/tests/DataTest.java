package com.spring.tests;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import com.spring.data.Data;
import com.spring.model.Contact;

public class DataTest {
	/*
	private Data data = Data.getInstance();
	private int i = 0;
	
	@Before
	public void setup() {
		i = 0;
	}
	
	private void printContact(Contact expected, Contact actual) {
		System.out.println("Expected: " + expected.toString());
		System.out.println("Actual: " + actual.toString());
	}
	
	private boolean compareContact(Contact expected, Contact actual) {
		if (expected == null && actual == null) {
			System.out.println("Error: both contact null.");
			return false;
		}
			
		if(expected.getFirstName() != actual.getFirstName()) {
			System.out.println("Error: contact firstName.");
			printContact(expected, actual);
			return false;
		}
		
		if(expected.getLastName() != actual.getLastName()) {
			System.out.println("Error: contact lasttName.");
			printContact(expected, actual);
			return false;
		}
		
		if(expected.getAddress() != actual.getAddress()) {
			System.out.println("Error: contact address.");
			printContact(expected, actual);
			return false;
		}
		
		return true;
	}
	

	@Test
	public void addContactsToDatabaseTest() {
		System.out.println("\n##### <Test 1> #####");
		
		String inputTest = "Add new contact 'John Doe' to the database.";
		System.out.println("Test: " + inputTest);
		
		LinkedList<Contact> actualContactList = new LinkedList<Contact>();
		LinkedList<Contact> expectedContactList = new LinkedList<Contact>();
		
		Contact newContact = new Contact("John", "Doe", "nowhere");
		data.createContact(newContact);
		
		actualContactList = data.getAllContacts();
		
		expectedContactList.add(new Contact("Paul", "Nicloux", "70 rue Paul Vaillant Couturier 94140 Alfortville"));
		expectedContactList.add(new Contact("Marion", "Jacquemin", "70 rue Paul Vaillant Couturier 94140 Alfortville"));
		expectedContactList.add(new Contact("Ugo", "Budin", "Paris"));
		expectedContactList.add(new Contact("Didier", "Jacquemin", "877 chemin de Rebaumont 88380 Arches"));
		expectedContactList.add(new Contact("Odile", "Koenig", "1 chemin de la Beulauderie 88220 Hadol"));
		expectedContactList.add(new Contact("John", "Doe", "nowhere"));
		
		System.out.println("Check expected size of contact list: expected: " + expectedContactList.size() + " <-> actual: " + actualContactList.size());
		assertEquals(expectedContactList.size(), actualContactList.size(), 0);
		
		for(Contact contact : expectedContactList) {
			assertTrue(compareContact(contact, actualContactList.get(i++)));
		}
		System.out.println("check of the list of contact: OK ");

		System.out.println("##### </Test 1> #####");
	}
	
	@Test
	public void removeContactFromDatabaseTest() {
		System.out.println("\n##### <Test 2> #####");
		
		String inputTest = "remove contact 'John Doe' from the database.";
		System.out.println("Test: " + inputTest);
		
		LinkedList<Contact> actualContactList = new LinkedList<Contact>();
		LinkedList<Contact> expectedContactList = new LinkedList<Contact>();
		
		data.deleteContactById(5); // John Doe have the id number 5
		
		actualContactList = data.getAllContacts();
		
		expectedContactList.add(new Contact("Paul", "Nicloux", "70 rue Paul Vaillant Couturier 94140 Alfortville"));
		expectedContactList.add(new Contact("Marion", "Jacquemin", "70 rue Paul Vaillant Couturier 94140 Alfortville"));
		expectedContactList.add(new Contact("Ugo", "Budin", "Paris"));
		expectedContactList.add(new Contact("Didier", "Jacquemin", "877 chemin de Rebaumont 88380 Arches"));
		expectedContactList.add(new Contact("Odile", "Koenig", "1 chemin de la Beulauderie 88220 Hadol"));
		
		System.out.println("Check expected size of contact list: expected: " + expectedContactList.size() + " <-> actual: " + actualContactList.size());
		assertEquals(expectedContactList.size(), actualContactList.size(), 0);
		
		for(Contact contact : expectedContactList) {
			assertTrue(compareContact(contact, actualContactList.get(i++)));
		}
		System.out.println("check of the list of contact: OK ");

		System.out.println("##### </Test 2> #####");
	}
	
	@Test
	public void searchContactByIdTest() {
		System.out.println("\n##### <Test 3> #####");
		
		String inputTest = "Search contact 'Paul Nicloux' from the database.";
		System.out.println("Test: " + inputTest);
		
		Contact expected = new Contact("Paul", "Nicloux", "70 rue Paul Vaillant Couturier 94140 Alfortville");
		Contact actual = data.searchContactById(0); // The first contact is Paul Nicloux
		
		assertTrue(compareContact(expected, actual));
		
		System.out.println("Find the contact: OK ");

		System.out.println("##### </Test 3> #####");
	}
*/

}
