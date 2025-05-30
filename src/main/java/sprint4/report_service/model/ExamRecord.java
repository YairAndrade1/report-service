package sprint4.report_service.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ExamRecord {
    private Long patientId;
    private String examType;
    private boolean anomaly;

    /** ahora es LocalDateTime, Jackson aplicará el patrón sin pedir “Z” */
    @JsonFormat(shape = JsonFormat.Shape.STRING,
                pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS",
                timezone = "UTC")
    private LocalDateTime timestamp;

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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
