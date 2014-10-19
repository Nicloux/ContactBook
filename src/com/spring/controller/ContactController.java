package com.spring.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.spring.model.Contact;
import com.spring.model.ContactService;

@RestController
@RequestMapping(value = "/Controller")
public class ContactController {
	
	@Autowired private ContactService contactService;
	
	Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	private enum Action {list, create, update, delete;}
	private enum ContactField {id, firstName, lastName, birthDate, phoneNumber, email, isActive;}
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private Pattern pattern;
	private Matcher matcher;
	
	
	
	@RequestMapping(value = "/", method = { RequestMethod.GET, RequestMethod.POST })
    protected String catchAllOthers() {
        System.out.println("Default mapping");
        String error = "{\"Result\": \"ERROR\",\"Message\": There is nothing here.\"}";
		return error;
    }
		
	/**
	 * @param action: action to execute: list, create, update, delete.
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
			
			System.out.println(firstName + ", " + lastName + ", " + birthDate + ", " + phoneNumber + ", " + email + ", " + isActive);
			
			
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
			return response;
			
		} catch(IllegalArgumentException e) {
			String error = "{\"Result\": \"ERROR\",\"Message\": " + e.getMessage() + "}";
			return error;
		} catch(IllegalStateException e) {
			String error = "{\"Result\": \"ERROR\",\"Message\": " + e.getMessage() + "}";
			return error;
		}
	}

	private Contact createContactFromRequestParameters(Map<String, String> requestParameters) {
		
		Contact contact = new Contact();
		for (Map.Entry<String, String> entry : requestParameters.entrySet()) {
		    String parameter = entry.getKey();
		    String value = entry.getValue();
		    
		    if(parameter.isEmpty()) { throw new IllegalStateException("Missing Parameter '" + parameter + "'");}
		    
		    switch(parameter) {
			    case "firstName": 	isEmptyParameter(parameter, value);
			    					contact.setFirstName(value);
			    					break;
			    					
			    case "lastName":	isEmptyParameter(parameter, value);
									contact.setLastName(value);
									break;
									
			    case "birthDate":	SimpleDateFormat dateFormater = new SimpleDateFormat("dd/MM/yyyy");
									try {
										dateFormater.parse(value);
										contact.setBirthDate(value);
									} 
									catch (ParseException e) {
										throw new IllegalArgumentException("Invalid date format.");
									}	
			    					break;
			    					
			    case "phoneNumber":	contact.setPhoneNumber(value);	break;
			    
			    case "email":		if(emailValidator(value) == true) {contact.setEmail(value);}
			    					else {throw new IllegalArgumentException("Invalid email format.");}	
			    					break;
			    
			    case "isActive":	isEmptyParameter(parameter, value);
			    					if(value.equals("true")) {contact.setActive(value);}
			    					else if(value.equals("false")){contact.setActive(value);}
			    					else {throw new IllegalArgumentException("Incorrect value in contact parameter (" + parameter + ":" + value + ").");}
			    					break;
			    default: throw new IllegalArgumentException("Unknown parameter: '" + parameter + "'.");
		    }
		}
		return contact;
	}
	
	private boolean emailValidator(String email) {
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	private void isEmptyParameter(String parameter, String value) {
		if(value.isEmpty()) {
			throw new IllegalArgumentException("Empty parameter '" + parameter + "'");
		}
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
