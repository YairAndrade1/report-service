// src/main/java/sprint4/report_service/model/ExamRecord.java
package sprint4.report_service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExamRecord {

    @JsonProperty("id")
    private Long patientId;

    @JsonProperty("tipo")
    private String examType;

    /** Lo mapeo a LocalDateTime porque el JSON no trae zona */
    @JsonProperty("creadoEn")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    private LocalDateTime createdAt;

    @JsonProperty("resultado")
    private String resultado;

    // getters y setters

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
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public String getResultado() {
        return resultado;
    }
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    /** como antes, filtramos solo anomalías */
    public boolean isAnomaly() {
        return "anomaly".equalsIgnoreCase(this.resultado);
    }
}
