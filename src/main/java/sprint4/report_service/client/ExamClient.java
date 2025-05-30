// src/main/java/sprint4/report_service/client/ExamClient.java
package sprint4.report_service.client;

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

    /** Llama  /recent-anomalies?from=…  con un Instant recibo desde el servicio */
    public Flux<ExamRecord> fetchExamsSince(Instant from) {
        return client.get()
                     .uri(uri -> uri.path("/recent-anomalies")
                                    .queryParam("from", from.toString())
                                    .build())
                     .retrieve()
                     .bodyToFlux(ExamRecord.class);
    }
}
