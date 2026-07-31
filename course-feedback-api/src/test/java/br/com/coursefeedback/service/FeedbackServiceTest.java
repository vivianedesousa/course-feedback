package br.com.coursefeedback.service;

import br.com.coursefeedback.feedback.domain.Feedback;
import br.com.coursefeedback.feedback.domain.Urgency;
import br.com.coursefeedback.feedback.dto.CreateFeedbackRequestDTO;
import br.com.coursefeedback.feedback.dto.FeedbackResponseDTO;
import br.com.coursefeedback.notification.service.CriticalFeedbackNotificationService;
import br.com.coursefeedback.feedback.repository.FeedbackRepository;
import br.com.coursefeedback.feedback.service.FeedbackCriticalityService;
import br.com.coursefeedback.feedback.service.SubmitFeedbackService;
import br.com.coursefeedback.shared.exception.FeedbackNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class FeedbackServiceTest {
    private FeedbackRepository feedbackRepository;
    private FeedbackCriticalityService feedbackCriticalityService;
    private CriticalFeedbackNotificationService criticalFeedbackNotificationService;
    private SubmitFeedbackService submitFeedbackService;

    @BeforeEach
    void setup() {

        feedbackRepository = Mockito.mock(FeedbackRepository.class);

        feedbackCriticalityService =
                Mockito.mock(FeedbackCriticalityService.class);

        criticalFeedbackNotificationService =
                Mockito.mock(CriticalFeedbackNotificationService.class);

        submitFeedbackService = new SubmitFeedbackService(
                feedbackRepository,
                feedbackCriticalityService,
                criticalFeedbackNotificationService
        );
    }

    @Test
    void createFeedback_success() {

        CreateFeedbackRequestDTO request = new CreateFeedbackRequestDTO();
        request.setUserId("user-1");
        request.setCourseId("course-1");
        request.setDescription("Excellent course");
        request.setRating(9);

        Mockito.when(
                feedbackCriticalityService.calculateUrgency(9)
        ).thenReturn(Urgency.LOW);

        FeedbackResponseDTO response =
                submitFeedbackService.createFeedback(request);

        assertNotNull(response);
        assertEquals("user-test", response.getUserId());
        assertEquals("course-1", response.getCourseId());
        assertEquals("Excellent course", response.getDescription());
        assertEquals(9, response.getRating());
        assertEquals(Urgency.LOW, response.getUrgency());

        Mockito.verify(feedbackRepository)
                .save(Mockito.any(Feedback.class));

        Mockito.verify(feedbackCriticalityService)
                .calculateUrgency(9);
    }

    @Test
    void getFeedbackById_found() {

        Feedback feedback = new Feedback();

        feedback.setFeedbackId("1");
        feedback.setUserId("user-1");
        feedback.setCourseId("course-1");
        feedback.setDescription("Excellent");
        feedback.setRating(10);
        feedback.setUrgency(Urgency.LOW);
        feedback.setCreatedAt("2025-07-25T10:00:00");

        Mockito.when(feedbackRepository.findById("1"))
                .thenReturn(Optional.of(feedback));

        FeedbackResponseDTO response =
                submitFeedbackService.getFeedbackById("1");

        assertNotNull(response);
        assertEquals("1", response.getFeedbackId());
        assertEquals("course-1", response.getCourseId());
        assertEquals("Excellent", response.getDescription());

        Mockito.verify(feedbackRepository)
                .findById("1");
    }


    @Test
    void getFeedbackById_notFound() {

        Mockito.when(feedbackRepository.findById("10"))
                .thenReturn(Optional.empty());

        try {

            submitFeedbackService.getFeedbackById("10");

            fail();

        } catch (FeedbackNotFoundException e) {

            assertEquals(
                    "Feedback was not found.",
                    e.getMessage()
            );
        }

        Mockito.verify(feedbackRepository)
                .findById("10");
    }

    @Test
    void getAllFeedbacks_success() {

        Feedback feedback1 = new Feedback();
        feedback1.setFeedbackId("1");
        feedback1.setDescription("Excellent");

        Feedback feedback2 = new Feedback();
        feedback2.setFeedbackId("2");
        feedback2.setDescription("Good");

        List<Feedback> feedbacks = new ArrayList<>();
        feedbacks.add(feedback1);
        feedbacks.add(feedback2);

        Mockito.when(feedbackRepository.findAll())
                .thenReturn(feedbacks);

        List<FeedbackResponseDTO> response =
                submitFeedbackService.getAllFeedbacks();

        assertEquals(2, response.size());

        assertEquals("1", response.get(0).getFeedbackId());
        assertEquals("Excellent", response.get(0).getDescription());

        assertEquals("2", response.get(1).getFeedbackId());
        assertEquals("Good", response.get(1).getDescription());

        Mockito.verify(feedbackRepository)
                .findAll();
    }

    @Test
    void getAllFeedbacks_empty() {

        Mockito.when(feedbackRepository.findAll())
                .thenReturn(new ArrayList<>());

        List<FeedbackResponseDTO> response =
                submitFeedbackService.getAllFeedbacks();

        assertTrue(response.isEmpty());

        Mockito.verify(feedbackRepository)
                .findAll();
    }

}
