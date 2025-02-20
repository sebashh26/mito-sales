package com.mitocode.security;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitocode.exception.CustomErrorResponse;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//ss6

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		String exceptionMsg = (String) request.getAttribute("exception");
		
		if(StringUtils.isBlank(exceptionMsg)) {
			exceptionMsg = "Token not found or invalid";
		}
		
		CustomErrorResponse errorResponse = new CustomErrorResponse(LocalDateTime.now(), exceptionMsg, request.getRequestURI());
		
		//cant return new ResponseEntity like before but it method is a void
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		//write pide string erro response es un objeto y ese string tiene formato de json
		response.getWriter().write(convertObjectToJson(errorResponse));
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);

	}
	
	private String convertObjectToJson(Object object) throws JsonProcessingException {
        if(object == null){
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();//azuca sintactico
        return mapper.writeValueAsString(object);//retorna string como si fuera un json, es decir cuando se vea el error va a tener la estructura json
    }
	

}
