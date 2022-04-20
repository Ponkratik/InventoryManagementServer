package com.ponkratov.inventorymanagementserver.controller;

import com.ponkratov.inventorymanagementserver.payload.request.SigninRequest;
import com.ponkratov.inventorymanagementserver.payload.request.SignupRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public interface AuthController {
    @PostMapping("/signin")
    ResponseEntity<?> authenticateUser(@Valid @RequestBody SigninRequest loginRequest);

    @PostMapping("/signup")
    //@PreAuthorize("hasRole('ROLE_SYSADMIN')")
    ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest);
}
