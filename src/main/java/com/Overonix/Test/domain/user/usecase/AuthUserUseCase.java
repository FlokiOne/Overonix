package com.Overonix.Test.domain.user.usecase;

import com.Overonix.Test.config.security.jwt.JwtUtils;
import com.Overonix.Test.config.security.services.UserDetailsImpl;
import com.Overonix.Test.presentation.dto.RqAuth;
import com.Overonix.Test.presentation.dto.RsAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthUserUseCase {
    AuthenticationManager authenticationManager;
    JwtUtils jwtUtils;

    @Autowired
    public AuthUserUseCase (AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    public ResponseEntity<?> authenticateUser(@Valid @RequestBody RqAuth rqAuth) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(rqAuth.getUsername(), rqAuth.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new RsAuth(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

}
