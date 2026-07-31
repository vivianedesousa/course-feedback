package br.com.coursefeedback.feedback.resource;

import br.com.coursefeedback.feedback.dto.CreateFeedbackRequestDTO;
import br.com.coursefeedback.feedback.dto.FeedbackResponseDTO;
import br.com.coursefeedback.feedback.service.SubmitFeedbackService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/api/v1/feedbacks")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

@Tag(
        name = "Feedbacks",
        description = "Endpoints responsible for submitting and managing course feedback."
)
public class FeedbackResource {
    private final SubmitFeedbackService submitFeedbackService;

    public FeedbackResource(SubmitFeedbackService submitFeedbackService) {
        this.submitFeedbackService = submitFeedbackService;
    }

    @POST
    @Operation(
            summary = "Submit feedback",
            description = "Creates a new feedback for a course and stores it in the database."
    )
    public FeedbackResponseDTO createFeedback(
            @Valid CreateFeedbackRequestDTO requestDTO) {
        return submitFeedbackService.createFeedback(requestDTO);
    }

    @GET
    @Operation(
            summary = "List all feedbacks",
            description = "Returns a list containing all feedbacks submitted by students."
    )
    public List<FeedbackResponseDTO> getAllFeedbacks() {
        return submitFeedbackService.getAllFeedbacks();
    }


    @GET
    @Path("/{feedbackId}")

    @Operation(
            summary = "Get feedback by ID",
            description = "Retrieves the details of a specific feedback using its unique identifier."
    )
    public FeedbackResponseDTO getFeedbackById(
            @PathParam("feedbackId") String feedbackId) {

        return submitFeedbackService.getFeedbackById(feedbackId);
    }
}
