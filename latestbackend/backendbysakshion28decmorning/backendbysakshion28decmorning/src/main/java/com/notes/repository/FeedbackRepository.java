package com.notes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.notes.entities.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

}
