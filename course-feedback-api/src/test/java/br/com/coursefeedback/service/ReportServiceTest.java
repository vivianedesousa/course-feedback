package br.com.coursefeedback.service;

import br.com.coursefeedback.course.domain.Course;
import br.com.coursefeedback.course.repository.CourseRepository;
import br.com.coursefeedback.feedback.domain.Feedback;
import br.com.coursefeedback.feedback.domain.Urgency;
import br.com.coursefeedback.feedback.repository.FeedbackRepository;
import br.com.coursefeedback.report.domain.FeedbackReport;
import br.com.coursefeedback.report.dto.GenerateReportRequestDTO;
import br.com.coursefeedback.report.dto.ReportResponseDTO;
import br.com.coursefeedback.report.repository.FeedbackReportRepository;
import br.com.coursefeedback.report.service.ReportService;
import br.com.coursefeedback.shared.exception.InvalidReportPeriodException;
import br.com.coursefeedback.shared.exception.ReportNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ReportServiceTest {
    private CourseRepository courseRepository;

    private FeedbackRepository feedbackRepository;

    private FeedbackReportRepository feedbackReportRepository;

    private ReportService reportService;

    @BeforeEach
    void setup() {

        courseRepository =
                Mockito.mock(CourseRepository.class);

        feedbackRepository =
                Mockito.mock(FeedbackRepository.class);

        feedbackReportRepository =
                Mockito.mock(FeedbackReportRepository.class);

        reportService = new ReportService(
                courseRepository,
                feedbackRepository,
                feedbackReportRepository
        );
    }


    @Test
    void generateReport_success() {

        GenerateReportRequestDTO request = new GenerateReportRequestDTO();

        request.setPeriodStart("2025-06-01");
        request.setPeriodEnd("2025-06-30");

        Feedback feedback = new Feedback();

        feedback.setFeedbackId("feedback-1");
        feedback.setCourseId("course-1");
        feedback.setUserId("user-1");
        feedback.setDescription("Excellent course");
        feedback.setRating(9);
        feedback.setUrgency(Urgency.LOW);
        feedback.setCreatedAt("2025-06-15T10:00:00");

        List<Feedback> feedbacks = new ArrayList<>();

        feedbacks.add(feedback);

        Course course = new Course();

        course.setCourseId("course-1");
        course.setName("Java");

        Mockito.when(feedbackRepository.findAll())
                .thenReturn(feedbacks);

        Mockito.when(courseRepository.findById("course-1"))
                .thenReturn(Optional.of(course));

        ReportResponseDTO response =
                reportService.generateReport(request);

        assertNotNull(response);

        assertEquals("2025-06-01", response.getPeriodStart());
        assertEquals("2025-06-30", response.getPeriodEnd());

        assertEquals(1, response.getTotalFeedbacks());
        assertEquals(1, response.getLowFeedbacks());
        assertEquals(0, response.getMediumFeedbacks());
        assertEquals(0, response.getHighFeedbacks());
        assertEquals(0, response.getCriticalFeedbacks());

        assertEquals(9.0, response.getAverageRating());

        Mockito.verify(feedbackRepository, Mockito.times(2))
                .findAll();

        Mockito.verify(courseRepository)
                .findById("course-1");

        Mockito.verify(feedbackReportRepository)
                .save(Mockito.any(FeedbackReport.class));
    }

    @Test
    void generateReport_invalidPeriod_shouldThrowException() {

        GenerateReportRequestDTO request = new GenerateReportRequestDTO();

        request.setPeriodStart("2025-06-30");
        request.setPeriodEnd("2025-06-01");

        try {

            reportService.generateReport(request);

            fail();

        } catch (InvalidReportPeriodException e) {
            assertEquals(
                    "The start date must be before or equal to the end date.",
                    e.getMessage()
            );
        }

        Mockito.verify(feedbackRepository, Mockito.never())
                .findAll();

        Mockito.verify(feedbackReportRepository, Mockito.never())
                .save(Mockito.any());
    }

    @Test
    void getAllReports_success() {

        FeedbackReport report = new FeedbackReport();

        report.setReportId("report-1");
        report.setPeriodStart("2025-06-01");
        report.setPeriodEnd("2025-06-30");
        report.setTotalFeedbacks(1);
        report.setLowFeedbacks(1);
        report.setMediumFeedbacks(0);
        report.setHighFeedbacks(0);
        report.setCriticalFeedbacks(0);
        report.setAverageRating(9.0);
        report.setGeneratedAt("2025-06-30T10:00:00");
        report.setGeneratedBy("ADMIN");
        report.setS3Key("reports/report.pdf");

        Mockito.when(feedbackReportRepository.findAll())
                .thenReturn(List.of(report));

        Mockito.when(feedbackRepository.findAll())
                .thenReturn(new ArrayList<>());

        List<ReportResponseDTO> response =
                reportService.getAllReports();

        assertNotNull(response);

        assertEquals(1, response.size());

        assertEquals("report-1", response.get(0).getReportId());

        Mockito.verify(feedbackReportRepository)
                .findAll();

        Mockito.verify(feedbackRepository)
                .findAll();
    }

    @Test
    void getAllReports_empty() {

        Mockito.when(feedbackReportRepository.findAll())
                .thenReturn(new ArrayList<>());

        List<ReportResponseDTO> response =
                reportService.getAllReports();

        assertNotNull(response);

        assertTrue(response.isEmpty());

        Mockito.verify(feedbackReportRepository)
                .findAll();

        Mockito.verify(feedbackRepository, Mockito.never())
                .findAll();
    }

    @Test
    void getReportById_found() {

        FeedbackReport report = new FeedbackReport();

        report.setReportId("report-1");
        report.setPeriodStart("2025-06-01");
        report.setPeriodEnd("2025-06-30");
        report.setTotalFeedbacks(1);
        report.setLowFeedbacks(1);
        report.setMediumFeedbacks(0);
        report.setHighFeedbacks(0);
        report.setCriticalFeedbacks(0);
        report.setAverageRating(9.0);
        report.setGeneratedAt("2025-06-30T10:00:00");
        report.setGeneratedBy("ADMIN");
        report.setS3Key("reports/report.pdf");

        Mockito.when(feedbackReportRepository.findById("report-1"))
                .thenReturn(Optional.of(report));

        Mockito.when(feedbackRepository.findAll())
                .thenReturn(new ArrayList<>());

        ReportResponseDTO response =
                reportService.getReportById("report-1");

        assertNotNull(response);

        assertEquals("report-1", response.getReportId());

        Mockito.verify(feedbackReportRepository)
                .findById("report-1");

        Mockito.verify(feedbackRepository)
                .findAll();
    }


    @Test
    void getReportById_notFound() {

        Mockito.when(feedbackReportRepository.findById("report-1"))
                .thenReturn(Optional.empty());

        try {

            reportService.getReportById("report-1");

            fail();

        } catch (ReportNotFoundException e) {

            assertEquals(
                    "Report was not found.",
                    e.getMessage()
            );
        }

        Mockito.verify(feedbackReportRepository)
                .findById("report-1");

        Mockito.verify(feedbackRepository, Mockito.never())
                .findAll();
    }
}


