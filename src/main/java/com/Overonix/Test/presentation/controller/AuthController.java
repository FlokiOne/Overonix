package com.Overonix.Test.presentation.controller;


import com.Overonix.Test.domain.user.UserService;
import com.Overonix.Test.presentation.dto.RqAuth;
import com.Overonix.Test.presentation.dto.RqRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody RqAuth rqAuth) {
        return this.userService.auth(rqAuth);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RqRegistration rqRegistration) {
        return this.userService.save(rqRegistration);
    }

}
