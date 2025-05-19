package org.exp.ecommerce.api.controllers.auth;

import lombok.RequiredArgsConstructor;
import org.exp.ecommerce.api.configs.security.JwtService;
import org.exp.ecommerce.api.request.LoginReq;
import org.exp.ecommerce.api.utils.Const;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(Const.API + "/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

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

}
