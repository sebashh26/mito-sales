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
public class CategoryDTO {
	
	
	private Integer idCategory;
	
	@NotNull
	@NotEmpty
	@Size(min = 3, max = 50)
	private String nameofCategory;
	
	@NotNull
	@NotEmpty
	@Size(min = 3, max = 50)
	private String descriptionCategory;
	
	@NotNull
	private boolean enabledCategory;
}
