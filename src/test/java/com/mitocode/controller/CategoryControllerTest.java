package com.mitocode.controller;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitocode.dto.CategoryDTO;
import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model.Category;
import com.mitocode.service.ICategoryService;

@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(CategoryController.class)
class CategoryControllerTest {
	
	@Autowired
	private MockMvc mockMvcc;
	
	@MockitoBean
	private ICategoryService categoryService;
	
	@MockitoBean(name = "categoryMapper")
	private ModelMapper mapper;
	
	@Autowired
	private ObjectMapper objectMapper; 
	
	
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
				.andExpect(jsonPath("$[0].idCategory", is(1)));// no trae nada porq no se conecta a la base si no se hace el mapper a dto
		
				
				
	}	

	@Test
	void testReadById() throws Exception {
		final int ID = 1;
		
		Mockito.when(categoryService.readById(any())).thenReturn(CATEGORY_1);
		Mockito.when(mapper.map(CATEGORY_1, CategoryDTO.class)).thenReturn(CATEGORYDTO_1);		
		
		mockMvcc.perform(MockMvcRequestBuilders
				.get("/categories/"+ ID)
				.content(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.nameofCategory", is("TV")));
		        
	}

	@Test
	void testCreate() throws Exception {
		
		Mockito.when(categoryService.save(any())).thenReturn(CATEGORY_3);
		Mockito.when(mapper.map(CATEGORY_3, CategoryDTO.class)).thenReturn(CATEGORYDTO_3);	 
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders 
		.post("/categories")
		.contentType(MediaType.APPLICATION_JSON)
		.content(objectMapper.writeValueAsString(CATEGORYDTO_3));//escribe el string en formato de json, se puede utilizar tambien GSON, O LO DEL MISMO spring me brinda
		
		mockMvcc.perform(builder)
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.nameofCategory", is("BOOKS")))
		.andExpect(jsonPath("$.enabledCategory", is(true)));
	}

	@Test
	void testUpdate() throws Exception {

		final int ID = 2;
		Mockito.when(categoryService.update(any(),any())).thenReturn(CATEGORY_2);
		Mockito.when(mapper.map(CATEGORY_2, CategoryDTO.class)).thenReturn(CATEGORYDTO_2);	 
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders 
		.put("/categories/" + ID)
		.contentType(MediaType.APPLICATION_JSON)
		.content(objectMapper.writeValueAsString(CATEGORYDTO_3));//escribe el string en formato de json, se puede utilizar tambien GSON, O LO DEL MISMO spring me brindas
		
		mockMvcc.perform(builder)
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.nameofCategory", is("PSP")))
		.andExpect(jsonPath("$.enabledCategory", is(true)));
	}
	
	@Test
	void testErrorUpdate() throws Exception {
		
		final int ID = 99;
		Mockito.when(categoryService.update(any(),any())).thenThrow(new ModelNotFoundException("ID NOT FOUND"+ID));
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders 
				.put("/categories/" + ID)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(CATEGORYDTO_3));//escribe el string en formato de json, se puede utilizar tambien GSON, O LO DEL MISMO spring me brindas
		
		mockMvcc.perform(builder)
		.andExpect(status().isNotFound())
		.andExpect(result -> assertTrue(result.getResolvedException() instanceof ModelNotFoundException));
	}

	@Test
	void testDeleteById() throws Exception {
		
		final int ID=1;
		
		mockMvcc.perform(MockMvcRequestBuilders
				.delete("/categories/"+ID)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNoContent());
	}

	@Test
	void testErrorDeleteById() throws Exception {
		
		final int ID=99;
		//Mockito.when(categoryService.delete(any())).thenThrow(new ModelNotFoundException("ID NOT FOUND"+ID));
		Mockito.doThrow(new ModelNotFoundException("ID NOT FOUND"+ID)).when(categoryService).delete(ID);
		mockMvcc.perform(MockMvcRequestBuilders
				.delete("/categories/"+ID)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound())
		.andExpect(result -> assertTrue(result.getResolvedException() instanceof ModelNotFoundException));
	}
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
