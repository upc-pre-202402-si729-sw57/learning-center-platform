package com.acme.center.platform.learning.domain.exceptions;

public class EnrollmentNotFoundException extends RuntimeException {
    public EnrollmentNotFoundException(Long enrollmentId) {
        super(String.format("Enrollment with id %d not found", enrollmentId));
    }
}
