package com.spring.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.spring.model.Address;
import com.spring.model.Contact;
import com.spring.model.ContactService;

@RestController
@RequestMapping(value = "/Controller")
public class ContactController {
	
	@Autowired private ContactService contactService;
	
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private enum Action {list, create, update, delete;}
	private enum ContactField {id, firstName, lastName, birthDate, phoneNumber, email, isActive;}
	
	@RequestMapping(value = "/", method = { RequestMethod.GET, RequestMethod.POST })
    protected String catchAllOthers() {
        System.out.println("Default mapping");
        String error = "{\"Result\": \"ERROR\",\"Message\": There is nothing here.\"}";
		return error;
    }
	
	@RequestMapping(value = "/address", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String doActionAddress(
			@RequestParam(value = "action", 	required = true, defaultValue = "") 	String value,
			@RequestParam(value = "contactId", 	required = true, defaultValue = "") 	String stringContactId,
			@RequestParam(value = "type", 		required = true, defaultValue = "") 	String type,
			@RequestParam(value = "street", 	required = true, defaultValue = "") 	String street,
			@RequestParam(value = "zip", 		required = true, defaultValue = "") 	String zip,
			@RequestParam(value = "city", 		required = true, defaultValue = "") 	String city,
			@RequestParam(value = "country", 	required = true, defaultValue = "") 	String country) {
		
		try {
			String response = "";
			Contact contact;
			HashMap<String, String> requestParameters = new HashMap<String, String>();
			
			int contactId = Integer.parseInt(stringContactId);
			
			requestParameters.put("contactId", stringContactId);
			requestParameters.put("type", type);
			requestParameters.put("street", street);
			requestParameters.put("zip", zip);
			requestParameters.put("city", city);
			requestParameters.put("country", country);
			
			if(value.isEmpty() || value == null) {
				throw new IllegalArgumentException();
			}
			
			Action action = Action.valueOf(value);
			
			switch(action) {
			case list: 		response = getContactAddresses(contactId); 
							break;
			case create: 	contact = createContactFromRequestParameters(requestParameters);
							response = createContact(contact);
							break;
			case update: 	contact = createContactFromRequestParameters(requestParameters);
							response = updateContact(contact);
							break;
			case delete: 	contact = createContactFromRequestParameters(requestParameters);
							response = deleteContact(contact);
							break;
			default: throw new IllegalArgumentException("Action non autorisée.");
			}
			System.out.println(response);
			return response;
			
		} catch(IllegalArgumentException e) {
			String error = "{\"Result\": \"ERROR\",\"Message\": " + e.getMessage() + "}";
			return error;
		}
	}
	
	private String getContactAddresses(int contactId) {
		LinkedList<Address> contactAddresses = contactService.getContact(contactId).getAddresses();
		String contactJson = gson.toJson(contactAddresses);
		String response = "{\"Result\":\"OK\",\"Records\":"+ contactJson +"}";
		return response;
	}
	/**
	 * @param action: action to execute: list, create, update, delete. Passed by GET
	 * The other parameters are the fields of the Contact object, passed by POST (not visible in the url)
	 */
	@RequestMapping(value = "/contact", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String doActionContact(
			@RequestParam(value = "action", 		required = true, defaultValue = "list") String value,
			@RequestParam(value = "firstName", 		required = true, defaultValue = "") String firstName,
			@RequestParam(value = "lastName", 		required = true, defaultValue = "") String lastName,
			@RequestParam(value = "birthDate", 		required = true, defaultValue = "") String birthDate,
			@RequestParam(value = "phoneNumber",	required = true, defaultValue = "") String phoneNumber,
			@RequestParam(value = "email", 			required = true, defaultValue = "") String email,
			@RequestParam(value = "isActive", 		required = true, defaultValue = "") String isActive) {
		
		try {
			String response = "";
			Contact contact;
			HashMap<String, String> requestParameters = new HashMap<String, String>();
			
			requestParameters.put("firstName", firstName);
			requestParameters.put("lastName", lastName);
			requestParameters.put("birthDate", birthDate);
			requestParameters.put("phoneNumber", phoneNumber);
			requestParameters.put("email", email);
			requestParameters.put("isActive", isActive);
			
			
			if(value.isEmpty() || value == null) {
				throw new IllegalArgumentException();
			}
			
			Action action = Action.valueOf(value);
			
			switch(action) {
				case list: 		response = getAllContacts(); 
								break;
				case create: 	contact = createContactFromRequestParameters(requestParameters);
								response = createContact(contact);
								break;
				case update: 	contact = createContactFromRequestParameters(requestParameters);
								response = updateContact(contact);
								break;
				case delete: 	contact = createContactFromRequestParameters(requestParameters);
								response = deleteContact(contact);
								break;
				default: throw new IllegalArgumentException("Action non autorisée.");
			}
			//System.out.println(response);
			return response;
			
		} catch(IllegalArgumentException e) {
			String error = "{\"Result\": \"ERROR\",\"Message\": " + e.getMessage() + "}";
			return error;
		}
	}

	private Contact createContactFromRequestParameters(Map<String, String> requestParameters) {
		Contact contact = new Contact();
		for (Map.Entry<String, String> entry : requestParameters.entrySet()) {
		    String parameter = entry.getKey();
		    String value = entry.getValue();
		    
		    if(parameter == null) { throw new IllegalArgumentException(); }
		    
		    switch(parameter) {
			    case "firstName": 	contact.setFirstName(value); 	break;
			    case "lastName":	contact.setLastName(value);		break;
			    case "birthDate":	contact.setBirthDate(value);	break;
			    case "phoneNumber":	contact.setPhoneNumber(value);
			    case "email":		contact.setEmail(value);
			    case "isActive":	if(value == "true") { contact.setActive(true);}
			    					else {contact.setActive(false);}
			    default: throw new IllegalArgumentException("Unknown parameter: '" +parameter + "'.");
		    }
		}
		return contact;
	}
	
	private String createContact(Contact contact) {
		System.out.println("Contact added: " + contact.toString());
		contactService.createContact(contact);
		String contactJson = gson.toJson(contact);
		String response = "{\"Result\":\"OK\",\"Record\":"+ contactJson +"}";
		return response;
	}
	
	private String updateContact(Contact contact) {
		contactService.updateContact(contact);
		String contactJson = gson.toJson(contact);
		String response = "{\"Result\":\"OK\",\"Record\":"+ contactJson +"}";
		return response;
	}
	
	private String deleteContact(Contact contact) {
		System.out.println("Contact deleted: " + contact.toString());
		contactService.deleteContact(contact);
		String contactJson = gson.toJson(contact);
		String response = "{\"Result\":\"OK\",\"Record\":"+ contactJson +"}";
		return response;
	}

	private String getAllContacts() {
		LinkedList<Contact> allContacts = contactService.getAllContacts();
		String allContactsToJson = gson.toJson(allContacts);
		String response = "{\"Result\":\"OK\",\"Records\":"+ allContactsToJson +"}";
		return response;
	}
} 
