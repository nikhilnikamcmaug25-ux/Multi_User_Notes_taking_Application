package com.notes.entities;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name="users")
@AttributeOverride(name="id", column= @Column(name="user_id"))

@Getter
@Setter
@ToString
@NoArgsConstructor
public class User extends BaseEntity{
	
	@Column(name = "first_name", length = 30) // col name , varchar size
	private String firstName;
	@Column(name = "last_name", length = 40)
	private String lastName;
	@Column(length = 50, unique = true) // col : unique constraint
	private String email;
	// not null constraint , size=300 (for hashed password)
	@Column(length = 300, nullable = false)
	private String password;
	@Column(unique = true, length = 14)

	private String phone;
	@Enumerated(EnumType.STRING)
	private UserRole role;
	
	
	public User(String firstName, String lastName, String email, String password, String phone, UserRole role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.role = role;
	}
	
	
}
