package sprint4.report_service.client;

import java.time.Duration;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import sprint4.report_service.model.ExamRecord;

@Component
public class ExamClient {
    private final WebClient client;

    public ExamClient(@Value("${exam.service.url}") String baseUrl) {
        this.client = WebClient.builder()
            .baseUrl(baseUrl)
            .build();
    }

    public Flux<ExamRecord> fetchExams(Duration since) {
        Instant from = Instant.now().minus(since);
        return client.get()
            .uri(uri -> uri
                .path("/recent-anomalies")
                .queryParam("from", from.toString())
                .build())
            .retrieve()
            .bodyToFlux(ExamRecord.class);
    }

    public Flux<ExamRecord> fetchExamsSince(Instant from) {
        return client.get()
            .uri(uri -> uri
                .path("/recent-anomalies")
                .queryParam("from", from.toString())
                .build())
            .retrieve()
            .bodyToFlux(ExamRecord.class);
    }
}
