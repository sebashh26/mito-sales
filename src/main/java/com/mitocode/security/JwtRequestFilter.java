package com.mitocode.security;

import java.io.IOException;

import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

//ss5
@Profile(value = {"dev", "qa", "prod"})
@Component
@RequiredArgsConstructor//onceper.. significa q se ejecuta en cada peticion http
public class JwtRequestFilter extends OncePerRequestFilter{
	
	private final JwtUserDetailService jwtUserDetailService;
	private final JwtTokenUtil jwtTokenUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String tokenHeader = request.getHeader("Authorization");
		
		String userName= null;
		
		String jwtToken= null;
		
		if (tokenHeader != null) {
			if (tokenHeader.startsWith("Bearer") || tokenHeader.startsWith("bearer ")) {

				final int TOLKEN_POSITTION = 7;
				jwtToken = tokenHeader.substring(TOLKEN_POSITTION);

				try {
					userName = jwtTokenUtil.getUserNameFromToken(jwtToken);

				} catch (Exception e) {
					request.setAttribute("exception", e.getMessage());
				}

			}
		}
		if (userName!= null) {
			UserDetails userDetails = jwtUserDetailService.loadUserByUsername(userName);
			// el siguiente es importante para saber q usuario esta logeuado en ese momento y lo pueda sacar de memoria
			if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
				//si deseo obtener reporte de ventas no seria idoneo colocar el usuario en el query alguin pouede poner otro usuario, mejoe sacar el token del usaurio logeado en ese momento
				//y ponerlo en el query
				//lo siguiente pone en memori de spring security la data del usuario, para no exponer la clave ponen null
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));//azuca sintactico
				
				//a partir del user name genera una instancia para que lo gestiones en el SecurityContextHolder y eso se lo recupera mas adelante 
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				
			}
		}
		//no indica q continue con el request y response
		filterChain.doFilter(request, response);
	}
	
}
