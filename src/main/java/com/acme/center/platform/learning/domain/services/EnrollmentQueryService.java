package com.acme.center.platform.learning.domain.services;

import com.acme.center.platform.learning.domain.model.aggregates.Enrollment;
import com.acme.center.platform.learning.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

/**
 * Enrollment Query Service
 */
public interface EnrollmentQueryService {

    /**
     * Handle Get All Enrollments By Acme Student Record ID Query
     *
     * @param query The {@link GetAllEnrollmentsByAcmeStudentRecordIdQuery} Query
     * @return A list of {@link Enrollment} instances
     */
    List<Enrollment>        handle(GetAllEnrollmentsByAcmeStudentRecordIdQuery query);

    /**
     * Handle Get Enrollment By ID Query
     *
     * @param query The {@link GetEnrollmentByIdQuery} Query
     * @return A {@link Enrollment} instance if the query is valid, otherwise empty
     */
    Optional<Enrollment>    handle(GetEnrollmentByIdQuery query);

    /**
     * Handle Get All Enrollments Query
     *
     * @param query The {@link GetAllEnrollmentsQuery} Query
     * @return A list of {@link Enrollment} instances
     */
    List<Enrollment>        handle(GetAllEnrollmentsQuery query);

    /**
     * Handle Get All Enrollments By Course ID Query
     *
     * @param query The {@link GetAllEnrollmentsByCourseIdQuery} Query
     * @return A list of {@link Enrollment} instances
     */
    List<Enrollment>        handle(GetAllEnrollmentsByCourseIdQuery query);

    /**
     * Handle Get Enrollment By Acme Student Record ID And Course ID Query
     *
     * @param query The {@link GetEnrollmentByAcmeStudentRecordIdAndCourseIdQuery} Query
     * @return A {@link Enrollment} instance if the query is valid, otherwise empty
     */
    Optional<Enrollment>    handle(GetEnrollmentByAcmeStudentRecordIdAndCourseIdQuery query);
}
