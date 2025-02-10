package com.mitocode.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
	
	//PARA ELIMINAR SON MAS ACOTADAS SOLO RECIBEN EL ID
	
	List<Category> findByIdCategoryLessThanEquals(Integer id);

	List<Category> findByIdCategoryGreaterThan(Integer id);
	
	List<Category> findByIdCategoryGreaterThanEquals(Integer id);
	
	List<Category> findByIdCategoryBetween(Integer id1, Integer id2);
	
	List<Category> findByNameOrderByDescription(String name);
	
	List<Category> findByNameOrderByDescriptionAsc(String name);*/
	
	//JPQL JAVA PERSITENCE QUERY LAGUAJE/////////////////////////////
	@Query("FROM  Category c where  c.name = :name and c.description like %:description%")//toma lo q tenga en entity sinno hay value toma el nombre de la clase
	List<Category> getNameAndDescription1(@Param("name") String name,@Param("description") String description);
	
	
	//@Query("FROM  Sale s where  s.client.fistName = :name and s.user.username= :username")
	//toma lo q tenga en entity  @Entity(x) si no hay value toma el nombre de la clase xyz
	@Query("SELECT new com.mitocode.model.Category(c.name, c.enabled) FROM  Category c where  c.name = :name and c.description like %:description%")//toma lo q tenga en entity sinno hay value toma el nombre de la clase
	List<Category> getNameAndDescription2(@Param("name") String name,@Param("description") String description);
	
	//query native: como se lo haría en la base de datos
	
	@Query(value = "select * from category c where c.name= :name", nativeQuery = true)
	List<Category> getNameSQL(@Param("name") String name);
	
	@Modifying//se usa para update, insert delete
	@Query(value = "update category set name= :name", nativeQuery = true)
	Integer updateName (@Param(value = "name") String name);
}
