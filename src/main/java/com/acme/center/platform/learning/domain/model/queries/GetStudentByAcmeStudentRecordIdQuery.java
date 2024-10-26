package com.acme.center.platform.learning.domain.model.queries;

import com.acme.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;

public record GetStudentByAcmeStudentRecordIdQuery(AcmeStudentRecordId studentRecordId) {
    public GetStudentByAcmeStudentRecordIdQuery {
        if (studentRecordId == null || studentRecordId.studentRecordId() == null || studentRecordId.studentRecordId().isBlank()) {
            throw new IllegalArgumentException("StudentRecordId cannot be null");
        }
    }
}
