package com.acme.center.platform.learning.domain.services;

import com.acme.center.platform.learning.domain.model.commands.CreateStudentCommand;
import com.acme.center.platform.learning.domain.model.commands.UpdateStudentMetricsOnTutorialCompletedCommand;
import com.acme.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;

/**
 * Service to handle student commands.
 */
public interface StudentCommandService {

    /**
     * Handle a command to create a student.
     *
     * @param command The {@link CreateStudentCommand} command
     * @return The student record ID
     */
    AcmeStudentRecordId handle(CreateStudentCommand command);

    /**
     * Handle a command to update student metrics when a tutorial is completed.
     *
     * @param command The {@link UpdateStudentMetricsOnTutorialCompletedCommand} command
     * @return The student record ID
     */
    AcmeStudentRecordId handle(UpdateStudentMetricsOnTutorialCompletedCommand command);
}
