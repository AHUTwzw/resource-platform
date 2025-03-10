package com.resource.core.domain;

import java.time.LocalDate;

public class TimeRange extends TimeDescription {
    private LocalDate startDate;
    private LocalDate endDate;

    // Getters and Setters
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    @Override
    public String toString() {
        return "TimeRange{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}