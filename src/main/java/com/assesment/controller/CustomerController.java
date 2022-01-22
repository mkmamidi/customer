package com.assesment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.assesment.model.Customer;
import com.assesment.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService service;

	@PostMapping("/registration")  
	private String saveStudent(@RequestBody Customer customer)   
	{  
	
		return service.save(customer);
	
	}
	@GetMapping("/getCustomer/{customerId}")
	public ResponseEntity findCity(@PathVariable String customerId) {
        
        return service.get(customerId);
    }
}
