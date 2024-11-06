package com.acme.center.platform.iam.infrastructure.persistence.jpa.repositories;

import com.acme.center.platform.iam.domain.model.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Role repository.
 * <p>
 *     This interface is used to interact with the database to perform CRUD operations on the Role entity.
 *     It additionally provides a method to find a role by its name and to check if a role with a given name exists.
 * </p>
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    /**
     * Find a role by its name.
     *
     * @param name the name of the role to find.
     * @return an optional containing the role if it exists, an empty optional otherwise.
     */
    Optional<Role> findByName(String name);

    /**
     * Check if a role with a given name exists.
     *
     * @param name the name of the role to check.
     * @return true if a role with the given name exists, false otherwise.
     */
    boolean existsByName(String name);
}
