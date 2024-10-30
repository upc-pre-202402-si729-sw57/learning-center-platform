package com.acme.center.platform.learning.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record StudentPerformanceMetricSet(Integer totalCompletedCourses, Integer totalCompletedTutorials) {
    public StudentPerformanceMetricSet() { this(0, 0); }

    public StudentPerformanceMetricSet {
        if (totalCompletedCourses < 0)
            throw new IllegalArgumentException("Total completed courses cannot be negative");
        if (totalCompletedTutorials < 0)
            throw new IllegalArgumentException("Total tutorials cannot be negative");
    }

    public StudentPerformanceMetricSet incrementTotalCompletedCourses() {
        return new StudentPerformanceMetricSet(totalCompletedCourses + 1, totalCompletedTutorials);
    }

    public StudentPerformanceMetricSet incrementTotalCompletedTutorials() {
        return new StudentPerformanceMetricSet(totalCompletedCourses, totalCompletedTutorials + 1);
    }
}
