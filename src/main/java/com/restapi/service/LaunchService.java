package com.restapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.model.Launch;
import com.restapi.model.Person;
import com.restapi.repository.LaunchRepository;
import com.restapi.repository.PersonRepository;
import com.restapi.service.Exception.PersonNotExistOrInativeException;

@Service
public class LaunchService {
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private LaunchRepository launchRepository;
	
	public Launch saveLaunch(Launch launch) {
		Optional<Person> person = this.personRepository.findById(launch.getPerson().getCode());
		if(!person.isPresent() || !person.get().isAtivo()) {
			throw new PersonNotExistOrInativeException();
		}
		return this.launchRepository.save(launch);		
	}
}
