package com.acme.center.platform.learning.domain.model.commands;

/**
 * Command to add a tutorial to a course learning path.
 */
public record AddTutorialToCourseLearningPathCommand(Long tutorialId, Long courseId) {
    /**
     * Constructor.
     * @param tutorialId the tutorial id
     *                   (required, cannot be null or less than or equal to zero)
     * @param courseId the course id
     *                 (required, cannot be null or less than or equal to zero)
     * @throws IllegalArgumentException if tutorialId or courseId is null or less than or equal to zero
     */
    public AddTutorialToCourseLearningPathCommand {
        if (tutorialId == null || tutorialId <= 0) {
            throw new IllegalArgumentException("tutorialId is required, cannot be null or less than or equal to zero");
        }
        if (courseId == null || courseId <= 0) {
            throw new IllegalArgumentException("courseId is required, cannot be null or less than or equal to zero");
        }
    }
}
