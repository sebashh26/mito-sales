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

import com.mitocode.dto.ClientDTO;
import com.mitocode.model.Client;
import com.mitocode.service.IClientService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/clients") // debe tenerlo para clocar el path
@RequiredArgsConstructor // den ser final la variable para usarla
public class ClientController {

	private final IClientService clientproductService;

	@Qualifier("defaultMapper")
	private final ModelMapper mapper;

	@GetMapping
	public ResponseEntity<List<ClientDTO>> readAll() throws Exception {
		List<ClientDTO> list = clientproductService.readAll().stream().map(this::convertToDto).toList();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClientDTO> readById(@PathVariable("id") Integer id) throws Exception {
		Client product = clientproductService.readById(id);
		return new ResponseEntity<>(this.convertToDto(product), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ClientDTO> create(@Valid @RequestBody ClientDTO dto) throws Exception {
		Client obj = clientproductService.save(this.convertToEntity(dto));
		return new ResponseEntity<>(this.convertToDto(obj), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ClientDTO> update(@Valid @RequestBody ClientDTO dto, @PathVariable("id") Integer id)
			throws Exception {
		Client obj = clientproductService.update(convertToEntity(dto), id);
		return new ResponseEntity<>(convertToDto(obj), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws Exception {
		clientproductService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	private ClientDTO convertToDto(Client product) {
		return mapper.map(product, ClientDTO.class);
	}

	private Client convertToEntity(ClientDTO productDTO) {
		return mapper.map(productDTO, Client.class);
	}

}
