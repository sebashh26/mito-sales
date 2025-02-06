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

import com.mitocode.dto.UserDTO;
import com.mitocode.model.User;
import com.mitocode.service.IUserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/users") // debe tenerlo para clocar el path
@RequiredArgsConstructor // den ser final la variable para usarla
public class UserController {

	private final IUserService userService;

	@Qualifier("defaultMapper")
	private final ModelMapper mapper;

	@GetMapping
	public ResponseEntity<List<UserDTO>> readAll() throws Exception {
		List<UserDTO> list = userService.readAll().stream().map(this::convertToDto).toList();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> readById(@PathVariable("id") Integer id) throws Exception {
		User user = userService.readById(id);
		return new ResponseEntity<>(this.convertToDto(user), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO dto) throws Exception {
		User obj = userService.save(this.convertToEntity(dto));
		return new ResponseEntity<>(this.convertToDto(obj), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> update(@Valid @RequestBody UserDTO dto, @PathVariable("id") Integer id)
			throws Exception {
		User obj = userService.update(convertToEntity(dto), id);
		return new ResponseEntity<>(convertToDto(obj), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws Exception {
		userService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	private UserDTO convertToDto(User user) {
		return mapper.map(user, UserDTO.class);
	}

	private User convertToEntity(UserDTO userDTO) {
		return mapper.map(userDTO, User.class);
	}

}
