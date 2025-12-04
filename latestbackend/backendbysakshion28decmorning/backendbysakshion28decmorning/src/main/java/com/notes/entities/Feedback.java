package com.notes.entities;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "feedback")
@AttributeOverride(name = "id", column = @Column(name = "feedback_id"))
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Feedback extends BaseEntity {

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 500, nullable = false)
    private String message;

    public Feedback(String name, String email, String message) {
        this.name = name;
        this.email = email;
        this.message = message;
    }
}
