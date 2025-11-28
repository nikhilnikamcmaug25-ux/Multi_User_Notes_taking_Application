package com.notes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.notes.entites.Notes;

public interface NoteRepository extends JpaRepository<Notes, Long> {

    List<Notes> findByUserUserId(Long userId);
}
