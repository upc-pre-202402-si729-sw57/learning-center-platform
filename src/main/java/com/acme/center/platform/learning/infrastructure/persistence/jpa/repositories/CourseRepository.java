package com.acme.center.platform.learning.infrastructure.persistence.jpa.repositories;

import com.acme.center.platform.learning.domain.model.aggregates.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Course Repository
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    /**
     * Find a Course by title
     *
     * @param title The title of the Course
     * @return An {@link Optional} of the {@link Course} instance
     */
    Optional<Course> findByTitle(String title);

    /**
     * Check if a Course with the given title exists
     *
     * @param title The title of the Course
     * @return true if a Course with the given title exists, otherwise false
     */
    boolean existsByTitle(String title);

    /**
     * Check if a Course with the given title exists and the ID is not the given ID
     *
     * @param title The title of the Course
     * @param id The ID of the Course
     * @return true if a Course with the given title exists and the ID is not the given ID, otherwise false
     */
    boolean existsByTitleAndIdIsNot(String title, Long id);
}
