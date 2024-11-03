package com.acme.center.platform.learning.infrastructure.persistence.jpa.repositories;

import com.acme.center.platform.learning.domain.model.aggregates.Student;
import com.acme.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;
import com.acme.center.platform.learning.domain.model.valueobjects.ProfileId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Student Repository
 */
public interface StudentRepository extends JpaRepository<Student, Long> {

    /**
     * Find a Student by Acme Student Record ID
     *
     * @param studentRecordId The Acme Student Record ID
     * @return An {@link Optional} of the {@link Student} instance
     */
    Optional<Student> findByAcmeStudentRecordId(AcmeStudentRecordId studentRecordId);

    /**
     * Find a Student by Profile ID
     *
     * @param profileId The Profile ID
     * @return An {@link Optional} of the {@link Student} instance
     */
    Optional<Student> findByProfileId(ProfileId profileId);

    /**
     * Check if a Student with the given Acme Student Record ID exists
     *
     * @param studentRecordId The Acme Student Record ID
     * @return true if a Student with the given Acme Student Record ID exists, otherwise false
     */
    boolean existsByAcmeStudentRecordId(AcmeStudentRecordId studentRecordId);
}
