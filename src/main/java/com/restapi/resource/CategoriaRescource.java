package com.restapi.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.restapi.model.Categoria;
import com.restapi.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
public class CategoriaRescource {
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public List<Categoria> findAll(){
		return this.categoriaRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Categoria> creatCategory( @RequestBody Categoria categoria, HttpServletResponse response) {
		Categoria categorySaved = this.categoriaRepository.save(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(categorySaved.getCodigo()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		return ResponseEntity.created(uri).body(categorySaved);
	}
	
	
	@GetMapping("/{codigo}")
	public Categoria findById(@PathVariable Long codigo) {
		return this.categoriaRepository.findById(codigo).get();		
	}
}

