package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProviderDTO {
	
	private Integer idProvider;
	
	@NotNull
	@NotEmpty
	@Size(min = 2)
	private String nameProvider;
	
	@NotNull
	@NotEmpty
	@Size(min = 3)
	private String addressProvider;
	
	@NotNull
	private boolean enabledProvider;

}
