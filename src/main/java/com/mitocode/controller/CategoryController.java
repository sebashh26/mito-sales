package com.mitocode.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitocode.model.Category;
import com.mitocode.service.ICategoryService;

import lombok.RequiredArgsConstructor;
@RestController//todo controlador debe tenerlo asi se define un servicio rest, no es necesirio esteriotiparle
@RequestMapping("/categories")//debe tenerlo para clocar el path
//@AllArgsConstructor
@RequiredArgsConstructor// den ser final la variable para usarla
public class CategoryController {
	
	//@Autowired
	private final ICategoryService categoryService;
	
	
	@GetMapping
	public ResponseEntity<List<Category>> readAll() throws Exception{		
		List<Category>list = categoryService.readAll();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> readById(@PathVariable("id") Integer id) throws Exception{
		Category category =  categoryService.readById(id);
		return new ResponseEntity<>(category, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Category> create(@RequestBody Category category) throws Exception{
		Category obj = categoryService.save(category);
		return new ResponseEntity<>(obj, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Category> update(@RequestBody Category category, @PathVariable("id") Integer id) throws Exception{
		Category obj = categoryService.update(category, id);
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws Exception{
		categoryService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	/*@GetMapping
	public Category saveCategory() {
		//categoryService = new CategoryService();
		return categoryService.validAndSave(new Category(1,"TV","television", true));
	}*/

}
