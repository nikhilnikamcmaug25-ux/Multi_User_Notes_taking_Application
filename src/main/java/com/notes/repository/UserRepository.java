package com.notes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.notes.entites.User;

public interface UserRepository extends JpaRepository<User, Long> {
	//derived query : select u from User where u.email=:em and u.password=:pass;
	Optional<User> findByEmailAndPassword(String em,String pass);
}
