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
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class SaleDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer idSaleDetail;
	
	
	@ManyToOne
	@JoinColumn(name="id_sale", nullable = false, foreignKey = @ForeignKey(name="FK_DETAIL_SALE"))
	@ToString.Exclude//for can grouping
	private Sale sale;
	
	@ManyToOne
	@JoinColumn(name="id_product", nullable = false, foreignKey = @ForeignKey(name="FK_DETAIL_PRODUCT"))
	private Product product;
	
	@Column(nullable = false)
	private short quantity;
	
	@Column(columnDefinition = "decimal(6,2)", nullable = false)
	private double salePrice;
	
	@Column(columnDefinition = "decimal(6,2)", nullable = false)
	private double discount;
}
