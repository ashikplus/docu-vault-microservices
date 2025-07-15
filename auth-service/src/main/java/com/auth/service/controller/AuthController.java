package com.auth.service.controller;

import java.util.Collections;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.service.JwtService;
import com.auth.service.model.AuthRequest;
import com.auth.service.model.RegisterRequest;
import com.auth.service.model.Role;
import com.auth.service.model.User;
import com.auth.service.repository.RoleRepository;
import com.auth.service.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepo;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authManager, UserRepository userRepo, PasswordEncoder passwordEncoder, RoleRepository roleRepo, JwtService jwtService) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.roleRepo = roleRepo;
        this.authManager = authManager;
        this.jwtService = jwtService;
    }
    
    @GetMapping
    public String home() {
    	System.out.println("Hit");
        return "Welcome to the public page!";
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
    	
    	Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            UserDetails user = (UserDetails) authentication.getPrincipal();
            String token = jwtService.generateToken(user);

            return ResponseEntity.ok(Collections.singletonMap("token", token));
    }

    @SuppressWarnings("unchecked")
	@PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        if (userRepo.findByUsername(request.getUsername()).isPresent()) {
            return "Username already taken";
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEnabled(true);
        
        Role defaultRole = roleRepo.findByName("ROLE_DEFAULT")
                .orElseThrow(() -> new RuntimeException("Default role not found"));
        
        ((Set<Role>) user.getAuthorities()).add(defaultRole);


        userRepo.save(user);
        return "User registered successfully!";
    }
}
