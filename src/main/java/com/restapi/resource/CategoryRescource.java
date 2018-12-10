package com.restapi.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.event.ResourceCreateEvent;
import com.restapi.model.Category;
import com.restapi.repository.CategoryRepository;

@RestController
@RequestMapping("/category")
public class CategoryRescource {
	
	@Autowired
	private CategoryRepository categoriaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Category> findAll(){
		return this.categoriaRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Category> creatCategory(@Valid @RequestBody Category categoria, HttpServletResponse response) {
		Category categorySaved = this.categoriaRepository.save(categoria);		
		this.publisher.publishEvent(new ResourceCreateEvent(this, response, categorySaved.getCode()));
		return ResponseEntity.status(HttpStatus.CREATED).body(categorySaved);
	}	
		
	@GetMapping("/{code}")
	public ResponseEntity<Category> findById(@PathVariable Long code) {
		Optional<Category> category = this.categoriaRepository.findById(code);		
		return category.isPresent() ? ResponseEntity.ok(category.get()) : ResponseEntity.notFound().build() ;
	}
}

