package com.acme.center.platform.learning.domain.model.commands;

/**
 * Command to complete a tutorial for an enrollment.
 */
public record CompleteTutorialForEnrollmentCommand(Long enrollmentId, Long tutorialId) {
    /**
     * Constructor.
     * @param enrollmentId the enrollment id
     *                     (required, cannot be null or less than or equal to zero)
     * @param tutorialId the tutorial id
     *                   (required, cannot be null or less than or equal to zero)
     * @throws IllegalArgumentException if enrollmentId or tutorialId is null or less than or equal to zero
     */
    public CompleteTutorialForEnrollmentCommand {
        if (enrollmentId == null || enrollmentId <= 0) {
            throw new IllegalArgumentException("enrollmentId is required, cannot be null or less than or equal to zero");
        }
        if (tutorialId == null || tutorialId <= 0) {
            throw new IllegalArgumentException("tutorialId is required, cannot be null or less than or equal to zero");
        }
    }
}
