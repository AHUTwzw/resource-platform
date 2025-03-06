package com.resource.core.domain;

import java.time.LocalDate;

public class TimePoint extends TimeDescription {
    private LocalDate date;

    // Getters and Setters
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    @Override
    public String toString() {
        return "TimePoint{" +
                "date=" + date +
                '}';
    }
}