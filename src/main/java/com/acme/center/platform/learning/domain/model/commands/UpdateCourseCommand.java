package com.acme.center.platform.learning.domain.model.commands;

public record UpdateCourseCommand(Long courseId, String title, String description) {
    public UpdateCourseCommand {
        if (courseId == null || courseId <= 0) {
            throw new IllegalArgumentException("CourseId is required");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title is required");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description is required");
        }
    }
}
