package com.restapi.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.event.ResourceCreateEvent;
import com.restapi.model.Launch;
import com.restapi.repository.LaunchRepository;
import com.restapi.service.LaunchService;

@RestController
@RequestMapping("/launch")
public class LaunchResource {

	@Autowired
	private LaunchRepository launchRepository;
	
	@Autowired
	private LaunchService LaunchService; 
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	
	@GetMapping("/{code}")
	public ResponseEntity<Launch> findLaunchByCode(@PathVariable Long code) {
		Optional<Launch> launch = launchRepository.findById(code);
		return launch.isPresent() ? ResponseEntity.ok(launch.get()) : ResponseEntity.notFound().build();
	}
	
	@GetMapping
	public List<Launch> findAllLaunch(){
		return this.launchRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Launch> createLaunch(@Valid @RequestBody Launch launch, HttpServletResponse response) {
		
		Launch launchSaved = this.LaunchService.saveLaunch(launch);
		this.publisher.publishEvent(new ResourceCreateEvent(this, response, launchSaved.getCode()));
		return ResponseEntity.status(HttpStatus.CREATED).body(launch);
	}
}
