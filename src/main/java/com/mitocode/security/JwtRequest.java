package com.mitocode.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//ss3
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequest {

	private String userName;
	private String password;
}
