package com.cns.cement.domain.member.dto;

import com.cns.cement.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * DTO for {@link com.cns.cement.domain.member.entity.Member}
 */
@AllArgsConstructor
@Getter
public class MemberResponse {
    String email;
    String name;
    String phone;
    String department;
    String motto;

    public static MemberResponse of(Member member) {
        return new MemberResponse(member.getEmail(),
                member.getName(),
                member.getPhone(),
                member.getDepartment(),
                member.getMotto());
    }
}