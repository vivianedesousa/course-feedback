package br.com.coursefeedback.report.resource;

import br.com.coursefeedback.report.dto.GenerateReportRequestDTO;
import br.com.coursefeedback.report.dto.ReportResponseDTO;
import br.com.coursefeedback.report.service.ReportService;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/v1/api/admin/reports")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped

@Tag(
        name = "Reports",
        description = "Endpoints responsible for generating and retrieving feedback reports."
)
public class ReportResource {
    private final ReportService reportService;

    public ReportResource(ReportService reportService) {
        this.reportService = reportService;
    }

    @POST
    @Path("/generate")
    @Operation(
            summary = "Generate report",
            description = "Generates a new feedback report based on the specified criteria."
    )
    public ReportResponseDTO generateReport(
            @Valid GenerateReportRequestDTO requestDTO) {
        return reportService.generateReport(requestDTO);
    }

    @GET
    @Operation(
            summary = "List all reports",
            description = "Returns a list containing all generated reports."
    )
    public List<ReportResponseDTO> getAllReports() {
        return reportService.getAllReports();
    }

    @GET
    @Path("/{reportId}")
    @Operation(
            summary = "Get report by ID",
            description = "Retrieves the details of a specific report using its unique identifier."
    )
    public ReportResponseDTO getReportById(
            @PathParam("reportId") String reportId) {
        return reportService.getReportById(reportId);
    }

}
