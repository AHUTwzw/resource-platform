package com.resource.core.domain;

import java.time.LocalDate;

public class TimeInterval extends TimeDescription {
    private LocalDate startDate;
    private LocalDate endDate;
    private String intervalType; // 如 "Yearly", "Monthly" 等

    // Getters and Setters
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public String getIntervalType() { return intervalType; }
    public void setIntervalType(String intervalType) { this.intervalType = intervalType; }

    @Override
    public String toString() {
        return "TimeInterval{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", intervalType='" + intervalType + '\'' +
                '}';
    }
}