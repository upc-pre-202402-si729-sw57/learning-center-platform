package com.acme.center.platform.iam.domain.services;

import com.acme.center.platform.iam.domain.model.aggregates.User;
import com.acme.center.platform.iam.domain.model.commands.SignInCommand;
import com.acme.center.platform.iam.domain.model.commands.SignUpCommand;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

/**
 * User command service.
 * <p>
 *     This service is responsible for handling user commands.
 *     It provides methods to handle sign-up and sign-in commands.
 * </p>
 */
public interface UserCommandService {
    /**
     * Handle sign up command.
     *
     * @param command the command
     * @return an optional of user if the sign-up was successful
     */
    Optional<User> handle(SignUpCommand command);

    /**
     * Handle sign in command.
     *
     * @param command the command
     * @return an optional of user and token if the sign-in was successful
     */
    Optional<ImmutablePair<User, String>> handle(SignInCommand command);
}
