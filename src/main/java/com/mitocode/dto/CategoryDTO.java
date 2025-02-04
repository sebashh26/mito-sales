package com.mitocode.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
	
	private Integer idCategory;
	private String nameofCategory;
	private String descriptionCategory;
	private boolean enabledCategory;
}
