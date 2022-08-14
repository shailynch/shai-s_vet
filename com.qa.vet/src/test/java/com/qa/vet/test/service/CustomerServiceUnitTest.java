package com.qa.vet.test.service;

//import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.vet.repo.CustomerRepo;
import com.qa.vet.service.CustomerService;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
class CustomerServiceUnitTest {

	@MockBean
	private CustomerRepo repo;

//	@MockBean
//	private ModelMapper mapper;

	@Autowired
	CustomerService service;

}
