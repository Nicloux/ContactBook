package com.spring.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.spring.model.Address;
import com.spring.model.Contact;
import com.spring.model.ContactService;

public class AddressController {
	
	@Autowired private ContactService contactService;
	
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
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
				case create: 	//contact = createContactFromRequestParameters(requestParameters);
								//response = createContact(contact);
								break;
				case update: 	//contact = createContactFromRequestParameters(requestParameters);
								//response = updateContact(contact);
								break;
				case delete: 	//contact = createContactFromRequestParameters(requestParameters);
								//response = deleteContact(contact);
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
	
	private Contact createAddressFromRequestParameters(Map<String, String> requestParameters) {
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
			    case "isActive":	
			    default: throw new IllegalArgumentException("Unknown parameter: '" + parameter + "'.");
		    }
		}
		return contact;
	}

}
