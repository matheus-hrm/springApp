package com.example.spring.security;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CreateNewUserController {

    private final PasswordEncoder passwordEncoder;

    private final CustomUserRepository customUserRepository;

    public CreateNewUserController(PasswordEncoder passwordEncoder, CustomUserRepository customUserRepository) {
        this.passwordEncoder = passwordEncoder;
        this.customUserRepository = customUserRepository;
    }

    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@RequestBody CustomUser customUser) {
        //TODO: Service class
        //TODO: error handling

        Optional<CustomUser> existingUser = customUserRepository.findById(customUser.getUsername());
        if (existingUser.isEmpty()) {
            customUser.setPassword(passwordEncoder.encode(customUser.getPassword()));
            customUserRepository.save(customUser);
            return ResponseEntity.ok("User created");
        } else {
            return ResponseEntity.badRequest().body("User already exists");
        }
    }
}
