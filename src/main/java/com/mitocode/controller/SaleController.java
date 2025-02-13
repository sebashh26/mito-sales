package com.mitocode.controller;

import java.util.List;
import java.util.Map;

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

import com.mitocode.dto.ProcedureDTO;
import com.mitocode.dto.SaleDTO;
import com.mitocode.model.Sale;
import com.mitocode.service.IProcedureDTO;
import com.mitocode.service.ISaleService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/sales") // debe tenerlo para clocar el path
@RequiredArgsConstructor // den ser final la variable para usarla
public class SaleController {

	private final ISaleService saleService;

	@Qualifier("defaultMapper")
	private final ModelMapper mapper;

	@GetMapping
	public ResponseEntity<List<SaleDTO>> readAll() throws Exception {
		List<SaleDTO> list = saleService.readAll().stream().map(this::convertToDto).toList();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SaleDTO> readById(@PathVariable("id") Integer id) throws Exception {
		Sale sale = saleService.readById(id);
		return new ResponseEntity<>(this.convertToDto(sale), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<SaleDTO> create(@Valid @RequestBody SaleDTO dto) throws Exception {
		Sale obj = saleService.save(this.convertToEntity(dto));
		return new ResponseEntity<>(this.convertToDto(obj), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<SaleDTO> update(@Valid @RequestBody SaleDTO dto, @PathVariable("id") Integer id)
			throws Exception {
		Sale obj = saleService.update(convertToEntity(dto), id);
		
		return new ResponseEntity<>(convertToDto(obj), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws Exception {
		saleService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/resume")
	public ResponseEntity<List<ProcedureDTO>> getSaleResume() throws Exception {
		return new ResponseEntity<>(saleService.callProcedure1(), HttpStatus.OK);
	}
	
	@GetMapping("/resume2")
	public ResponseEntity<List<IProcedureDTO>> getSaleResume2() throws Exception {
		return new ResponseEntity<>(saleService.callProcedure2(), HttpStatus.OK);
	}
	
	@GetMapping("/resume3")
	public ResponseEntity<List<ProcedureDTO>> getSaleResume3() throws Exception {
		return new ResponseEntity<>(saleService.callProcedure3(), HttpStatus.OK);
	}
	
	@GetMapping("/resume4")
	public ResponseEntity<Void> getSaleResume4() throws Exception {
		saleService.callProcedure4();
		return new ResponseEntity<>( HttpStatus.OK);
	}
	
	@GetMapping("/mostexpensive")
	public ResponseEntity<SaleDTO> getMostExpemsive() throws Exception {
		SaleDTO dto = this.convertToDto(saleService.getSaleMostExpensive());
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@GetMapping("/bestsellerperson")
	public ResponseEntity<String> getBestSellerPerson() throws Exception {
		String string= saleService.getBestSellerPerson();
		return new ResponseEntity<>(string, HttpStatus.OK);
	}
	
	@GetMapping("/sellercount")
	public ResponseEntity<Map<String, Long>> getBestSellerCount() throws Exception {
		Map<String, Long> map= saleService.getSalesCountBySeller();
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@GetMapping("/bestsellerproduct")
	public ResponseEntity<Map<String, Double>> getBestSellerProduct() throws Exception {
		Map<String, Double> map= saleService.getMostSellerProduct();
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	private SaleDTO convertToDto(Sale sale) {
		return mapper.map(sale, SaleDTO.class);
	}

	private Sale convertToEntity(SaleDTO saleDTO) {
		return mapper.map(saleDTO, Sale.class);
	}

}
