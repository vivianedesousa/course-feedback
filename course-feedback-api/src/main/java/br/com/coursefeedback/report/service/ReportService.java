package br.com.coursefeedback.report.service;
import br.com.coursefeedback.course.domain.Course;
import br.com.coursefeedback.course.repository.CourseRepository;
import br.com.coursefeedback.feedback.domain.Feedback;
import br.com.coursefeedback.feedback.repository.FeedbackRepository;
import br.com.coursefeedback.report.domain.FeedbackReport;
import br.com.coursefeedback.report.dto.DailyFeedbackSummaryResponseDTO;
import br.com.coursefeedback.report.dto.FeedbackSummaryResponseDTO;
import br.com.coursefeedback.report.dto.GenerateReportRequestDTO;
import br.com.coursefeedback.report.dto.ReportResponseDTO;
import br.com.coursefeedback.report.repository.FeedbackReportRepository;
import br.com.coursefeedback.shared.exception.InvalidReportPeriodException;
import br.com.coursefeedback.shared.exception.ReportNotFoundException;
import jakarta.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class ReportService {
    private final CourseRepository courseRepository;
    private final FeedbackRepository feedbackRepository;
    private final FeedbackReportRepository feedbackReportRepository;

    public ReportService(
            CourseRepository courseRepository,
            FeedbackRepository feedbackRepository,
            FeedbackReportRepository feedbackReportRepository) {
        this.courseRepository =courseRepository;
        this.feedbackRepository = feedbackRepository;
        this.feedbackReportRepository = feedbackReportRepository;
    }

    public ReportResponseDTO generateReport(
            GenerateReportRequestDTO requestDTO) {
        validatePeriod(requestDTO);
        FeedbackReport report = buildReport(requestDTO);
        feedbackReportRepository.save(report);

        return toReportResponse(report);
    }

    private void validatePeriod(GenerateReportRequestDTO requestDTO){
     LocalDate start =
             LocalDate.parse(requestDTO.getPeriodStart()); // que foi enviado pelo son
     LocalDate end =
             LocalDate.parse(requestDTO.getPeriodEnd());
       if (start.isAfter(end)) {
           throw new InvalidReportPeriodException("The start date must be before or equal to the end date.");
       }
     }

    public List<ReportResponseDTO> getAllReports() {
        List<FeedbackReport> reports = feedbackReportRepository.findAll();
        List<ReportResponseDTO> response = new ArrayList<>();
        for (FeedbackReport report : reports) {
            response.add(toReportResponse(report));
        }
        return response;
    }
   //  * Busca um relatório pelo ID.
    public ReportResponseDTO getReportById(String reportId){
        Optional<FeedbackReport> reportOptional = feedbackReportRepository.findById(reportId);
        if (reportOptional.isEmpty()) {
            throw new ReportNotFoundException("Report was not found.");
        }
        FeedbackReport report = reportOptional.get();
        return toReportResponse(report);
    }

    private FeedbackReport buildReport(GenerateReportRequestDTO requestDTO) {
        FeedbackReport report = new FeedbackReport();
        report.setReportId(UUID.randomUUID().toString());
        report.setPeriodStart(requestDTO.getPeriodStart());
        report.setPeriodEnd(requestDTO.getPeriodEnd());
        report.setGeneratedAt(
                LocalDateTime.now()
                        .withNano(0)
                        .toString());

        report.setGeneratedBy("ADMIN");
        report.setS3Key("reports/report.pdf");
        // Busca todos os feedbacks
        List<Feedback> allFeedbacks = feedbackRepository.findAll();
        // Datas informadas pelo administrador
        LocalDate start =
                LocalDate.parse(requestDTO.getPeriodStart());
        LocalDate end =
                LocalDate.parse(requestDTO.getPeriodEnd());
        // Contadores
        int total = 0;
        int low = 0;
        int medium = 0;
        int high = 0;
        int critical = 0;
        int ratingSum = 0;
        // Percorre todos os feedbacks
        for (Feedback feedback : allFeedbacks) {
            LocalDate feedbackDate =
                    LocalDateTime
                            .parse(feedback.getCreatedAt())
                            .toLocalDate();
            // Verifica se pertence ao período

            if (feedbackDate.isBefore(start) ||
                    feedbackDate.isAfter(end)) {
                continue;
            }
            total++;
            ratingSum += feedback.getRating();
            switch (feedback.getUrgency()) {

                case LOW:
                    low++;
                    break;

                case MEDIUM:
                    medium++;
                    break;

                case HIGH:
                    high++;
                    break;

                case CRITICAL:
                    critical++;
                    break;
            }
        }

        double average = 0;

        if (total > 0) {
            average = Math.round(((double) ratingSum / total) * 100.0) / 100.0;
        }

        report.setTotalFeedbacks(total);
        report.setLowFeedbacks(low);
        report.setMediumFeedbacks(medium);
        report.setHighFeedbacks(high);
        report.setCriticalFeedbacks(critical);
        report.setAverageRating(average);
        return report;
    }

    private List<FeedbackSummaryResponseDTO> buildFeedbackSummary(List<Feedback> feedbacks) {
        List<FeedbackSummaryResponseDTO> summary = new ArrayList<>();
        for (Feedback feedback : feedbacks) {
            FeedbackSummaryResponseDTO dto = new FeedbackSummaryResponseDTO();
            // Busca o curso pelo ID para obter o nome do curso no relatório.
            // Como o repositório retorna um Optional, verifica se o curso foi encontrado.
            Optional<Course> courseOptional = courseRepository.findById(feedback.getCourseId());
            if (courseOptional.isPresent()) {
                Course course = courseOptional.get();
                dto.setCourseName(course.getName());
            }
            dto.setDescription(feedback.getDescription());
            dto.setUrgency(feedback.getUrgency());
            dto.setSubmittedAt(feedback.getCreatedAt());
            summary.add(dto);
        }
        return summary;
    }


    private List<DailyFeedbackSummaryResponseDTO> buildDailySummary(List<Feedback> feedbacks) {
        List<DailyFeedbackSummaryResponseDTO> days = new ArrayList<>();
        for (Feedback feedback : feedbacks) {
            String date = feedback.getCreatedAt().substring(0, 10);
            boolean found = false;
            for (DailyFeedbackSummaryResponseDTO day : days) {
                if (day.getDate().equals(date)) {
                    day.setTotalFeedbacks(day.getTotalFeedbacks() + 1);
                    found = true;
                    break;
                }
            }
            if (!found) {
                days.add(new DailyFeedbackSummaryResponseDTO(date, 1));
            }
        }
        return days;
    }

   private ReportResponseDTO toReportResponse(FeedbackReport report) {

        List<Feedback> allFeedbacks = feedbackRepository.findAll();

        List<Feedback> feedbacks = new ArrayList<>();

        LocalDate start = LocalDate.parse(report.getPeriodStart());
        LocalDate end = LocalDate.parse(report.getPeriodEnd());

        for (Feedback feedback : allFeedbacks) {

            LocalDate feedbackDate = LocalDateTime
                    .parse(feedback.getCreatedAt())
                    .toLocalDate();

            if (!feedbackDate.isBefore(start) && !feedbackDate.isAfter(end)) {
                feedbacks.add(feedback);
            }
        }


        List<FeedbackSummaryResponseDTO> feedbackSummary =
                buildFeedbackSummary(feedbacks);
        List<DailyFeedbackSummaryResponseDTO> dailySummary =
                buildDailySummary(feedbacks);
        return new ReportResponseDTO(
                report.getReportId(),
                report.getPeriodStart(),
                report.getPeriodEnd(),
                report.getTotalFeedbacks(),
                report.getLowFeedbacks(),
                report.getMediumFeedbacks(),
                report.getHighFeedbacks(),
                report.getCriticalFeedbacks(),
                report.getAverageRating(),
                report.getGeneratedAt(),
                report.getGeneratedBy(),
                report.getS3Key(),
                dailySummary,
                feedbackSummary
        );
    }
    }






