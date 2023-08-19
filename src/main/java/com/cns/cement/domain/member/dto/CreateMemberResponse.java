package com.cns.cement.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.cns.cement.domain.member.entity.Member}
 */
@AllArgsConstructor
@Getter
public class CreateMemberResponse {
    String email;
    String name;
    String phone;
    String department;
    String motto;
}