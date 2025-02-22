package com.mitocode.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;
//s7
@Configuration// si pongo @configuration muy problablemente dentro tengo @beans
@EnableWebSecurity
@EnableMethodSecurity//no sirve para proteger metodo por metodo diferencialfo por el role, con esto funciona las anotaciones preauthorize
@RequiredArgsConstructor
public class WebSecurityConfig {

	private final JwtAuthenticationEntryPoint authenticationEntryPoint;
	private final UserDetailsService jwtUserDetailService;
	private final JwtRequestFilter jwtRequestFilter;
	
	@Bean//intancia vacia donde se le proporciona donde estan los user y pass
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
	
	@Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
	
	@Autowired//puedo tener una injeccion de dependencia sobre los parametros de un metodo
	//en pocas pide una instancia compatible con AuthenticationManagerBuilder
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		//passwordEncoder se le pasa este porque es como va a decodificar las claves
        auth.userDetailsService(jwtUserDetailService).passwordEncoder(passwordEncoder());
    }
	
	@Bean	//controlamos con esta instancia que ruta protege o q libera
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        //Desde Spring Boot 3.1, csrf cross site request forgery o falsificacion de de peticion de sitios cruzados
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable) //csrf -> csrf.disable()
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/rest/**").permitAll()
                        //.requestMatchers("/categories/**").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults()) //httpBasic()
                .exceptionHandling(e -> e.authenticationEntryPoint(authenticationEntryPoint))
                .formLogin(AbstractHttpConfigurer::disable) //e -> e.disable() deshabilita el form inicial q se pone
                .sessionManagement(Customizer.withDefaults());// no se gestina la vista , solo se da rq y devuelce rs

        //Lista de cambios deprecated -> https://docs.spring.io/spring-security/site/docs/current/api/deprecated-list.html

        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
