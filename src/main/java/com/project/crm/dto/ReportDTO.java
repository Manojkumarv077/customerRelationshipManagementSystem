package com.project.crm.dto;

public class ReportDTO {
    private String label;
    private Double total;

    public ReportDTO(String label, Double total) {
        this.label = label;
        this.total = total;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
