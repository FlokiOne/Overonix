package com.Overonix.Test.domain.location.usecase;

import com.Overonix.Test.data.entity.EUser;
import com.Overonix.Test.data.repository.UserRepository;
import com.Overonix.Test.presentation.dto.RsUserAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetUserLocationsUseCase {

    private final UserRepository userRepository;

    @Autowired
    public GetUserLocationsUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public RsUserAddress getAllUserAddress(String username) {
        EUser user = this.userRepository.findByUsernameWithAddress(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new RsUserAddress(user.getUsername(), user.getAddress());
    }

}
