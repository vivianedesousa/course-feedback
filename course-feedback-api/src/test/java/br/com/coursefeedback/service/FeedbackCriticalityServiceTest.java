package br.com.coursefeedback.service;

import static org.junit.jupiter.api.Assertions.*;

import br.com.coursefeedback.feedback.domain.Urgency;
import br.com.coursefeedback.feedback.service.FeedbackCriticalityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FeedbackCriticalityServiceTest {
    private FeedbackCriticalityService feedbackCriticalityService;

    @BeforeEach
    void setup() {
        feedbackCriticalityService = new FeedbackCriticalityService();
    }


    @Test
    void calculateUrgency_rating0_shouldReturnCritical() {
        Urgency urgency = feedbackCriticalityService.calculateUrgency(0);
        assertEquals(Urgency.CRITICAL, urgency);
    }


    @Test
    void calculateUrgency_rating1_shouldReturnCritical() {

        Urgency urgency = feedbackCriticalityService.calculateUrgency(1);

        assertEquals(Urgency.CRITICAL, urgency);
    }

    @Test
    void calculateUrgency_rating2_shouldReturnHigh() {

        Urgency urgency = feedbackCriticalityService.calculateUrgency(2);

        assertEquals(Urgency.HIGH, urgency);
    }

    @Test
    void calculateUrgency_rating4_shouldReturnHigh() {

        Urgency urgency = feedbackCriticalityService.calculateUrgency(4);

        assertEquals(Urgency.HIGH, urgency);
    }

    @Test
    void calculateUrgency_rating5_shouldReturnMedium() {

        Urgency urgency = feedbackCriticalityService.calculateUrgency(5);

        assertEquals(Urgency.MEDIUM, urgency);
    }

    @Test
    void calculateUrgency_rating7_shouldReturnMedium() {

        Urgency urgency = feedbackCriticalityService.calculateUrgency(7);

        assertEquals(Urgency.MEDIUM, urgency);
    }

    @Test
    void calculateUrgency_rating8_shouldReturnLow() {

        Urgency urgency = feedbackCriticalityService.calculateUrgency(8);

        assertEquals(Urgency.LOW, urgency);
    }

    @Test
    void calculateUrgency_rating10_shouldReturnLow() {

        Urgency urgency = feedbackCriticalityService.calculateUrgency(10);

        assertEquals(Urgency.LOW, urgency);
    }

    @Test
    void isCritical_rating0_shouldReturnTrue() {

        boolean critical = feedbackCriticalityService.isCritical(0);

        assertTrue(critical);
    }


    @Test
    void isCritical_rating8_shouldReturnFalse() {

        boolean critical = feedbackCriticalityService.isCritical(8);

        assertFalse(critical);
    }
}