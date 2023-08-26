package com.cns.cement.domain.apply.dto;

import com.cns.cement.domain.apply.entity.ApplyMember;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class ApplyMemberRequest {
    private String email;
    private String username;
    @Setter
    private String password;
    private String confirmPassword;
    private String phone;
    private String gender;
    private Integer age;
    private String file_name;

    public ApplyMember toEntity() {
        return ApplyMember.of(email, username, password, phone, gender, age, file_name);
    }
}
