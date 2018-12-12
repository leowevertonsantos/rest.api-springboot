package com.restapi.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.restapi.model.Person;
import com.restapi.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository;	
	
	public Person updatePerson(Long code, Optional<Person> person) {
		Optional<Person> personSaved = this.personRepository.findById(code);
		
		if(!personSaved.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(person.get(), personSaved.get(), "code");
		this.personRepository.save(personSaved.get());
		return personSaved.get();
	}

	public void updateStatusPerson(Long code, Boolean status) {
		Optional<Person> person = this.personRepository.findById(code);
		if(!person.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		person.get().setStatus(status);
		this.personRepository.save(person.get());
	}
}
