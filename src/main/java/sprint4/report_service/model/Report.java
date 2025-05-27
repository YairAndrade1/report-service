package sprint4.report_service.model;

import java.util.List;

public class Report {
    private String period;
    private String groupBy;
    private List<ReportEntry> data;
    // constructor, getters

    public Report(String period, String groupBy, List<ReportEntry> data) {
        this.period = period;
        this.groupBy = groupBy;
        this.data = data;
    }

    public String getPeriod() {
        return period;
    }

    public String getGroupBy() {
        return groupBy;
    }

    public List<ReportEntry> getData() {
        return data;
    }
}