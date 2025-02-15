package com.mitocode.controller;


import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.mitocode.dto.CategoryDTO;
import com.mitocode.model.Category;
import com.mitocode.service.ICategoryService;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {
	
	@Autowired
	private MockMvc mockMvcc;
	
	@MockitoBean
	private ICategoryService categoryService;
	
	@MockitoBean(name = "categoryMapper")
	private ModelMapper mapper;
	
	Category CATEGORY_1 = new Category(1, "TV", "Television", true);
    Category CATEGORY_2 = new Category(2, "PSP", "Play Station Portable", true);
    Category CATEGORY_3 = new Category(3, "BOOKS", "Some books", true);

    CategoryDTO CATEGORYDTO_1 = new CategoryDTO(1, "TV", "Television", true);
    CategoryDTO CATEGORYDTO_2 = new CategoryDTO(2, "PSP", "Play Station", true);
    CategoryDTO CATEGORYDTO_3 = new CategoryDTO(3, "BOOKS", "Some books", true);

	@Test
	void testReadAll() throws Exception {
		
		List<Category> list = List.of(CATEGORY_1,CATEGORY_2,CATEGORY_3);
		
		Mockito.when(categoryService.readAll()).thenReturn(list);
		Mockito.when(mapper.map(CATEGORY_1, CategoryDTO.class)).thenReturn(CATEGORYDTO_1);
		Mockito.when(mapper.map(CATEGORY_2, CategoryDTO.class)).thenReturn(CATEGORYDTO_2);
		Mockito.when(mapper.map(CATEGORY_3, CategoryDTO.class)).thenReturn(CATEGORYDTO_3);
		
		mockMvcc.perform(MockMvcRequestBuilders
				.get("/categories")
				.content(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(3)))
				.andExpect(jsonPath("$[1].nameofCategory", is("PSP")))//[0] el contenido es un arreglo se accede por su posicion
				.andExpect(jsonPath("$[0].idCategory", is(1)));// no trae nada porq no se conecta a la base
		
				
				
	}	

//	@Test
//	void testReadById() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testCreate() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testUpdate() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testDeleteById() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindByName() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindByNameLike() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindByNameOrEabled() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetNameAndDescription() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetNameSql() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindPagePageable() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindPageIntInt() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindAllOrder() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testCategoryController() {
//		fail("Not yet implemented");
//	}

}
