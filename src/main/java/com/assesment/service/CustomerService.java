package com.assesment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.assesment.model.Customer;
import com.assesment.repository.CustomerDAO;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerDAO repo;
	
	public String save(Customer customer) {
		return repo.save(customer);
		
	}

	public ResponseEntity get(String customerId) {
		return repo.getCustomer(customerId);
	}
	
	

}
