package com.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.restapi.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
