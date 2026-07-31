package br.com.coursefeedback.resource;
import br.com.coursefeedback.feedback.dto.CreateFeedbackRequestDTO;
import br.com.coursefeedback.feedback.dto.FeedbackResponseDTO;
import br.com.coursefeedback.feedback.resource.FeedbackResource;
import br.com.coursefeedback.feedback.service.SubmitFeedbackService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FeedbackResourceTest {

    private SubmitFeedbackService submitFeedbackService;

    private FeedbackResource feedbackResource;

    @BeforeEach
    void setup() {

        submitFeedbackService =
                Mockito.mock(SubmitFeedbackService.class);

        feedbackResource =
                new FeedbackResource(submitFeedbackService);
    }

    @Test
    void createFeedback_success() {

        CreateFeedbackRequestDTO request =
                new CreateFeedbackRequestDTO();

        FeedbackResponseDTO response =
                new FeedbackResponseDTO();

        Mockito.when(submitFeedbackService.createFeedback(request))
                .thenReturn(response);

        FeedbackResponseDTO result =
                feedbackResource.createFeedback(request);

        assertNotNull(result);

        Mockito.verify(submitFeedbackService)
                .createFeedback(request);
    }

    @Test
    void getAllFeedbacks_success() {

        FeedbackResponseDTO response =
                new FeedbackResponseDTO();

        List<FeedbackResponseDTO> feedbacks =
                List.of(response);

        Mockito.when(submitFeedbackService.getAllFeedbacks())
                .thenReturn(feedbacks);

        List<FeedbackResponseDTO> result =
                feedbackResource.getAllFeedbacks();

        assertEquals(1, result.size());

        Mockito.verify(submitFeedbackService)
                .getAllFeedbacks();
    }

    @Test
    void getFeedbackById_success() {

        FeedbackResponseDTO response =
                new FeedbackResponseDTO();

        Mockito.when(submitFeedbackService.getFeedbackById("feedback-1"))
                .thenReturn(response);

        FeedbackResponseDTO result =
                feedbackResource.getFeedbackById("feedback-1");

        assertNotNull(result);

        Mockito.verify(submitFeedbackService)
                .getFeedbackById("feedback-1");
    }
}
