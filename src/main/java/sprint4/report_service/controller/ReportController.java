package sprint4.report_service.controller;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

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

    public ReportController(ReportService service) {
        this.service = service;
    }

    @GetMapping
    public Mono<Report> getReport(@RequestParam(name="from", defaultValue="PT720h") String from) {
        Duration period = Duration.parse(from);
        return service.generateReport(period)
            .timeout(Duration.ofSeconds(2))
            .onErrorMap(TimeoutException.class,
                e -> new ResponseStatusException(HttpStatus.GATEWAY_TIMEOUT, "Timeout > 2s", e));
    }
}