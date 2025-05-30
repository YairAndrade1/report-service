package sprint4.report_service.controller;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import sprint4.report_service.model.Report;
import sprint4.report_service.service.ReportService;

@RestController
@RequestMapping("/reports")
public class ReportController {
    private final ReportService service;

    public ReportController(ReportService service) {
        this.service = service;
    }

    @GetMapping
    public Mono<Report> getReport(
        @RequestParam("from")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        LocalDateTime from
    ) {
        Instant fromInstant = from.atZone(ZoneId.systemDefault()).toInstant();
        return service.generateReport(fromInstant);
    }
}
