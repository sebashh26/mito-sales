package com.mitocode.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model.Category;
import com.mitocode.repo.ICategoryRepo;

@ExtendWith(SpringExtension.class)// para test in services
@SpringBootTest
class CategoryServiceImplTest {

	@MockitoBean
	private CategoryServiceImpl serviceImpl;
	
	@MockitoBean
	private ICategoryRepo repo;
	
	Category CATEGORY_1 = new Category(1, "TV", "Television", true);
    Category CATEGORY_2 = new Category(2, "PSP", "Play Station Portable", true);
    Category CATEGORY_3 = new Category(3, "BOOKS", "Some books", true);
    
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		this.serviceImpl = new CategoryServiceImpl(repo);
	    
	    List<Category> categories = List.of(CATEGORY_1,CATEGORY_2,CATEGORY_3);
	    Mockito.when(repo.findAll()).thenReturn(categories);
	    Mockito.when(repo.findById(any())).thenReturn(Optional.of(CATEGORY_1));
	    Mockito.when(repo.save(any())).thenReturn(CATEGORY_1);
	    
	}
	
	@Test
	void testReadAll() throws Exception {
		List<Category> list= serviceImpl.readAll();
		
		assertNotNull(list);
		assertFalse(list.isEmpty());
		assertEquals(list.size(), 3);
	}
	
	@Test
	void testReadById() throws Exception{
		
		final int ID=1;
		Category category = serviceImpl.readById(ID);
		assertNotNull(category);
	}

	@Test
	void save() throws Exception {
		Category category = serviceImpl.save(CATEGORY_1);
		assertNotNull(category);
	}
	
	@Test
	void update() throws Exception {
		
		Category category = serviceImpl.update(CATEGORY_1,anyInt());
		assertNotNull(category);
	}
	
	@Test
	void delete() throws Exception {
		final int ID=2;
		serviceImpl.delete(ID);
		Mockito.verify(repo, times(1)).deleteById(ID);
		//verify(null)
	}
	
	@Test
	void testExceptioDelete() throws Exception {
		final int ID=1;		 
		Mockito.doThrow(new ModelNotFoundException("ID NOT FOUND"+CATEGORY_1.getIdCategory())).when(repo).deleteById(any());
		assertThrows(ModelNotFoundException.class, () -> serviceImpl.delete(ID));
	}
}
