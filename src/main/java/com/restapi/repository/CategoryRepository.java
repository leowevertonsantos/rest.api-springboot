package com.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.restapi.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
