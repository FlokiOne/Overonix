package com.Overonix.Test.domain.user;


import com.Overonix.Test.domain.user.usecase.AuthUserUseCase;
import com.Overonix.Test.domain.user.usecase.SaveUserUseCase;
import com.Overonix.Test.presentation.dto.RqAuth;
import com.Overonix.Test.presentation.dto.RqRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final SaveUserUseCase saveUserUseCase;
    private final AuthUserUseCase authUserUseCase;

    @Autowired
    public UserService(SaveUserUseCase saveUserUseCase, AuthUserUseCase authUserUseCase) {
        this.saveUserUseCase = saveUserUseCase;
        this.authUserUseCase = authUserUseCase;
    }

    public ResponseEntity<?> save(RqRegistration rqRegistration) {
        return this.saveUserUseCase.saveUser(rqRegistration);
    }

    public ResponseEntity<?> auth(RqAuth rqAuth) {
        return this.authUserUseCase.authenticateUser(rqAuth);
    }
}
