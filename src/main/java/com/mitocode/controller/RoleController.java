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

import com.mitocode.dto.RoleDTO;
import com.mitocode.model.Role;
import com.mitocode.service.IRoleService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/roles") // debe tenerlo para clocar el path
@RequiredArgsConstructor // den ser final la variable para usarla
public class RoleController {

	private final IRoleService roleService;

	@Qualifier("defaultMapper")
	private final ModelMapper mapper;

	@GetMapping
	public ResponseEntity<List<RoleDTO>> readAll() throws Exception {
		List<RoleDTO> list = roleService.readAll().stream().map(this::convertToDto).toList();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<RoleDTO> readById(@PathVariable("id") Integer id) throws Exception {
		Role role = roleService.readById(id);
		return new ResponseEntity<>(this.convertToDto(role), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<RoleDTO> create(@Valid @RequestBody RoleDTO dto) throws Exception {
		Role obj = roleService.save(this.convertToEntity(dto));
		return new ResponseEntity<>(this.convertToDto(obj), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<RoleDTO> update(@Valid @RequestBody RoleDTO dto, @PathVariable("id") Integer id)
			throws Exception {
		Role obj = roleService.update(convertToEntity(dto), id);
		return new ResponseEntity<>(convertToDto(obj), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws Exception {
		roleService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	private RoleDTO convertToDto(Role role) {
		return mapper.map(role, RoleDTO.class);
	}

	private Role convertToEntity(RoleDTO roleDTO) {
		return mapper.map(roleDTO, Role.class);
	}

}
