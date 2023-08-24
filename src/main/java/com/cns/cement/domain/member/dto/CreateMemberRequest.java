package com.cns.cement.domain.member.dto;

import com.cns.cement.domain.member.entity.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.cns.cement.domain.member.entity.Member}
 */
@Getter
public class CreateMemberRequest {
    private String email;
    private String name;
    private String phone;
    private String department;
    private String motto;
    @Setter
    private String file_name;


    public Member toEntity() {
        return Member.of(email, name, phone, department, motto, file_name);
    }
}