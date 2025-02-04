package com.mitocode.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {

	private Integer idClient;
	@NotNull
	@NotEmpty
	//@NotBlank
	@Size(min = 3, max = 10)
	private String firstName;
	
	@NotNull
	@NotEmpty
	@Size(min = 3, max = 10)
	private String lastName;
	
	@NotNull
	@NotEmpty
	@Size(min = 3, max = 10)
	private String country;
	
	@NotNull
	@NotEmpty
	@Size(min = 10, max = 10)
	private String cardId;
	
	@NotNull
	@NotEmpty
	@Pattern(regexp = "[0-9]+")
	private String phoneNumber;
	
	@NotNull
	@NotEmpty
	@Email
	private String email;
	
	@NotNull
	@NotEmpty
	@Size(min = 3, max = 10)
	private String address;
	
	/*@Max(value = 99)
	@Min(value = 1)
	private int age;*/
	
}
