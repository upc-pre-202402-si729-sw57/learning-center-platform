package com.acme.center.platform.learning.domain.model.commands;

/**
 * Command to confirm an enrollment.
 */
public record ConfirmEnrollmentCommand(Long enrollmentId) {
    /**
     * Constructor.
     *
     * @param enrollmentId the enrollment ID.
     *                     Cannot be null or less than or equal to 0.
     * @throws IllegalArgumentException if enrollmentId is null or less than or equal to 0.
     */
    public ConfirmEnrollmentCommand {
        if (enrollmentId == null || enrollmentId <= 0) {
            throw new IllegalArgumentException("enrollmentId cannot be null or less than or equal to 0");
        }
    }
}