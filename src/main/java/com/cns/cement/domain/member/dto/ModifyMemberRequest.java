package com.cns.cement.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ModifyMemberRequest {
    Long id;
    String name;
    String phone;
    String department;
    String motto;
}
