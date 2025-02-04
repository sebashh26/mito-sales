package com.mitocode.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
	
	private Integer idProduct;
	private Integer idCategory;//cliente manda integer, la libreria debe ver como genera una instancia, la liberria valida el nombre y que sean iguales los name de los id genera la instancia y genera la inseccion cuando mando a guardae
	private String nameProduct;
	private String descriptionProduct;
	private double priceProduct;
	private boolean enabledProduct;
	
}
