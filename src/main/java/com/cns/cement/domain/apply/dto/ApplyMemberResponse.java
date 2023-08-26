package com.cns.cement.domain.apply.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Builder
public class ApplyMemberResponse {
    private String email;
    private String username;
    private String phone;
    private String gender;
    private Integer age;
}
