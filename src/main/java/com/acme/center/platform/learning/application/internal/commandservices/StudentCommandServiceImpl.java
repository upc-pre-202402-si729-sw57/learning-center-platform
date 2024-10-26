package com.acme.center.platform.learning.application.internal.commandservices;

import com.acme.center.platform.learning.domain.exceptions.StudentNotFoundException;
import com.acme.center.platform.learning.domain.model.commands.CreateStudentCommand;
import com.acme.center.platform.learning.domain.model.commands.UpdateStudentMetricsOnTutorialCompletedCommand;
import com.acme.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;
import com.acme.center.platform.learning.domain.services.StudentCommandService;
import com.acme.center.platform.learning.infrastructure.persistence.jpa.repositories.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentCommandServiceImpl implements StudentCommandService {
    private final StudentRepository studentRepository;

    public StudentCommandServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public AcmeStudentRecordId handle(CreateStudentCommand command) {
        // TODO: Implement the logic to fetch the profile from the external service by email
        // If the profile is empty, create a new profile with command data.
        // If the profile is not empty, check if student exists in the system.
        // If profile is still empty, throw an exception.
        // Create a new student with the profile data.
        // Return the student record ID.
        // For now, return new AcmeStudentRecordId.
        return new AcmeStudentRecordId();
    }

    @Override
    public AcmeStudentRecordId handle(UpdateStudentMetricsOnTutorialCompletedCommand command) {
        studentRepository.findByAcmeStudentRecordId(command.studentRecordId()).map(student -> {
            // Update the student metrics with the tutorial completed.
            student.updateMetricsOnTutorialCompleted();
            studentRepository.save(student);
            return student.getAcmeStudentRecordId();
        }).orElseThrow(() -> new StudentNotFoundException(command.studentRecordId()));
        return null;
    }
}
