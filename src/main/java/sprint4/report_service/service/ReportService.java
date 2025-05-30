// src/main/java/sprint4/report_service/service/ReportService.java
package sprint4.report_service.service;

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
    public ReportService(ExamClient examClient) { this.examClient = examClient; }

    public Mono<Report> generateReport(Instant from) {
    Instant now = Instant.now();
    return examClient.fetchExamsSince(from)
        .filter(ExamRecord::isAnomaly)
        .collectList()
        .map(list -> {
            Map<String, List<ExamRecord>> grouped =
                list.stream().collect(Collectors.groupingBy(ExamRecord::getExamType));

            List<ReportEntry> entries = grouped.entrySet().stream()
                .map(e -> new ReportEntry(
                    e.getKey(),
                    e.getValue().size(),
                    e.getValue().stream()                     // ← aquí
                        .map(this::patientId)            // ID real o inventado
                        .distinct()
                        .collect(Collectors.toList())))
                .collect(Collectors.toList());

            return new Report(from + "_to_" + now, "examType", entries);
        });
}

    /* ----- helpers ----- */

    private long patientId(ExamRecord r) {
    // si viene null, fabricamos uno determinístico con el id del examen
    return r.getPatientId() != null ? r.getPatientId()
                                    : 10_000L + r.getId();   // <-- inventado
    }
    private Report buildReport(List<ExamRecord> exams, Instant from, Instant to) {

        Map<String,List<ExamRecord>> grouped =
            exams.stream().collect(Collectors.groupingBy(ExamRecord::getExamType));

        List<ReportEntry> entries = grouped.entrySet().stream()
            .map(e -> new ReportEntry(
                       e.getKey(),
                       e.getValue().size(),
                       e.getValue().stream()
                                .map(ExamRecord::getPatientId)
                                .distinct()
                                .toList()))
            .toList();

        return new Report(from + "_to_" + to, "examType", entries);
    }
}
