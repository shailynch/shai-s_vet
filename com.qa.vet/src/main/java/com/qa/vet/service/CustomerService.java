package com.qa.vet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.qa.vet.models.Customer;
import com.qa.vet.repo.CustomerRepo;

//import com.qa.customerapi.models.Customer;
//import com.qa.customerapi.repo.CustomerRepo;

@Component
public class CustomerService {
	private Customer customer;
	private CustomerRepo repo;

	public CustomerService() {
		customer = new Customer();
	}

	// create
	public Customer addCustomer(Customer newCustomer) {
		return repo.save(newCustomer);
	}

	// read
	public Customer readCustomer(Long id) {
		return repo.findById(id).get();
		// .get returns null or the customer as the customer would be optional
		// type check would be better
	}

	public List<Customer> readAll() {
		return repo.allFromCustomer();
		// .get returns null or the customer as the customer would be optional
		// type check would be better
	}

	// update - change to current customer and new customer
	public Customer updateCustomer(Customer updateCustomer, Long id) {
		Optional<Customer> currentCustomer = this.repo.findById(id);
		if (currentCustomer.get() instanceof Customer) {
			Customer oldCustomer = currentCustomer.get();
			oldCustomer.setFirstName(updateCustomer.getFirstName());
			oldCustomer.setLastName(updateCustomer.getLastName());
			oldCustomer.setEmail(updateCustomer.getEmail());

			return repo.save(oldCustomer);
		}
		return null;

	}

	// delete
	public boolean deleteByCustomerID(Long id) {
		Optional<Customer> currentCustomer = this.repo.findById(id);
		boolean isPresent = (currentCustomer.isPresent()) ? true : false;
		if (isPresent) {
			this.repo.deleteById(id);
			return true;
		} else {
			return false;
		}
	}
}
