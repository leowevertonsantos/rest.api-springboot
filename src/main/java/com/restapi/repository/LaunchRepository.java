package com.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restapi.model.Launch;

@Repository
public interface LaunchRepository extends JpaRepository<Launch, Long>{

}
