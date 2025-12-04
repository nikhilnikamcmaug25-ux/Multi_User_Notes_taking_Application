package com.notes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.notes.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    // derived query was present: findByEmailAndPassword -> keep but avoid using it
    Optional<User> findByEmailAndPassword(String em,String pass);

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email); // new
}
