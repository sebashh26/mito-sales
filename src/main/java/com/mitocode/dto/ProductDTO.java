package com.mitocode.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
	
	private Integer idProduct;
	
	@NotNull
	@Size(min = 1)
	private Integer idCategory;//cliente manda integer, la libreria debe ver como genera una instancia, la liberria valida el nombre y que sean iguales los name de los id genera la instancia y genera la inseccion cuando mando a guardae
	
	@NotNull
	@NotEmpty
	//@Size(min = 3, max = 50)
	private String nameProduct;
	
	@NotNull
	@NotEmpty
	//@Size(min = 3, max = 50)
	private String descriptionProduct;
	
	@Min(value = 1)
	private double priceProduct;
	
	@NotNull
	private boolean enabledProduct;
	
}
