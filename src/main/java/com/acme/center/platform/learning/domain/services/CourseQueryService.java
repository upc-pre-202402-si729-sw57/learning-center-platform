package com.acme.center.platform.learning.domain.services;

import com.acme.center.platform.learning.domain.model.aggregates.Course;
import com.acme.center.platform.learning.domain.model.entities.LearningPathItem;
import com.acme.center.platform.learning.domain.model.queries.GetAllCoursesQuery;
import com.acme.center.platform.learning.domain.model.queries.GetCourseByIdQuery;
import com.acme.center.platform.learning.domain.model.queries.GetLearningPathItemByCourseIdAndTutorialIdQuery;

import java.util.List;
import java.util.Optional;

/**
 * Course Query Service
 */
public interface CourseQueryService {
    /**
     * Handle Get Course By ID Query
     *
     * @param query The {@link GetCourseByIdQuery} Query
     * @return A {@link Course} instance if the query is valid, otherwise empty
     */
    Optional<Course> handle(GetCourseByIdQuery query);

    /**
     * Handle Get All Courses Query
     *
     * @param query The {@link GetAllCoursesQuery} Query
     * @return A list of {@link Course} instances
     */
    List<Course> handle(GetAllCoursesQuery query);

    /**
     * Handle Get Learning Path Item By Course ID And Tutorial ID Query
     *
     * @param query The {@link GetLearningPathItemByCourseIdAndTutorialIdQuery} Query
     * @return A {@link LearningPathItem} instance if the query is valid, otherwise empty
     */
    Optional<LearningPathItem> handle(GetLearningPathItemByCourseIdAndTutorialIdQuery query);
}
