package com.acme.center.platform.learning.domain.model.aggregates;

import com.acme.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;
import com.acme.center.platform.learning.domain.model.valueobjects.EnrollmentStatus;
import com.acme.center.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Entity
public class Enrollment extends AuditableAbstractAggregateRoot<Enrollment> {

    @Getter
    @Embedded
    private AcmeStudentRecordId acmeStudentRecordId;

    @Getter
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private EnrollmentStatus status;

    // TODO: Add relationship with progress record

    public Enrollment(AcmeStudentRecordId studentRecordId, Course course) {
        this.acmeStudentRecordId = studentRecordId;
        this.course = course;
        this.status = EnrollmentStatus.REQUESTED;
        // TODO: Initialize progress record
    }

    public Enrollment() {
        // Required by JPA
    }

    public void confirm() {
        this.status = EnrollmentStatus.CONFIRMED;
    }

    public void reject() {
        this.status = EnrollmentStatus.REJECTED;
    }

    public void cancel() {
        this.status = EnrollmentStatus.CANCELLED;
    }

    public String getStatus() {
        return status.name().toLowerCase();
    }

    public boolean isCancelled() {
        return status == EnrollmentStatus.CANCELLED;
    }

    public boolean isConfirmed() {
        return status == EnrollmentStatus.CONFIRMED;
    }

    public boolean isRejected() {
        return status == EnrollmentStatus.REJECTED;
    }

    public boolean isRequested() {
        return status == EnrollmentStatus.REQUESTED;
    }

    public void completeTutorial(Long tutorialId) {
        // TODO: Update Progress Record and publish Event to notify completion
    }
}
