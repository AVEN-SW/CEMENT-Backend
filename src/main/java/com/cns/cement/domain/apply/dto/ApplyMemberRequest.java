package com.cns.cement.domain.apply.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApplyMemberRequest {
    private String email;
    private String username;
    private String password;
    private String confirmPassword;
    private String phone;
    private String gender;
    private Integer age;
}
