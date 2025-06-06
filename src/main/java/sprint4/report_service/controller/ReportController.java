// src/main/java/sprint4/report_service/controller/ReportController.java
package sprint4.report_service.controller;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.TimeoutException;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import reactor.core.publisher.Mono;
import sprint4.report_service.model.Report;
import sprint4.report_service.service.ReportService;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService service;
    public ReportController(ReportService service) { this.service = service; }

    @GetMapping
    public Mono<Report> getReport(
            @RequestParam("from")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime from) {

        Instant fromInstant = from.atZone(ZoneId.systemDefault()).toInstant();

        return service.generateReport(fromInstant)
                      .timeout(java.time.Duration.ofSeconds(3))
                      .onErrorMap(TimeoutException.class,
                          ex -> new ResponseStatusException(
                                  HttpStatus.GATEWAY_TIMEOUT,"Backend >3 s",ex));
    }
}
