package sprint4.report_service.model;

import java.time.Instant;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ExamRecord {
    private Long patientId;
    private String examType;
    private boolean anomaly;

    /** Jackson usará este patrón para parsear “2025-05-23T02:31:52.039638” */
    @JsonFormat(shape = JsonFormat.Shape.STRING,
                pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS",
                timezone = "UTC")
    private Instant timestamp;

    // getters & setters...

    public Long getPatientId() {
        return patientId;
    }
    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getExamType() {
        return examType;
    }
    public void setExamType(String examType) {
        this.examType = examType;
    }

    public boolean isAnomaly() {
        return anomaly;
    }
    public void setAnomaly(boolean anomaly) {
        this.anomaly = anomaly;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
