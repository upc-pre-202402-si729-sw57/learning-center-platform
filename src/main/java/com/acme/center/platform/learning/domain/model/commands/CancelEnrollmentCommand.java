package com.acme.center.platform.learning.domain.model.commands;

/**
 * Command to cancel an enrollment.
 */
public record CancelEnrollmentCommand(Long enrollmentId) {
    /**
     * Constructor.
     * @param enrollmentId the enrollment id
     *                     (required, cannot be null or less than or equal to zero)
     * @throws IllegalArgumentException if enrollmentId is null or less than or equal to zero
     */
    public CancelEnrollmentCommand {
        if (enrollmentId == null || enrollmentId <= 0) {
            throw new IllegalArgumentException("enrollmentId is required, cannot be null or less than or equal to zero");
        }
    }
}
