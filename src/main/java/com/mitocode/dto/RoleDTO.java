package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleDTO {

	private Integer idRole;
	
	@NotNull
	@NotEmpty
	private String nameRole;
	
	@NotNull
	private String enabledRole;
	
}
