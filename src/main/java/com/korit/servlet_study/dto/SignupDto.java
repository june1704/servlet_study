package com.korit.servlet_study.dto;

import com.korit.servlet_study.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupDto {

    private String username;
    private String password;
    private String name;
    private String email;

    public User toUser() {
        return User.builder()
                .username(username)
                .password(BCrypt.hashpw(password, BCrypt.gensalt(10))) // BCrypt.gensalt(10) 복잡도(값이 커질수록 복잡해짐) 10:기본값
                .name(name)
                .email(email)
                .build();
    }
}
