package com.notes.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.notes.dto.FeedbackDTO;
import com.notes.entities.Feedback;
import com.notes.repository.FeedbackRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final ModelMapper modelMapper;

    @Override
    public Feedback addFeedback(FeedbackDTO dto) {
        Feedback feedback = modelMapper.map(dto, Feedback.class);
        return feedbackRepository.save(feedback);
    }

    @Override
    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }
}
