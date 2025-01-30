package com.mitocode.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode//toma los 2 como criterio de comparaciom
public class IngressDetailPK {
	
	@ManyToOne
	@JoinColumn(name="id_ingress", nullable = false, foreignKey = @ForeignKey(name="FK_IDETAIL_PK"))
	private Ingress ingress;
	
	@ManyToOne
	@JoinColumn(name="id_product", nullable = false, foreignKey = @ForeignKey(name="FK_PDETAIL_PK"))
	private Product product;

}
