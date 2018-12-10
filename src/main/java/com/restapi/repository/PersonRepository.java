package com.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restapi.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
