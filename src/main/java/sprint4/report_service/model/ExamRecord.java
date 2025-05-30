// src/main/java/sprint4/report_service/model/ExamRecord.java
package sprint4.report_service.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ExamRecord {

    private Long id;

    // El micro-servicio NO envía patientId; lo dejamos nullable
    private Long patientId;

    /** Mapea JSON "tipo"  */
    @JsonProperty("tipo")
    private String examType;

    /** "resultado": puede ser 'anomaly' o algo más  */
    @JsonProperty("resultado")
    private String resultado;

    /** yyyy-MM-dd'T'HH:mm:ss.SSSSSS */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    @JsonProperty("creadoEn")
    private LocalDateTime creadoEn;

    /* ---------- lógica ---------- */

    /** true si el texto resultado contiene la palabra 'anomaly' */
    public boolean isAnomaly() {
        return resultado != null && resultado.toLowerCase().contains("anomaly");
    }

    /* ---------- getters / setters clásicos ---------- */

    public Long getId()                 { return id; }
    public void setId(Long id)          { this.id = id; }

    public Long getPatientId()                   { return patientId; }
    public void setPatientId(Long patientId)     { this.patientId = patientId; }

    public String getExamType()                  { return examType; }
    public void setExamType(String examType)     { this.examType = examType; }

    public String getResultado()                 { return resultado; }
    public void setResultado(String resultado)   { this.resultado = resultado; }

    public LocalDateTime getCreadoEn()           { return creadoEn; }
    public void setCreadoEn(LocalDateTime t)     { this.creadoEn = t; }
}
