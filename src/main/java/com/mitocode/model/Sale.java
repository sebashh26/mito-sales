package com.mitocode.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Sale {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer idSale;
	
	@ManyToOne
	@JoinColumn(name="id_client", nullable = false, foreignKey = @ForeignKey(name="FK_SALE_CLIENT"))
	private Client client;

	@ManyToOne
	@JoinColumn(name="id_user", nullable = false, foreignKey = @ForeignKey(name="FK_SALE_USER"))
	private User user;
	
	@OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)//atributo por el q se hace el match,RELACION DE MAESTRO DETALLE INSERTARA DE GOLPE LA VENTA Y SUS ITEMS
	private List<SaleDetail> details;
	
	@Column(nullable = false)
	private LocalDateTime dateTime;
	
	@Column(columnDefinition = "decimal(7,2)", nullable = false)
	private double total;
	
	@Column(columnDefinition = "decimal(6,2)", nullable = false)
	private double tax;
	
	@Column(nullable = false)
	private boolean enabled;
	
}
