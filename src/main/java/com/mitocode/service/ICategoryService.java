package com.mitocode.service;

import java.util.List;

import com.mitocode.model.Category;

public interface ICategoryService {

	Category save(Category category) throws Exception;
	
	Category update(Category category, Integer id) throws Exception;
	
	List<Category> readAll() throws Exception;
	
	Category readById(Integer id) throws Exception;
	
	void delete(Integer id) throws Exception;
	//Category validAndSave(Category category);
}
