package com.Overonix.Test.domain.user.usecase;

import com.Overonix.Test.data.entity.ERole;
import com.Overonix.Test.data.entity.EUser;
import com.Overonix.Test.data.entity.RoleType;
import com.Overonix.Test.data.repository.RoleRepository;
import com.Overonix.Test.data.repository.UserRepository;
import com.Overonix.Test.presentation.dto.RqRegistration;
import com.Overonix.Test.presentation.dto.RsMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class SaveUserUseCase {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SaveUserUseCase(
            PasswordEncoder passwordEncoder,
            RoleRepository roleRepository,
            UserRepository userRepository
    ) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> saveUser(RqRegistration rqRegistration) {
        if (userRepository.existsByUsername(rqRegistration.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new RsMessage("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(rqRegistration.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new RsMessage("Error: Email is already in use!"));
        }

        EUser eUser = new EUser(
                rqRegistration.getUsername(),
                rqRegistration.getEmail(),
                passwordEncoder.encode(rqRegistration.getPassword()));

        Set<String> strRoles = rqRegistration.getRole();
        Set<ERole> roles = new HashSet<>();

        if (strRoles == null || strRoles.isEmpty()) {
            ERole userRole = roleRepository.findByName(RoleType.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        ERole adminRole = roleRepository.findByName(RoleType.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    default:
                        ERole userRole = roleRepository.findByName(RoleType.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        eUser.setRoles(roles);
        userRepository.save(eUser);

        return ResponseEntity.ok(new RsMessage("User registered successfully!"));
    }
}
