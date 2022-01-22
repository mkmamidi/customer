package com.assesment.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.assesment.model.Customer;

@Repository
public class CustomerDAO {
	@Autowired
	private  JdbcTemplate template;

	public String save(Customer customer) {
		try {
		int count = template.update("insert into Customer(CustomerId,CustomerName,Phone,Address,DOB) values (?,?,?,?,?)",
				customer.getCustomerId(),customer.getCustomerName(),customer.getPhone(),customer.getAddress(),customer.getDob());
		if(count>0)
			return "Success";
		else 
			return "Insertion Failed";
		}
		catch(DataIntegrityViolationException e) {
			return "Customer already registered";
		}
		catch(Exception e) {
			return "Insertion Failed";
		}
}

	@SuppressWarnings({ "deprecation" })
	public ResponseEntity getCustomer(String customerId) {
		
		try {
		Customer customer= (Customer) template.queryForObject("select * from Customer where CustomerId=?", new Object[]{customerId},
		        new BeanPropertyRowMapper<Customer>(Customer.class));
		return new ResponseEntity<>(customer,HttpStatus.OK);
		}
		catch(EmptyResultDataAccessException e ) {
			return new ResponseEntity<>("Customer NOT Registered",HttpStatus.NOT_FOUND);
		}
		catch(Exception e ) {
			return new ResponseEntity<>("Unable to perform the operation",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
