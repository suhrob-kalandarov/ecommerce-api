package org.exp.ecommerce.api.repositories;

import org.exp.ecommerce.api.models.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    //Role findByName(String roleUser);

    Optional<Role> findByName(String name);
}
