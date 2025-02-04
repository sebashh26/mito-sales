package com.mitocode.config;



import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mitocode.dto.CategoryDTO;
import com.mitocode.dto.ProductDTO;
import com.mitocode.model.Category;
import com.mitocode.model.Product;

@Configuration
public class MapperConfig {

	@Bean("categoryMapper")
	public ModelMapper categoryMapper() {
		ModelMapper mapper = new ModelMapper();
		//lectura
		TypeMap<Category, CategoryDTO> typeMap1 = mapper.createTypeMap(Category.class, CategoryDTO.class);
		typeMap1.addMapping(Category::getName, (destination, value) -> destination.setNameofCategory((String) value));
		//escritura
		TypeMap<CategoryDTO, Category> typeMap2 = mapper.createTypeMap(CategoryDTO.class, Category.class);
		typeMap2.addMapping(CategoryDTO::getNameofCategory, (destination, value) -> destination.setName((String) value));
		
		return mapper;
	}
	
	@Bean("productMapper")
	public ModelMapper productMapper() {
		ModelMapper mapper = new ModelMapper();
		
		TypeMap<ProductDTO, Product> typeMap1 = mapper.createTypeMap(ProductDTO.class, Product.class);
		typeMap1.addMapping(ProductDTO::getIdCategoria, (destination, value) -> destination.getCategory().setIdCategory((Integer) value));
		TypeMap<Product, ProductDTO> typeMap2 = mapper.createTypeMap(Product.class, ProductDTO.class);
		typeMap2.addMapping(e -> e.getCategory().getIdCategory(), (destination, value) -> destination.setIdCategoria((Integer) value));
		return mapper;
	}
	
}
