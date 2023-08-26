package com.cns.cement.domain.apply.dto;

import com.cns.cement.domain.apply.entity.ApplyMember;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

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
    @Setter
    private String file_name;
    private MultipartFile profile_image;

    public ApplyMember toEntity() {
        return ApplyMember.of(email, username, password, phone, gender, age, file_name);
    }
}
