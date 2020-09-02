package io.github.anantharajuc.sbtest.web.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.anantharajuc.sbtest.backend.persistence.domain.backend.Person;
import io.github.anantharajuc.sbtest.backend.service.PersonServiceImpl;

@RestController
@RequestMapping("/management/api/v1")
public class PersonManagementController 
{
	@Autowired
	private PersonServiceImpl personServiceImpl;
	
	@GetMapping(value="/person")	
	@PreAuthorize("hasAnyRole('ADMIN','ADMINTRAINEE')")
	public List<Person> getAllPersons() 
	{		
		return personServiceImpl.getAllPersons(); 
	}
	
	@GetMapping(value="/person/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','ADMINTRAINEE')")
	public Person getPersonById(@PathVariable(value="id") Long personId)
	{		
		return personServiceImpl.getPersonById(personId);
	}
	
	@GetMapping(value="/person/pageable")
	@PreAuthorize("hasAnyRole('ADMIN','ADMINTRAINEE')")
	public Page<Person> getAllPersons(Pageable pageable) 
	{		
		return personServiceImpl.getAllPersonsPageable(pageable);
	}
	
	@PostMapping("/person")
	@PreAuthorize("hasAuthority('person:update')")
	public Person createPerson(@Valid @RequestBody Person person)
	{		
		return personServiceImpl.createPerson(person);
	}
	
	@PutMapping("/person/{id}")
	@PreAuthorize("hasAuthority('person:update')")
	public Person updatePerson(@PathVariable(value="id") Long personId,@Valid @RequestBody Person personDetails)
	{		
		return personServiceImpl.updatePerson(personId, personDetails);
	}
	
	@DeleteMapping("/person/{id}")
	@PreAuthorize("hasAuthority('person:delete')")
	public ResponseEntity<?> deletePerson(@PathVariable(value="id") Long personId) 
	{		
		return personServiceImpl.deletePerson(personId);
	}
}
