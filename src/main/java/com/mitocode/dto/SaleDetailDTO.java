package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleDetailDTO {

	//@JsonIgnore
	@JsonBackReference
	@ToString.Exclude
	private SaleDTO sale;
	
	@NotNull
	private ProductDTO product;
	
	@Min(value = 1)
	private short quantity;
	
	@Min(value = 1)
	private double salePrice;
	
	@Min(value = 0)
	private double discount;
	
}
