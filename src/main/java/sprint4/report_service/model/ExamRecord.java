// src/main/java/sprint4/report_service/model/ExamRecord.java
package sprint4.report_service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExamRecord {

    /** El JSON trae "id", mapeamos a patientId */
    @JsonProperty("id")
    private Long patientId;

    /** El JSON trae "tipo" */
    @JsonProperty("tipo")
    private String examType;

    /** El JSON trae "creadoEn" con microsegundos sin zona */
    @JsonProperty("creadoEn")
    @JsonFormat(shape = JsonFormat.Shape.STRING,
                pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS",
                timezone = "UTC")
    private Instant timestamp;

    /** El JSON trae "resultado" con valores "anomaly" o "normal" */
    @JsonProperty("resultado")
    private String resultado;

    // getters / setters

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

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    /**
     * Sólo devuelve true si el resultado es "anomaly"
     */
    public boolean isAnomaly() {
        return "anomaly".equalsIgnoreCase(this.resultado);
    }
}
