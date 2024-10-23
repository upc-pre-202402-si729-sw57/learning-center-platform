package com.acme.center.platform.learning.domain.model.aggregates;

import com.acme.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;
import com.acme.center.platform.learning.domain.model.valueobjects.ProfileId;
import com.acme.center.platform.learning.domain.model.valueobjects.StudentPerformanceMetricSet;
import com.acme.center.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;

/**
 * Student aggregate root.
 * @summary
 * This class represents a student in the platform.
 * A student is a user that is registered in the platform.
 * @since 1.0.0
 */
@Entity
public class Student extends AuditableAbstractAggregateRoot<Student> {

    @Getter
    @Embedded
    @Column(name = "acme_student_id")
    private final AcmeStudentRecordId acmeStudentRecordId;

    @Embedded
    private ProfileId profileId;

    @Embedded
    private final StudentPerformanceMetricSet performanceMetricSet;

    /**
     * Default constructor.
     */
    public Student() {
        this.acmeStudentRecordId = new AcmeStudentRecordId();
        this.performanceMetricSet = new StudentPerformanceMetricSet();
    }

    /**
     * Constructor.
     * @param ProfileId Profile identifier.
     */
    public Student(Long ProfileId) {
        this();
        this.profileId = new ProfileId(ProfileId);
    }

    /**
     * Constructor.
     * @param profileId Profile identifier.
     */
    public Student(ProfileId profileId) {
        this();
        this.profileId = profileId;
    }

    /**
     * Update student metrics when a course is completed.
     */
    public void updateMetricsOnCourseCompleted() {
        this.performanceMetricSet.incrementTotalCompletedCourses();
    }

    /**
     * Update student metrics when a tutorial is completed.
     */
    public void updateMetricsOnTutorialCompleted() {
        this.performanceMetricSet.incrementTotalTutorials();
    }

    /**
     * Get student record identifier.
     * @return Student record identifier.
     */
    public String getStudentRecordId() {
        return this.acmeStudentRecordId.studentRecordId();
    }

    /**
     * Get student profile identifier.
     * @return Profile identifier.
     */
    public Long getProfileId() {
        return this.profileId.profileId();
    }

    /**
     * Get total completed courses.
     * @return Total completed courses.
     */
    public int getTotalCompletedCourses() {
        return this.performanceMetricSet.totalCompletedCourses();
    }

    /**
     * Get total tutorials.
     * @return Total tutorials.
     */
    public int getTotalTutorials() {
        return this.performanceMetricSet.totalTutorials();
    }
}
