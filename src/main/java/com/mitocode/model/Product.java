package com.mitocode.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer idProduct;
	
	@ManyToOne //FK /many to one pensar en FK, muchos productos le pueden pertener a una categoria
	//@JoinColumn(name = "idCategory", nullable = false, foreignKey = @ForeignKey(name = "FK_PRODUCT_CATEGORY"))
    @JoinColumn(name = "id_category", nullable = false, foreignKey = @ForeignKey(name = "FK_PRODUCT_CATEGORY"))
    private Category category;
	
	@Column(length = 50, nullable = false)
	private String name;
	
	@Column(length = 150, nullable = false)
	private String description;
	
	@Column(columnDefinition = "decimal(6,2)", nullable = false)
	private double price;
	
	@Column(nullable = false)
	private boolean enabled;

}
