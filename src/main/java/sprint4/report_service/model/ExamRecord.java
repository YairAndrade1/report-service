// src/main/java/sprint4/report_service/model/ExamRecord.java
package sprint4.report_service.model;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExamRecord {
  @JsonProperty("id")
  private Long patientId;

  @JsonProperty("tipo")
  private String examType;

  @JsonProperty("resultado")
  private String resultado;

  @JsonProperty("creadoEn")
  private Instant timestamp;

  public boolean isAnomaly() {
    return "anomaly".equalsIgnoreCase(resultado);
  }

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
    public String getResultado() {
        return resultado;
    }
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
    public Instant getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

}
