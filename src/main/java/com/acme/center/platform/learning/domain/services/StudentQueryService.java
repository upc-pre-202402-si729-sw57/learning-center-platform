package com.acme.center.platform.learning.domain.services;

import com.acme.center.platform.learning.domain.model.aggregates.Student;
import com.acme.center.platform.learning.domain.model.queries.GetStudentByAcmeStudentRecordIdQuery;
import com.acme.center.platform.learning.domain.model.queries.GetStudentByProfileIdQuery;

import java.util.Optional;

public interface StudentQueryService {
    Optional<Student> handle(GetStudentByAcmeStudentRecordIdQuery query);
    Optional<Student> handle(GetStudentByProfileIdQuery query);
}
