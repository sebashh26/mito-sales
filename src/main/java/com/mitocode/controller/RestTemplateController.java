package com.mitocode.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.header.Header;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitocode.dto.CategoryDTO;
import com.mitocode.security.JwtRequest;
import com.mitocode.security.JwtResponse;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class RestTemplateController {

	private final RestTemplate restTemplate;
	
	@GetMapping("/dragonball/{name}")
	public ResponseEntity<?> getCharacterDragonBall(@PathVariable("name") String name) {
		
		String dragonballUrl= "https://dragonball-api.com/api/characters/"+ name;
		String response = restTemplate.getForEntity(dragonballUrl, String.class).getBody();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/test1")
	public ResponseEntity<List<CategoryDTO>> test1() {
		
		String categoriesUrl= "http://localhost:9797/categories";
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(List.of(MediaType.APPLICATION_JSON));
		
		HttpEntity<String> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<CategoryDTO>> typeReference = new ParameterizedTypeReference<>() {
		};
		
		return restTemplate.exchange(categoriesUrl, HttpMethod.GET, entity, typeReference );
	}
	
	@GetMapping("/test2")
    public ResponseEntity<?> test2(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        String url = "http://localhost:9797/categories/pagination2?p=" + page + "&s=" + size;

        ResponseEntity<?> response = restTemplate.getForEntity(url, String.class);

        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }
	
	@GetMapping("/test3")//lo mismo q el test2 pero con mejor practicas
    public ResponseEntity<?> test3(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        String url = "http://localhost:9797/categories/pagination2?p={page}&s={size}";

        Map<String, Integer> uriVariables = new HashMap<>();
        uriVariables.put("page", page);
        uriVariables.put("size", size);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> requestEntity = new HttpEntity(headers);//esto me da el cuerpo de la info q necesito

        return restTemplate.exchange(url, HttpMethod.GET, requestEntity, Map.class, uriVariables);
    }
	
	 @PostMapping("/test4")
	    public ResponseEntity<CategoryDTO> test4(@RequestBody CategoryDTO categoryDTO) {
	        String url = "http://localhost:9797/categories";

	        HttpEntity<CategoryDTO> request = new HttpEntity<>(categoryDTO);
	        CategoryDTO dto = restTemplate.postForObject(url, request, CategoryDTO.class);

	        return new ResponseEntity<>(dto, HttpStatus.CREATED);
	  }

	 @PutMapping("/test5/{id}")
	    public ResponseEntity<CategoryDTO> test5(@RequestBody CategoryDTO categoryDTO, @PathVariable("id") Integer id){
	        String url = "http://localhost:9797/categories/" + id;

	        HttpEntity<CategoryDTO> request = new HttpEntity<>(categoryDTO);

	        return restTemplate.exchange(url, HttpMethod.PUT, request, CategoryDTO.class);
	   }
	 
	 @DeleteMapping("/test6/{id}")
	    public ResponseEntity<Void> test6(@PathVariable("id") Integer id){
	        String url = "http://localhost:9797/categories/" + id;
	        restTemplate.delete(url);

	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	   }
	 
	 @DeleteMapping("/test7/{id}")//lo mismo de arriba pero con el id dinámico
	    public ResponseEntity<Void> test7(@PathVariable("id") Integer id) {
	        String url = "http://localhost:9797/categories/{idCategory}";

	        Map<String, Integer> uriVariables = new HashMap<>();
	        uriVariables.put("idCategory", id);

	        restTemplate.delete(url, uriVariables);

	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	 
		@DeleteMapping("/test8/{id}")
		public ResponseEntity<Void> test8(@PathVariable("id") Integer id) throws Exception {
			// Generando el token
			final String AUTHETICATION_URL = "http://localhost:9797/login";

			JwtRequest jwtRequest = new JwtRequest("mito", "123");

			// poner el request tipo string formato a json
			String userRequestJson = new ObjectMapper().writeValueAsString(jwtRequest);

			HttpHeaders headersAuth = new HttpHeaders();
			headersAuth.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
			headersAuth.set("Accept", MediaType.APPLICATION_JSON_VALUE);

			HttpEntity<String> httpEntity = new HttpEntity<String>(userRequestJson, headersAuth);
			ResponseEntity<JwtResponse> responseEntity = restTemplate.exchange(AUTHETICATION_URL, HttpMethod.POST,
					httpEntity, JwtResponse.class);

			String jwtToken = responseEntity.getBody().jwtToken();

			// Enviando el token para pedir datos o hacer acciones
			String tokenAuth = "Bearer " + jwtToken;
			HttpHeaders headers = new HttpHeaders();
			headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
			headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
			headers.set("Authorization", tokenAuth);

			HttpEntity<String> jwtEntity = new HttpEntity<>(headers);

			String url = "http://localhost:9797/categories/{idCategory}";

			Map<String, Integer> uriVariables = new HashMap<>();
			uriVariables.put("idCategory", id);

			restTemplate.exchange(url, HttpMethod.DELETE, jwtEntity, String.class, uriVariables);

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		}
}
