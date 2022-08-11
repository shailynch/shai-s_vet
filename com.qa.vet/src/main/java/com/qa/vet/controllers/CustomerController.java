package com.qa.vet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.vet.models.Customer;
import com.qa.vet.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	private final CustomerService service;

	@Autowired
	CustomerController(CustomerService service) {
		this.service = service;
	}

	// Aggregate root
	// tag::get-aggregate-root[]
	@GetMapping("/")
	public String trial() {
//	List<Customer> all() {
//		return service.readAll();
		System.out.println("trial");
		return "trial";

	}

	@PostMapping("/customers")
	Customer newCustomer(@RequestBody Customer newCustomer) {
		return service.addCustomer(newCustomer);
	}

	// Single item

	@GetMapping("/customers/{id}")
	Customer one(@PathVariable Long id) {

		return service.readCustomer(id);
//      .orElseThrow(() -> new CustomerNotFoundException(id));
	}

//	@PutMapping("/customers/{id}")
//	Customer replaceCustomer(@RequestBody Customer newCustomer, @PathVariable Long id) {
//
//		return service.readCustomer(id).map(customer -> {
//			customer.setFirstName(newCustomer.getFirstName());
//			customer.setLastName(newCustomer.getLastName());
//			customer.setEmail(newCustomer.getEmail());
//
//			return repository.save(customer);
//		}).orElseGet(() -> {
//			newCustomer.setId(id);
//			return repository.save(newCustomer);
//		});
//	}

	@DeleteMapping("/customers/{id}")
	void deleteCustomer(@PathVariable Long id) {
		service.deleteByCustomerID(id);
	}
}