package com.acme.center.platform.learning.domain.model.queries;

public record GetEnrollmentByIdQuery(Long enrollmentId) {
    public GetEnrollmentByIdQuery {
        if (enrollmentId == null || enrollmentId <= 0) {
            throw new IllegalArgumentException("enrollmentId cannot be null");
        }
    }
}
