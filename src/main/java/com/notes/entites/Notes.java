package com.notes.entites;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@AttributeOverride(name="id" ,column=@Column(name="notes_id"))
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Notes extends BaseEntity{	
	@Column(length = 50)
	private String title;
	@Column(length = 200)
	private String content;
@ManyToOne
@JoinColumn(name= "user_id",nullable = false)

private User user;
public Notes(String title, String content) {
	super();
	this.title = title;
	this.content = content;
}

}
