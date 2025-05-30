// src/main/java/sprint4/report_service/model/ExamRecord.java
package sprint4.report_service.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Proyección del JSON que llega desde examen-service.
 * Se mantiene LocalDateTime para evitar el problema con Instant.
 */
public class ExamRecord {

    private Long id;
    private Long patientId;
    private String examType;
    private boolean anomaly;

    /** yyyy-MM-dd'T'HH:mm:ss.SSSSSS (micro-segundos) */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    private LocalDateTime creadoEn;

    /* getters / setters */
    public Long getId()              { return id; }
    public void setId(Long id)       { this.id = id; }

    public Long getPatientId()               { return patientId; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }

    public String getExamType()             { return examType; }
    public void setExamType(String examType){ this.examType = examType; }

    public boolean isAnomaly()            { return anomaly; }
    public void setAnomaly(boolean anomaly){ this.anomaly = anomaly; }

    public LocalDateTime getCreadoEn()             { return creadoEn; }
    public void setCreadoEn(LocalDateTime creadoEn){ this.creadoEn = creadoEn; }
}
