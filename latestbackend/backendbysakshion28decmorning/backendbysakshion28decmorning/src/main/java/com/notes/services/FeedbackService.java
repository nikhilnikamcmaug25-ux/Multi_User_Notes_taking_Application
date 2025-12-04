package com.notes.services;

import java.util.List;
import com.notes.dto.FeedbackDTO;
import com.notes.entities.Feedback;

public interface FeedbackService {
    Feedback addFeedback(FeedbackDTO dto);
    List<Feedback> getAllFeedback();
}
