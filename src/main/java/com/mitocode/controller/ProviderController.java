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

import com.mitocode.dto.ProviderDTO;
import com.mitocode.model.Provider;
import com.mitocode.service.IProviderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/providers") // debe tenerlo para clocar el path
@RequiredArgsConstructor // den ser final la variable para usarla
public class ProviderController {

	private final IProviderService providerService;

	@Qualifier("defaultMapper")
	private final ModelMapper mapper;

	@GetMapping
	public ResponseEntity<List<ProviderDTO>> readAll() throws Exception {
		List<ProviderDTO> list = providerService.readAll().stream().map(this::convertToDto).toList();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProviderDTO> readById(@PathVariable("id") Integer id) throws Exception {
		Provider provider = providerService.readById(id);
		return new ResponseEntity<>(this.convertToDto(provider), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ProviderDTO> create(@Valid @RequestBody ProviderDTO dto) throws Exception {
		Provider obj = providerService.save(this.convertToEntity(dto));
		return new ResponseEntity<>(this.convertToDto(obj), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProviderDTO> update(@Valid @RequestBody ProviderDTO dto, @PathVariable("id") Integer id)
			throws Exception {
		Provider obj = providerService.update(convertToEntity(dto), id);
		return new ResponseEntity<>(convertToDto(obj), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws Exception {
		providerService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	private ProviderDTO convertToDto(Provider provider) {
		return mapper.map(provider, ProviderDTO.class);
	}

	private Provider convertToEntity(ProviderDTO providerDTO) {
		return mapper.map(providerDTO, Provider.class);
	}

}
