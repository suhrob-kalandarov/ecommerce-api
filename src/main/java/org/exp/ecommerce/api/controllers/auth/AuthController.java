package org.exp.ecommerce.api.controllers.auth;

import lombok.RequiredArgsConstructor;
import org.exp.ecommerce.api.configs.security.JwtService;
import org.exp.ecommerce.api.models.base.Attachment;
import org.exp.ecommerce.api.models.user.Role;
import org.exp.ecommerce.api.models.user.User;
import org.exp.ecommerce.api.repositories.AttachmentRepository;
import org.exp.ecommerce.api.repositories.RoleRepository;
import org.exp.ecommerce.api.repositories.UserRepository;
import org.exp.ecommerce.api.request.LoginReq;
import org.exp.ecommerce.api.request.RegisterReq;
import org.exp.ecommerce.api.utils.Const;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(Const.API + "/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AttachmentRepository attachmentRepository;
    private final PasswordEncoder passwordEncoder;

    /// Login
    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody LoginReq loginReq) {
        var auth = new UsernamePasswordAuthenticationToken(
                loginReq.getEmail(),
                loginReq.getPassword()
        );
        authenticationManager.authenticate(auth);
        String token = jwtService.generateToken(loginReq.getEmail());
        return ResponseEntity.ok(token);
    }

    /// Register
    @PostMapping(value = "/register", consumes = {"multipart/form-data"})
    public HttpEntity<?> register(
            @RequestParam("fullname") String fullname,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("phone") String phone,
            @RequestParam(value = "profileImage", required = false) MultipartFile profileImage
    ) {
        try {
            // Check if user already exists
            if (userRepository.findByEmail(email).isPresent()) {
                return ResponseEntity.badRequest().body("User with this email already exists");
            }

            // Save profile image if provided
            Attachment attachment = null;
            if (profileImage != null && !profileImage.isEmpty()) {
                attachment = new Attachment();
                attachment.setName(profileImage.getOriginalFilename());
                attachment.setCode(UUID.randomUUID().toString());
                attachment.setContent(profileImage.getBytes());
                attachment.set_active(true);
                attachment = attachmentRepository.save(attachment);
            }

            // Get USER role
            Role userRole = roleRepository.findByName("ROLE_USER")
                    .orElseThrow(() -> new RuntimeException("Default role not found"));

            // Create new user
            User user = new User();
            user.setFullname(fullname);
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(password));
            user.setPhone(phone);
            user.setAttachment(attachment);
            user.setRoles(Collections.singletonList(userRole));
            user.set_active(true);
            userRepository.save(user);

            // Generate token for the new user
            String token = jwtService.generateToken(email);
            return ResponseEntity.ok(token);

        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Error processing profile image: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error during registration: " + e.getMessage());
        }
    }
}