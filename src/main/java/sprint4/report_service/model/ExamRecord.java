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

  public Long getPatientId() { return patientId; }
  public String getExamType()   { return examType; }
  public Instant getTimestamp() { return timestamp; }

  public boolean isAnomaly() {
    return resultado != null &&
           resultado.toLowerCase().contains("anomaly");
  }

  // setters Jackson‐style...
  public void setPatientId(Long patientId)   { this.patientId = patientId; }
  public void setExamType(String examType)    { this.examType = examType; }
  public void setResultado(String resultado)  { this.resultado = resultado; }
  public void setTimestamp(Instant timestamp){ this.timestamp = timestamp; }
}
