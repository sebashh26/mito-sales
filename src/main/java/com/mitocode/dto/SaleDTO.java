package com.mitocode.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaleDTO {

	private Integer idSale;
	
	@NotNull
	private ClientDTO client;
	
	@NotNull
	private UserDTO user;
	
	@NotNull
	private LocalDateTime dateTime;
	
	@Min(value = 1)
	private double total;
	
	@Min(value = 1)
	private double tax;
	
	@NotNull
	private boolean enabled;
	
	@NotNull
	@JsonManagedReference
	private List<SaleDetailDTO> details;

}
