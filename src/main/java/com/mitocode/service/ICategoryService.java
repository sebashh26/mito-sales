package com.mitocode.service;

import java.util.List;

import com.mitocode.model.Category;

public interface ICategoryService extends ICRUD<Category, Integer>{

	/*Category save(Category category) throws Exception;

	Category update(Category category, Integer id) throws Exception;

	List<Category> readAll() throws Exception;

	Category readById(Integer id) throws Exception;

	void delete(Integer id) throws Exception;*/
	
	List<Category> findByName(String name);
	
	List<Category> findByNameLike(String name);
	
	List<Category> findByNameOrEnabled(String name, boolean enabled);
	
	List<Category> getNameAndDescription(String name, String description);
	
	List<Category> getNameSQL(String name);
}
