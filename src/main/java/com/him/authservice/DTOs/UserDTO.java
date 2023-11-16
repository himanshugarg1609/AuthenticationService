package com.him.authservice.DTOs;

import com.him.authservice.models.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private String email;
    private String password;
    private String fullName;

    public User toUser() {
        return User.builder()
                .email(email)
                .password(password)
                .fullName(fullName)
                .build();
    }
}
