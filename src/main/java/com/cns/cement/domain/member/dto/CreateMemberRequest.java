package com.cns.cement.domain.member.dto;

import com.cns.cement.domain.member.entity.Member;
import lombok.Getter;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.cns.cement.domain.member.entity.Member}
 */
@Getter
public class CreateMemberRequest {
    String email;
    String name;
    String phone;
    String department;
    String motto;

    public Member toEntity() {
        return Member.of(email, name, phone, department, motto);
    }
}