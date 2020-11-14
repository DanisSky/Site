package ru.itis.services;

import ru.itis.dto.FeedbackForm;
import ru.itis.models.Feedback;
import ru.itis.repositories.FeedbackRepository;

public class FeedbackServiceImpl implements FeedbackService {

    private FeedbackRepository feedbackRepository;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public void sendFeedback(FeedbackForm feedbackForm) {
        Feedback feedback = Feedback.builder()
                .email(feedbackForm.getEmail())
                .name(feedbackForm.getName())
                .text(feedbackForm.getText())
                .build();
        feedbackRepository.save(feedback);
    }
}
