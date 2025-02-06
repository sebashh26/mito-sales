package com.mitocode.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	private Integer idUser;
	
	@NotNull
	private RoleDTO role;
	
	@NotNull
	@NotEmpty
	@Size(min = 3, max = 50)
	private String  userName;
	
	@NotNull
	@NotEmpty
	@Size(min = 3, max = 60)
	private String password;
	
	@NotNull
	private boolean enabledUser;
}
