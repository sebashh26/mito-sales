package com.mitocode.repo;

import java.util.List;

import com.mitocode.model.Category;

public interface ICategoryRepo extends IGenericRepo<Category, Integer> {
	
	// Category save(Category category);
	
	//Derived querys
	//select * from category c where..
	List<Category> findByName(String name);
	
	//select * from category c where c.name like '%mous%'
	List<Category> findByNameLike(String name);
	
	//'%XYZ%' findByNameContains
	//'%XYZ' findByNameStarsWith
	//'XYZ%' findByNameEndsWith
	List<Category> findByNameOrEnabled(String name, boolean enabled);
	/*List<Category> findByNameAndEnabled(String name, boolean enabled);
	
	
	List<Category> findByEnabled(boolean enabled);
	List<Category> findByEnabledTrue();
	List<Category> findByEnabledFalse();
	
	Category findOneByName(String name);
	
	List<Category> findTop3ByName(String name);

	List<Category> findByNameIs(String name);
	
	List<Category> findByNameIsNot(String name);
	
	List<Category> findByNameIsNull(String name);
	
	List<Category> findByNameIsNotNull(String name);
	
	List<Category> findByNameEqualsIgnoreCase(String name);
	
	List<Category> findByIdCategoryLessThan(Integer id);
	
	List<Category> findByIdCategoryLessThanEquals(Integer id);

	List<Category> findByIdCategoryGreaterThan(Integer id);
	
	List<Category> findByIdCategoryGreaterThanEquals(Integer id);
	
	List<Category> findByIdCategoryBetween(Integer id1, Integer id2);
	
	List<Category> findByNameOrderByDescription(String name);
	
	List<Category> findByNameOrderByDescriptionAsc(String name);*/
}
