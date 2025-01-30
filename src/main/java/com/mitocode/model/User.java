package com.mitocode.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "user_data")
public class User {

	@Id
	@EqualsAndHashCode.Include
	private Integer idUser;
	
	@ManyToOne
	@JoinColumn(name = "idRole", nullable = false, foreignKey = @ForeignKey(name="FK_USER_ROLE"))
	private Role role;
	
	@Column(length = 50, nullable = false, unique = true)
	private String userName;
	
	@Column(length = 60, nullable = false)
	private String password;
	
	@Column(nullable = false)
	private boolean enabled;
}
