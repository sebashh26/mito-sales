package com.mitocode.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mitocode.model.Category;

public interface ICategoryRepo extends JpaRepository<Category, Integer> {
	
	// Category save(Category category);
}
