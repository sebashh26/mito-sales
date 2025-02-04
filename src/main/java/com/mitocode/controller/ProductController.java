package com.mitocode.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
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

import com.mitocode.dto.ProductDTO;
import com.mitocode.model.Product;
import com.mitocode.service.IProductService;

import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/products") // debe tenerlo para clocar el path
@RequiredArgsConstructor // den ser final la variable para usarla
public class ProductController {

	private final IProductService productService;

	@Qualifier("productMapper")
	private final ModelMapper mapper;

	@GetMapping
	public ResponseEntity<List<ProductDTO>> readAll() throws Exception {
		List<ProductDTO> list = productService.readAll().stream().map(this::convertToDto).toList();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> readById(@PathVariable("id") Integer id) throws Exception {
		Product product = productService.readById(id);
		return new ResponseEntity<>(this.convertToDto(product), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO dto) throws Exception {
		Product obj = productService.save(this.convertToEntity(dto));
		return new ResponseEntity<>(this.convertToDto(obj), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO dto, @PathVariable("id") Integer id)
			throws Exception {
		Product obj = productService.update(convertToEntity(dto), id);
		return new ResponseEntity<>(convertToDto(obj), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws Exception {
		productService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	private ProductDTO convertToDto(Product product) {
		return mapper.map(product, ProductDTO.class);
	}

	private Product convertToEntity(ProductDTO productDTO) {
		return mapper.map(productDTO, Product.class);
	}

}
