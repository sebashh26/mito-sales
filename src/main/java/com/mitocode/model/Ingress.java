package com.mitocode.model;

import java.time.LocalDateTime;

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
public class Ingress {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer idIngress;
	
	@ManyToOne
	@JoinColumn(name="id_provider", nullable = false, foreignKey = @ForeignKey(name="FK_INGRESS_PROVIDER"))
	private Provider provider;
	
	@ManyToOne
	@JoinColumn(name="id_user", nullable = false, foreignKey = @ForeignKey(name="FK_INGRESS_USER"))
	private User user;
	
	@Column(nullable = false)
	private LocalDateTime dateTime;
	
	@Column(columnDefinition = "decimal(6,2)", nullable = false)
	private double total;
	
	@Column(columnDefinition = "decimal(6,2)", nullable = false)
	private double tax;
	
	@Column(nullable = false)
	private boolean enabled;
	
}
