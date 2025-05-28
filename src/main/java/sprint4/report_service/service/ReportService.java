package sprint4.report_service.service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;
import sprint4.report_service.client.ExamClient;
import sprint4.report_service.model.ExamRecord;
import sprint4.report_service.model.Report;
import sprint4.report_service.model.ReportEntry;

@Service
public class ReportService {
    private final ExamClient examClient;

    public ReportService(ExamClient examClient) {
        this.examClient = examClient;
    }

    public Mono<Report> generateReport(Duration period) {
    Instant now  = Instant.now();
    Instant from = now.minus(period);

    return examClient.fetchExams(period)      // Llama al cliente
        .filter(ExamRecord::isAnomaly)          // Solo anomalías
        .collectList()
        .map(list -> {
        // Agrupa por tipo y extrae IDs únicos
        Map<String, List<ExamRecord>> grouped = list.stream()
            .collect(Collectors.groupingBy(ExamRecord::getExamType));
        List<ReportEntry> entries = grouped.entrySet().stream()
            .map(e -> new ReportEntry(
                e.getKey(),
                e.getValue().size(),
                e.getValue().stream()
                .map(ExamRecord::getPatientId)
                .distinct()
                .toList()))
            .toList();
        return new Report(from + "_to_" + now, "examType", entries);
        });
    }

}
