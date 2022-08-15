package com.qa.vet.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.vet.models.Pet;
import com.qa.vet.service.PetService;

@RestController
@RequestMapping("/pet")
public class PetController {

	private final PetService service;

	@Autowired
	PetController(PetService service) {
		this.service = service;
	}

	// Aggregate root
	// tag::get-aggregate-root[]
	@GetMapping("/all")
	List<Pet> all() {
		return service.readAll();

	}

	@CrossOrigin
	@PostMapping("/add")
	public String newPetForm(@RequestBody Pet pet) {
		Pet newPet = pet;
		service.addPet(newPet);
		return pet.toString();
	}

	@GetMapping("/{id}")
	Pet one(@PathVariable Long id) {

		return service.readPet(id);
//      .orElseThrow(() -> new PetNotFoundException(id));
	}

//	@PutMapping("/pets/{id}")
//	Pet replacePet(@RequestBody Pet newPet, @PathVariable Long id) {
//
//		return service.readPet(id).map(pet -> {
//			pet.setFirstName(newPet.getFirstName());
//			pet.setLastName(newPet.getLastName());
//			pet.setEmail(newPet.getEmail());
//
//			return repository.save(pet);
//		}).orElseGet(() -> {
//			newPet.setId(id);
//			return repository.save(newPet);
//		});
//	}

	@DeleteMapping("/{id}")
	void deletePet(@PathVariable Long id) {
		service.deleteByPetID(id);
	}
}