package com.acme.center.platform.learning.domain.services;

import com.acme.center.platform.learning.domain.model.aggregates.Student;
import com.acme.center.platform.learning.domain.model.queries.GetStudentByAcmeStudentRecordIdQuery;
import com.acme.center.platform.learning.domain.model.queries.GetStudentByProfileIdQuery;

import java.util.Optional;

/**
 * Student Query Service
 */
public interface StudentQueryService {

    /**
     * Handle Get Student By Acme Student Record ID Query
     *
     * @param query The {@link GetStudentByAcmeStudentRecordIdQuery} Query
     * @return A {@link Student} instance if the query is valid, otherwise empty
     */
    Optional<Student> handle(GetStudentByAcmeStudentRecordIdQuery query);

    /**
     * Handle Get Student By Profile ID Query
     *
     * @param query The {@link GetStudentByProfileIdQuery} Query
     * @return A {@link Student} instance if the query is valid, otherwise empty
     */
    Optional<Student> handle(GetStudentByProfileIdQuery query);
}
