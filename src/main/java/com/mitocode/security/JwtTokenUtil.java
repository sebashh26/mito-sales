package com.mitocode.security;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
//class s1
@Component
public class JwtTokenUtil implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	//milisegundos
	public final long JWT_TOKEN_VALIDITY = 5*60*60*1000;//5 HORAS
	
	@Value("${jwt.secret}")// el expression laguaje, se lo defien en los properties application
	private String secret;
	
	//payload
	public String generateToken(UserDetails userDetails){
		
		Map<String, Object> claims= new HashMap<>();
		claims.put("role", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining()));//e -> e.getAuthority()
		
		return doGenerateToken(claims, userDetails.getUsername());
	}

	private String doGenerateToken(Map<String, Object> claims, String username) {
		
		return Jwts.builder()
				.claims(claims)//payload
				.subject(username)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY))
				.signWith(getSignInKey(), Jwts.SIG.HS512)
				.compact();
	}
	
	
	
	private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
	
	//validations
	
	public Claims getAllClaimsFromToken(String token) {
		return Jwts.parser()
				.verifyWith(getSignInKey())
		        .build()
		        .parseSignedClaims(token).getPayload();
	}
	
	//The <T> in the method signature implies that the method will be dealing with generic type T. 
	//This is needed even if the method is returning void.
	//funtion receive anything an go back anything
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver ) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	
	public String getUserNameFromToken(String token){
		return getClaimFromToken(token, Claims::getSubject);
	} 
	
	public Date getExpirationDateFromToken(String token){
		return getClaimFromToken(token, Claims::getExpiration);
	} 
	
	private boolean isTokenExpired(String token){
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
    //UserDetails:interfaz por cual spring sabe quien esta logeado en ese momento
    public boolean validateToken(String token, UserDetails userDetails){
        final String username = getUserNameFromToken(token);
        return (username.equalsIgnoreCase(userDetails.getUsername()) && !isTokenExpired(token));
    }
	
}
