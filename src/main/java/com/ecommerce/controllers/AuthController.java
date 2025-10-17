package com.ecommerce.controllers;

import com.ecommerce.security.JwtService;
import com.ecommerce.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final UserService userService;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authManager, UserService userService, JwtService jwtService) {
        this.authManager = authManager;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> req) {
        String name = req.get("name");
        String email = req.get("email");
        String password = req.get("password");
        String role = req.getOrDefault("role", "ROLE_CLIENT");
        userService.registerUser(name, email, password, role);
        return ResponseEntity.ok(Map.of("message", "User registered successfully!"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> req) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.get("email"), req.get("password"))
        );

        UserDetails user = (UserDetails) auth.getPrincipal();
        String token = jwtService.generateToken(user.getUsername(), Map.of("role", user.getAuthorities().toString()));

        return ResponseEntity.ok(Map.of("token", token));
    }
}
