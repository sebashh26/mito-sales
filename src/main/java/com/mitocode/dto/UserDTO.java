package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

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
	
	@JsonIncludeProperties(value = {"idRole","nameRole"})//mostar propiedades del obj
	@NotNull
	private RoleDTO role;
	
	@JsonProperty(value = "user_name")//para integraciones
	@NotNull
	@NotEmpty
	@Size(min = 3, max = 50)
	private String  userName;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)//defino que no mas hacer con la varibale lectura o escritura
	//@JsonIgnore
	@NotNull
	@NotEmpty
	@Size(min = 3, max = 60)
	private String password;
	
	@NotNull
	private boolean enabledUser;
}
