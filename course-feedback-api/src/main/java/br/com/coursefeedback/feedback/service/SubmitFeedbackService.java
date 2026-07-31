package br.com.coursefeedback.feedback.service;

import br.com.coursefeedback.feedback.domain.Feedback;
import br.com.coursefeedback.notification.service.CriticalFeedbackNotificationService;
import br.com.coursefeedback.feedback.dto.CreateFeedbackRequestDTO;
import br.com.coursefeedback.feedback.dto.FeedbackResponseDTO;
import br.com.coursefeedback.feedback.repository.FeedbackRepository;
import br.com.coursefeedback.shared.exception.FeedbackNotFoundException;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class SubmitFeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final FeedbackCriticalityService feedbackCriticalityService;
    private final CriticalFeedbackNotificationService criticalFeedbackNotificationService;

    public SubmitFeedbackService(
            FeedbackRepository feedbackRepository,
            FeedbackCriticalityService feedbackCriticalityService,
            CriticalFeedbackNotificationService criticalFeedbackNotificationService) {

        this.feedbackRepository = feedbackRepository;
        this.feedbackCriticalityService = feedbackCriticalityService;
        this.criticalFeedbackNotificationService = criticalFeedbackNotificationService;
    }


    public FeedbackResponseDTO createFeedback(CreateFeedbackRequestDTO requestDTO) {
        Feedback feedback = createFeedbackEntity(requestDTO);
        feedbackRepository.save(feedback);
        if (feedbackCriticalityService.isCritical(feedback.getRating())) {
            criticalFeedbackNotificationService.publish(feedback);
        }
        return toFeedbackResponse(feedback);
    }

    private Feedback createFeedbackEntity(CreateFeedbackRequestDTO requestDTO) {
        Feedback feedback = new Feedback();
        feedback.setFeedbackId(UUID.randomUUID().toString());
        feedback.setUserId(requestDTO.getUserId());
        feedback.setCourseId(requestDTO.getCourseId());
        feedback.setDescription(requestDTO.getDescription());
        feedback.setRating(requestDTO.getRating());
        feedback.setUrgency(
                feedbackCriticalityService.calculateUrgency(
                        requestDTO.getRating()
                )
        );
        feedback.setCreatedAt(
                LocalDateTime.now()
                        .withNano(0)
                        .toString()
        );
        return feedback;
    }


    public List<FeedbackResponseDTO> getAllFeedbacks() {

        List<Feedback> feedbacks = feedbackRepository.findAll();

        List<FeedbackResponseDTO> responseDTOs = new ArrayList<>();

        for (Feedback feedback : feedbacks) {
            responseDTOs.add(toFeedbackResponse(feedback));
        }
        return responseDTOs;
    }


    public FeedbackResponseDTO getFeedbackById(String feedbackId) {

        Optional<Feedback> feedbackOptional =
                feedbackRepository.findById(feedbackId);

        if (feedbackOptional.isEmpty()) {
            throw new FeedbackNotFoundException("Feedback was not found.");
        }

        Feedback feedback = feedbackOptional.get();

        return toFeedbackResponse(feedback);
    }


    private FeedbackResponseDTO toFeedbackResponse(Feedback feedback) {

        return new FeedbackResponseDTO(
                feedback.getFeedbackId(),
                feedback.getUserId(),
                feedback.getCourseId(),
                feedback.getDescription(),
                feedback.getRating(),
                feedback.getUrgency(),
                feedback.getCreatedAt()
        );

    }
}
