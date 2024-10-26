package com.acme.center.platform.learning.domain.model.queries;

public record GetCourseByIdQuery(Long courseId) {
    public GetCourseByIdQuery {
        if (courseId == null || courseId <= 0) {
            throw new IllegalArgumentException("Course id cannot be null or less than or equal to zero");
        }
    }
}
