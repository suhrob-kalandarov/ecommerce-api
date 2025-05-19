package org.exp.ecommerce.api.repositories;

import org.exp.ecommerce.api.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    //UserDetails findByEmail(String email);

    Optional<User> findByEmail(String email);

}
