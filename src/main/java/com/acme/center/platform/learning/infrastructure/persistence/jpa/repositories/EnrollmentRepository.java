package com.acme.center.platform.learning.infrastructure.persistence.jpa.repositories;

import com.acme.center.platform.learning.domain.model.aggregates.Enrollment;
import com.acme.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Enrollment Repository
 */
@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    /**
     * Find all Enrollments by Acme Student Record ID
     *
     * @param studentRecordId The Acme Student Record ID
     * @return A list of {@link Enrollment} instances
     */
    List<Enrollment> findAllByAcmeStudentRecordId(AcmeStudentRecordId studentRecordId);

    /**
     * Find all Enrollments by Course ID
     *
     * @param courseId The Course ID
     * @return A list of {@link Enrollment} instances
     */
    List<Enrollment> findAllByCourseId(Long courseId);

    /**
     * Find an Enrollment by Acme Student Record ID and Course ID
     *
     * @param studentRecordId The Acme Student Record ID
     * @param courseId The Course ID
     * @return An {@link Optional} of the {@link Enrollment} instance
     */
    Optional<Enrollment> findByAcmeStudentRecordIdAndCourseId(AcmeStudentRecordId studentRecordId, Long courseId);

}
