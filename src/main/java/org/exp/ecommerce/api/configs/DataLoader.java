package org.exp.ecommerce.api.configs;

import lombok.RequiredArgsConstructor;
import org.exp.ecommerce.api.models.user.Role;
import org.exp.ecommerce.api.models.user.User;
import org.exp.ecommerce.api.repositories.RoleRepository;
import org.exp.ecommerce.api.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        if (roleRepository.count() == 0) {
            Role roleUser = Role.builder()
                    .name("ROLE_USER")
                    .build();

            Role roleAdmin = Role.builder()
                    .name("ROLE_ADMIN")
                    .build();
            roleRepository.saveAll(List.of(roleUser, roleAdmin));
        }

        if (userRepository.count() == 0) {
            User user = User.builder()
                    .fullname("Eshmat Toshmatov")
                    ._active(true)
                    .phone("901234567")
                    .email("eshmat@gmail.com")
                    .password(passwordEncoder.encode("120232Sk$"))
                    .roles(Collections.singletonList(roleRepository.getById(1)))
                    .build();
            userRepository.save(user);
        }
    }
}
