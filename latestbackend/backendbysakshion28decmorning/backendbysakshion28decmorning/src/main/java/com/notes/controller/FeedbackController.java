package com.notes.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notes.dto.FeedbackDTO;
import com.notes.entities.Feedback;
import com.notes.services.FeedbackService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/feedback")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    // POST endpoint: submit feedback
    @PostMapping("/submit")
    public ResponseEntity<?> submitFeedback(@RequestBody FeedbackDTO dto) {
   
            Feedback feedback = feedbackService.addFeedback(dto);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(feedback);
        
    }

    // GET endpoint: fetch all feedbacks
    @GetMapping("/all")
    public ResponseEntity<?> getAllFeedback() {
   
            List<Feedback> feedbackList = feedbackService.getAllFeedback();
            return ResponseEntity.ok(feedbackList);
        
    }
}
