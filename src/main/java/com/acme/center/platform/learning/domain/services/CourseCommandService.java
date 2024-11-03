package com.acme.center.platform.learning.domain.services;

import com.acme.center.platform.learning.domain.model.aggregates.Course;
import com.acme.center.platform.learning.domain.model.commands.AddTutorialToCourseLearningPathCommand;
import com.acme.center.platform.learning.domain.model.commands.CreateCourseCommand;
import com.acme.center.platform.learning.domain.model.commands.DeleteCourseCommand;
import com.acme.center.platform.learning.domain.model.commands.UpdateCourseCommand;

import java.util.Optional;

/**
 * Course Command Service
 */
public interface CourseCommandService {
    /**
     * Handle Create Course Command
     *
     * @param command The {@link CreateCourseCommand} Command
     * @return The ID of the created course
     * @throws IllegalArgumentException if the course title already exists
     */
    Long handle(CreateCourseCommand command);

    /**
     * Handle Update Course Command
     *
     * @param command The {@link UpdateCourseCommand} Command
     * @return An {@link Optional} of the updated {@link Course} instance
     * @throws IllegalArgumentException if the course title already exists
     * @throws IllegalArgumentException if the course is not found
     */
    Optional<Course> handle(UpdateCourseCommand command);

    /**
     * Handle Delete Course Command
     *
     * @param command The {@link DeleteCourseCommand} Command
     */
    void handle(DeleteCourseCommand command);

    /**
     * Handle Add Tutorial To Course Learning Path Command
     *
     * @param command The {@link AddTutorialToCourseLearningPathCommand} Command
     */
    void handle(AddTutorialToCourseLearningPathCommand command);
}
