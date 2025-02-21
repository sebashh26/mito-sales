package com.mitocode.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mitocode.dto.CategoryDTO;
import com.mitocode.model.Category;
import com.mitocode.service.ICategoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController // todo controlador debe tenerlo asi se define un servicio rest, no es necesirio
				// esteriotiparle
@RequestMapping("/categories") // debe tenerlo para clocar el path
//@AllArgsConstructor
@RequiredArgsConstructor // den ser final la variable para usarla
public class CategoryController {

	// @Autowired
	private final ICategoryService categoryService;

	@Qualifier("categoryMapper")
	private final ModelMapper mapper;

	//@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	@PreAuthorize("@authServiceImpl.hasAccess('/readAll')")
	@GetMapping
	public ResponseEntity<List<CategoryDTO>> readAll() throws Exception {

		/*
		 * List<CategoryDTO> list = categoryService.readAll().stream().map(e -> {
		 * CategoryDTO dto = new CategoryDTO(); dto.setIdCategory(e.getIdCategory());
		 * dto.setNameCategory(e.getName());
		 * dto.setDescriptionCategory(e.getDescription());
		 * dto.setEnabledCategory(e.isEnabled()); return dto; }).toList();
		 */

		// List<CategoryRecord> list = categoryService.readAll().stream().map(e-> new
		// CategoryRecord(e.getIdCategory(), e.getName(), e.getDescription(),
		// e.isEnabled())).toList();// use with record
		//List<CategoryDTO> list = categoryService.readAll().stream().map(e -> this.convertToDto(e)).toList();// withoutrecord,	modelmapper not support records
		List<CategoryDTO> list = categoryService.readAll().stream().map(this::convertToDto).toList();

		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryDTO> readById(@PathVariable("id") Integer id) throws Exception {
		Category category = categoryService.readById(id);
		return new ResponseEntity<>(this.convertToDto(category), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<CategoryDTO> create(@Valid @RequestBody CategoryDTO dto) throws Exception {
		Category obj = categoryService.save(this.convertToEntity(dto));
		return new ResponseEntity<>(this.convertToDto(obj), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CategoryDTO> update(@Valid @RequestBody CategoryDTO dto, @PathVariable("id") Integer id)
			throws Exception {
		//dto.setIdCategory(id);//asi o con java refletion en el crud
		Category obj = categoryService.update(convertToEntity(dto), id);
		return new ResponseEntity<>(convertToDto(obj), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws Exception {
		categoryService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	////////////////////////queries//////////////////
	@GetMapping("/find/name/{name}")
	public ResponseEntity<List<CategoryDTO>> findByName(@PathVariable("name") String name){
		List<CategoryDTO> list = categoryService.findByName(name).stream().map(this::convertToDto).toList();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping("/find/name/like/{name}")
	public ResponseEntity<List<CategoryDTO>> findByNameLike(@PathVariable("name") String name){
		List<CategoryDTO> list = categoryService.findByNameLike(name).stream().map(this::convertToDto).toList();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping("/find/name/{name}/{enabled}")
	public ResponseEntity<List<CategoryDTO>> findByNameOrEabled(@PathVariable("name") String name, @PathVariable("enabled") boolean enabled){
		List<CategoryDTO> list = categoryService.findByNameOrEnabled(name,enabled).stream().map(this::convertToDto).toList();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping("/get/name/description")
	public ResponseEntity<List<CategoryDTO>> getNameAndDescription(@RequestParam("name") String name, @RequestParam("description") String description){
		List<CategoryDTO> list = categoryService.getNameAndDescription(name, description).stream().map(this::convertToDto).toList();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping("/get/name/sql/{name}")
	public ResponseEntity<List<CategoryDTO>> getNameSql(@PathVariable("name") String name){
		List<CategoryDTO> list = categoryService.findByName(name).stream().map(this::convertToDto).toList();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	
	@GetMapping("/pagination")
	public ResponseEntity<Page<CategoryDTO>> findPage(Pageable pageable){
		Page<CategoryDTO>  pageCategoryDTO = categoryService.findPage(pageable).map(this::convertToDto);
		return new ResponseEntity<>(pageCategoryDTO,HttpStatus.OK);
	}
	
	@GetMapping("/pagination2")
	public ResponseEntity<Page<CategoryDTO>> findPage(@RequestParam(name = "p",defaultValue = "0") int page, @RequestParam(name = "s",defaultValue = "3") int size){
		PageRequest pageRequest=  PageRequest.of(page, size);
		Page<CategoryDTO>  pageCategoryDTO = categoryService.findPage(pageRequest).map(this::convertToDto);
		return new ResponseEntity<>(pageCategoryDTO,HttpStatus.OK);
	}
	
	@GetMapping("/order")
	public ResponseEntity<List<CategoryDTO>> findAllOrder(String param){
		List<CategoryDTO> list = categoryService.findAllOrder(param).stream().map(this::convertToDto).toList();
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	private CategoryDTO convertToDto(Category category) {
		return mapper.map(category, CategoryDTO.class);
	}

	private Category convertToEntity(CategoryDTO categoryDTO) {
		return mapper.map(categoryDTO, Category.class);
	}

	/*
	 * @GetMapping public Category saveCategory() { //categoryService = new
	 * CategoryService(); return categoryService.validAndSave(new
	 * Category(1,"TV","television", true)); }
	 */

}
