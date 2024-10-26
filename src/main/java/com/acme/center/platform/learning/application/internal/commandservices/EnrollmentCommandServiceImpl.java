package com.acme.center.platform.learning.application.internal.commandservices;

import com.acme.center.platform.learning.domain.exceptions.CourseNotFoundException;
import com.acme.center.platform.learning.domain.exceptions.EnrollmentNotFoundException;
import com.acme.center.platform.learning.domain.exceptions.EnrollmentRequestException;
import com.acme.center.platform.learning.domain.model.aggregates.Enrollment;
import com.acme.center.platform.learning.domain.model.commands.*;
import com.acme.center.platform.learning.domain.services.EnrollmentCommandService;
import com.acme.center.platform.learning.infrastructure.persistence.jpa.repositories.CourseRepository;
import com.acme.center.platform.learning.infrastructure.persistence.jpa.repositories.EnrollmentRepository;
import com.acme.center.platform.learning.infrastructure.persistence.jpa.repositories.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentCommandServiceImpl implements EnrollmentCommandService {
    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public EnrollmentCommandServiceImpl(EnrollmentRepository enrollmentRepository, CourseRepository courseRepository, StudentRepository studentRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Long handle(RequestEnrollmentCommand command) {
         if(!studentRepository.existsByAcmeStudentRecordId(command.studentRecordId()))
                throw new IllegalArgumentException("Student with ACME student record ID %s not found".formatted(command.studentRecordId().studentRecordId()));
         var course = courseRepository.findById(command.courseId())
                 .orElseThrow(() -> new CourseNotFoundException(command.courseId()));
         try {
             var enrollment = new Enrollment(command, course);
             enrollmentRepository.save(enrollment);
                return enrollment.getId();
         } catch (Exception e) {
             throw new EnrollmentRequestException(e.getMessage());
         }
    }

    @Override
    public Long handle(ConfirmEnrollmentCommand command) {
        enrollmentRepository.findById(command.enrollmentId()).map(enrollment -> {
            enrollment.confirm();
            enrollmentRepository.save(enrollment);
            return enrollment.getId();
        }).orElseThrow(() -> new EnrollmentNotFoundException(command.enrollmentId()));
        return null;
    }

    @Override
    public Long handle(RejectEnrollmentCommand command) {
        enrollmentRepository.findById(command.enrollmentId()).map(enrollment -> {
            enrollment.reject();
            enrollmentRepository.save(enrollment);
            return enrollment.getId();
        }).orElseThrow(() -> new EnrollmentNotFoundException(command.enrollmentId()));
        return null;
    }

    @Override
    public Long handle(CancelEnrollmentCommand command) {
        enrollmentRepository.findById(command.enrollmentId()).map(enrollment -> {
            enrollment.cancel();
            enrollmentRepository.save(enrollment);
            return enrollment.getId();
        }).orElseThrow(() -> new EnrollmentNotFoundException(command.enrollmentId()));
        return null;
    }

    @Override
    public Long handle(CompleteTutorialForEnrollmentCommand command) {
        enrollmentRepository.findById(command.enrollmentId()).map(enrollment -> {
            enrollment.completeTutorial(command.tutorialId());
            enrollmentRepository.save(enrollment);
            return enrollment.getId();
        }).orElseThrow(() -> new EnrollmentNotFoundException(command.enrollmentId()));
        return null;
    }
}
