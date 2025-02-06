package com.mitocode.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {

	private Integer idRole;
	
	@NotNull
	@NotEmpty
	private String nameRole;
	
	@NotNull
	private String enabledRole;
	
}
