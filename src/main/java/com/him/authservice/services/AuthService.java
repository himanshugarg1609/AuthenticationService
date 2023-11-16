package com.him.authservice.services;

import com.him.authservice.repositories.SessionRepository;
import com.him.authservice.repositories.UserRepository;

import com.him.authservice.Exceptions.InvalidCredentialsException;
import com.him.authservice.models.Session;
import com.him.authservice.models.User;
import com.him.authservice.utils.GenerateToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepo;
    private final SessionRepository sessionRepo;

    public void signUp(User user) throws InvalidCredentialsException {
        if(userRepo.existsByEmail(user.getEmail())) {
            throw new InvalidCredentialsException("User with same email already exists, please login");
        }
        userRepo.save(user);
    }

    public String login(String email, String password) throws InvalidCredentialsException {
        User user = userRepo.findByEmailAndPassword(email, password);
        if (user == null) {
            throw new InvalidCredentialsException("Invalid email or password provided");
        }
        String token = GenerateToken.generate();
        sessionRepo.save(Session.builder()
                .token(token)
                .user(user)
                .build());
        return token;
    }

    public boolean validate(String token) {
        return sessionRepo.existsByToken(token);
    }

}
