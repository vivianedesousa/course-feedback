package br.com.coursefeedback.feedback.service;

import br.com.coursefeedback.feedback.domain.Urgency;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FeedbackCriticalityService {
    public Urgency calculateUrgency(Integer rating) {

        if (rating <= 1) {
            return Urgency.CRITICAL;
        }

        if (rating <= 4) {
            return Urgency.HIGH;
        }

        if (rating <= 7) {
            return Urgency.MEDIUM;
        }

        return Urgency.LOW;
    }

    public boolean isCritical(Integer rating) {
        return calculateUrgency(rating) == Urgency.CRITICAL;
    }
}
