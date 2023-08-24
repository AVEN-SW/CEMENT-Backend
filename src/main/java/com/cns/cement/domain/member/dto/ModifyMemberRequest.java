package com.cns.cement.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ModifyMemberRequest {
    private Long id;
    private String name;
    private String phone;
    private String department;
    private String motto;
}
