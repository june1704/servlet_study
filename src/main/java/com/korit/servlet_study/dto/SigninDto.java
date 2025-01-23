package com.korit.servlet_study.dto;

import com.korit.servlet_study.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SigninDto {
    private String username;
    private String password;

    public User toUser() {
        return User.builder()
                .username(username)
                .password(password)
                .build();
    }
}
