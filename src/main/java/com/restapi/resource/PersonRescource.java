package com.restapi.resource;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.event.ResourceCreateEvent;
import com.restapi.model.Person;
import com.restapi.repository.PersonRepository;

@RestController
@RequestMapping("/person")
public class PersonRescource {
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@PostMapping
	public ResponseEntity<Person> creatCategory(@Valid @RequestBody Person person, HttpServletResponse response) {
		Person personCreated = this.personRepository.save(person);
		this.publisher.publishEvent(new ResourceCreateEvent(this, response, personCreated.getCode()));
		return ResponseEntity.status(HttpStatus.CREATED).body(personCreated);
	}	
		 
	@GetMapping("/{code}")
	public ResponseEntity<Person> getPersonByCode(@PathVariable Long code) {
		Optional<Person> person = this.personRepository.findById(code);
		return person.isPresent() ? ResponseEntity.ok(person.get()) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("{code}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePerson(@PathVariable Long code) {
		this.personRepository.deleteById(code);
	}
}

