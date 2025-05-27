package sprint4.report_service.model;

import java.util.List;

public class ReportEntry {
    private String examType;
    private long count;
    private List<Long> patientIds;
    
    // constructor, getters
    public ReportEntry(String examType, long count, List<Long> patientIds) {
        this.examType = examType;
        this.count = count;
        this.patientIds = patientIds;
    }

    public String getExamType() {
        return examType;
    }

    public long getCount() {
        return count;
    }

    public List<Long> getPatientIds() {
        return patientIds;
    }
}