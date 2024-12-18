package com.example.spring.security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @GetMapping("/open")
    public String open() {
        return "Open endpoint";
    }

    @GetMapping("/close")
    public String close() {
        return "Close endpoint";
    }

    @PreAuthorize("hasRole('superuser')")
    @GetMapping("/special")
    public String special() {
        return "Special endpoint";
    }

    @PreAuthorize("hasAnyRole('superuser', 'user')")
    @GetMapping("/basic")
    public String basic() {
        return "Basic endpoint";
    }
}
