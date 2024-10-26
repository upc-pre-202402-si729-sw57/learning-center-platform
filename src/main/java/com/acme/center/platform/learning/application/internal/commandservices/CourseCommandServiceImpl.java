package com.acme.center.platform.learning.application.internal.commandservices;

import com.acme.center.platform.learning.domain.model.aggregates.Course;
import com.acme.center.platform.learning.domain.model.commands.*;
import com.acme.center.platform.learning.domain.services.CourseCommandService;
import com.acme.center.platform.learning.infrastructure.persistence.jpa.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseCommandServiceImpl implements CourseCommandService {
    private final CourseRepository courseRepository;

    public CourseCommandServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Long handle(CreateCourseCommand command) {
        if (courseRepository.existsByTitle(command.title()))
            throw new IllegalArgumentException("Course with title %s already exists".formatted(command.title()));
        var course = new Course(command);
        try {
            courseRepository.save(course);
            return course.getId();
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving course: %s".formatted(e.getMessage()));
        }
    }

    @Override
    public Optional<Course> handle(UpdateCourseCommand command) {
        if (courseRepository.existsByTitleAndIdIsNot(command.title(), command.courseId()))
            throw new IllegalArgumentException("Course with title %s already exists".formatted(command.title()));
        var course = courseRepository.findById(command.courseId());
        if (course.isEmpty())
            throw new IllegalArgumentException("Course with id %s not found".formatted(command.courseId()));
        var courseToUpdate = course.get();
        try {
            var updatedCourse = courseRepository.save(courseToUpdate.updateInformation(command.title(), command.description()));
            return Optional.of(updatedCourse);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error updating course: %s".formatted(e.getMessage()));
        }
    }

    @Override
    public void handle(DeleteCourseCommand command) {
        if (!courseRepository.existsById(command.courseId()))
            throw new IllegalArgumentException("Course with id %s not found".formatted(command.courseId()));
        try {
            courseRepository.deleteById(command.courseId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error deleting course: %s".formatted(e.getMessage()));
        }
    }

    @Override
    public void handle(AddTutorialToCourseLearningPathCommand command) {
        if (!courseRepository.existsById(command.courseId()))
            throw new IllegalArgumentException("Course with id %s not found".formatted(command.courseId()));
        try {
            courseRepository.findById(command.courseId()).map(course -> {
                course.addTutorialToLearningPath(command.tutorialId());
                return courseRepository.save(course);
            });
        } catch (Exception e) {
            throw new IllegalArgumentException("Error adding tutorial to course learning path: %s".formatted(e.getMessage()));
        }

    }
}
