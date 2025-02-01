 package com.mitocode.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
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

import com.mitocode.dto.CategoryDTO;
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
	
	private final ModelMapper mapper;
	
	@GetMapping
	public ResponseEntity<List<CategoryDTO>> readAll() throws Exception{	
		
		/*List<CategoryDTO> list = categoryService.readAll().stream().map(e -> {
			CategoryDTO dto = new CategoryDTO();
			dto.setIdCategory(e.getIdCategory());
			dto.setNameCategory(e.getName());
			dto.setDescriptionCategory(e.getDescription());
			dto.setEnabledCategory(e.isEnabled());
			return dto;
		}).toList();*/
		
	//List<CategoryRecord> list = categoryService.readAll().stream().map(e-> new CategoryRecord(e.getIdCategory(), e.getName(), e.getDescription(), e.isEnabled())).toList();// use with record
	List<CategoryDTO> list = categoryService.readAll().stream().map(e-> mapper.map(e, CategoryDTO.class)).toList();//withoutrecord, model mapper not support records
	
	
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDTO> readById(@PathVariable("id") Integer id) throws Exception{
		Category category =  categoryService.readById(id);
		CategoryDTO dto = mapper.map(category, CategoryDTO.class);
		return new ResponseEntity<>(dto, HttpStatus.OK);
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
