package com.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;

import com.spring.model.Contact;

public class ContactWebArgumentResolver implements WebArgumentResolver {

	@Override
	 public Object resolveArgument(MethodParameter methodParameter, NativeWebRequest webRequest) throws Exception {
		if (Contact.class.equals(methodParameter.getParameterType())) {
			
            ServletWebRequest servletWebRequest = (ServletWebRequest) webRequest;
            HttpServletRequest request = servletWebRequest.getRequest();
			
			String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String birthDate = request.getParameter("birthDate");
            String phoneNumber = request.getParameter("phoneNumber");
            String isActive = request.getParameter("isActive");
            
            Contact contact = new Contact();
            contact.setFirstName(firstName);
            contact.setLastName(lastName);
            contact.setBirthDate(birthDate);
            contact.setPhoneNumber(phoneNumber);
            contact.setActive(isActive);
            System.out.println("Resolver: " + contact.toString());
            return new Contact();
        }

        return WebArgumentResolver.UNRESOLVED;
    }
}