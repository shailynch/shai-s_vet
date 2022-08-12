package com.qa.vet.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	@GetMapping("/all")
	List<Customer> all() {
		return service.readAll();

	}

	@CrossOrigin
	@PostMapping("/add")
	public String newCustomerForm(Customer customer, Model model) {
		model.addAttribute("customer", customer);
		Customer newCustomer = customer;
		service.addCustomer(newCustomer);
		return customer.toString();

	}

//	@PostMapping("/add")
//	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
//		Customer newCustomer = service.addCustomer(customer);
//		return new ResponseEntity<Customer>(newCustomer, HttpStatus.CREATED);
//
//	}

	// Single itemz

	@GetMapping("/{id}")
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

	@DeleteMapping("/{id}")
	void deleteCustomer(@PathVariable Long id) {
		service.deleteByCustomerID(id);
	}
}