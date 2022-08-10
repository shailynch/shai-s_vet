package com.qa.vet.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.vet.persistence.dao.CustomerDAO;
import com.qa.vet.persistence.domain.Customer;
import com.qa.vet.utils.Utils;

@RestController
@RequestMapping("/") // anything after this url can be found in controller
public class CustomerController implements CrudController<Customer> {

	public String home() {
		return "Hello, World!";
	} // no additional url so default return

	@GetMapping("/test")
	public String test() {
		return "Test, World!";
	}// url/test will produce this

	public static final Logger LOGGER = LogManager.getLogger();
	private CustomerDAO customerDAO;
	private Utils utils;

	public CustomerController(CustomerDAO customerDAO, Utils utils) {
		super();
		this.customerDAO = customerDAO;
		this.utils = utils;
	}

	/**
	 * Reads all customers to the logger
	 */
	@Override
	public List<Customer> readAll() {
		List<Customer> customers = customerDAO.readAll();
		for (Customer customer : customers) {
			LOGGER.info(customer);
		}
		return customers;
	}

	/**
	 * Creates a customer by taking in user input
	 */
	@Override
	public Customer create() {
		LOGGER.info("Please enter a first name");
		String firstName = utils.getString();
		LOGGER.info("Please enter a last name");
		String lastName = utils.getString();
		LOGGER.info("Please enter an email");
		String email = utils.getString();
		Customer customer = customerDAO.create(new Customer(firstName, lastName, email));
		LOGGER.info("Customer created");
		return customer;
	}

	/**
	 * Updates an existing customer by taking in user input
	 */
	@Override
	public Customer update() {
		LOGGER.info("Please enter the id of the customer you would like to update");
		Long id = utils.getLong();
		LOGGER.info("Please enter a first name");
		String firstName = utils.getString();
		LOGGER.info("Please enter a last name");
		String lastName = utils.getString();
		LOGGER.info("Please enter an email");
		String email = utils.getString();
		Customer customer = customerDAO.update(new Customer(id, firstName, lastName, email));
		LOGGER.info("Customer Updated");
		return customer;
	}

	/**
	 * Deletes an existing customer by the id of the customer
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the customer you would like to delete");
		Long id = utils.getLong();
		return customerDAO.delete(id);
	}

}
