package ru.itis.services;

import ru.itis.dto.FeedbackForm;

public interface FeedbackService {
    void sendFeedback(FeedbackForm feedbackForm);
}
