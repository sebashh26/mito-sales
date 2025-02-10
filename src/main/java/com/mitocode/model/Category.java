package com.mitocode.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
//@Table(name = "lgb_categtory")
public class Category {
	
	public Category(String name, boolean enabled) {
		super();
		this.name = name;
		this.enabled = enabled;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@SequenceGenerator(name="secuena de la base de datos")
	@EqualsAndHashCode.Include
	private Integer idCategory;
	
	//@Column(name = "name_category" , nullable = false,length = 50)
	@Column(nullable = false,length = 50)
	private String name;
	
	@Column(nullable = false,length = 50)
	private String description;
	
	@Column(nullable = false)
	private boolean enabled;

}
