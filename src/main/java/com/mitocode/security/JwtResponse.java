package com.mitocode.security;

//ss4
//nos sirve como salida de la respuesta al hacer login
public record JwtResponse(String jwtToken) {
	
}

//todo esto como es inmutable la varibale se puede reemplazar con un recorda
//public class JwtResponse {
//
//	private final String jwtToken;
//
//	public JwtResponse(String jwtToken) {
//		this.jwtToken = jwtToken;
//	}
//	
//	public String getJwtToken() {
//		return jwtToken;
//	}
//	
//	
//}
