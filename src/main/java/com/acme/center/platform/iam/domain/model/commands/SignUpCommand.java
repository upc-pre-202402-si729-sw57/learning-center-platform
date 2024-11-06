package com.acme.center.platform.iam.domain.model.commands;

import com.acme.center.platform.iam.domain.model.entities.Role;

import java.util.List;

/**
 * Command to sign up a new user.
 * <p>
 *     This command is used to create a new user in the system.
 *     It contains the username and password of the new user.
 * </p>
 */
public record SignUpCommand(String username, String password, List<Role> roles) {
}
