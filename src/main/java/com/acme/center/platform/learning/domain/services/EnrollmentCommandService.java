package com.acme.center.platform.learning.domain.services;

import com.acme.center.platform.learning.domain.model.commands.*;

/**
 * Service to handle enrollment commands.
 */
public interface EnrollmentCommandService {

    /**
     * Handle a command to enroll a student in a course.
     *
     * @param command The {@link RequestEnrollmentCommand} command
     * @return The enrollment ID
     */
    Long handle(RequestEnrollmentCommand command);

    /**
     * Handle a command to confirm an enrollment.
     *
     * @param command The {@link ConfirmEnrollmentCommand} command
     * @return The enrollment ID
     */
    Long handle(ConfirmEnrollmentCommand command);

    /**
     * Handle a command to reject an enrollment.
     *
     * @param command The {@link RejectEnrollmentCommand} command
     * @return The enrollment ID
     */
    Long handle(RejectEnrollmentCommand command);

    /**
     * Handle a command to cancel an enrollment.
     *
     * @param command The {@link CancelEnrollmentCommand} command
     * @return The enrollment ID
     */
    Long handle(CancelEnrollmentCommand command);

    /**
     * Handle a command to complete a tutorial for an enrollment.
     *
     * @param command The {@link CompleteTutorialForEnrollmentCommand} command
     * @return The enrollment ID
     */
    Long handle(CompleteTutorialForEnrollmentCommand command);
}
