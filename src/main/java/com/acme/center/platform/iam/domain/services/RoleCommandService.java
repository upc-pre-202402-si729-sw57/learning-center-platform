package com.acme.center.platform.iam.domain.services;

import com.acme.center.platform.iam.domain.model.commands.SeedRolesCommand;

/**
 * RoleCommandService
 * <p>
 *     Service to handle role commands.
 * </p>
 */
public interface RoleCommandService {

    /**
     * Handle seed roles command.
     * <p>
     *     Seed roles command is used to seed roles in the system.
     * </p>
     *
     * @param command the {@link SeedRolesCommand} command
     */
    void handle(SeedRolesCommand command);
}
