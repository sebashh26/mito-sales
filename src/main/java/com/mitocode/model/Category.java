package com.mitocode.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Table(name = "lgb_categtory")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@SequenceGenerator(name="secuena de la base de datos")
	private Integer idCategory;
	
	//@Column(name = "name_category" , nullable = false,length = 50)
	@Column(nullable = false,length = 50)
	private String name;
	
	@Column(nullable = false,length = 50)
	private String description;
	
	@Column(nullable = false)
	private boolean enabled;

}
