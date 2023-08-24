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
    private String email;
    private String name;
    private String phone;
    private String department;
    private String motto;
    private String file_name;


    public static MemberResponse of(Member member) {
        return new MemberResponse(member.getEmail(),
                member.getName(),
                member.getPhone(),
                member.getDepartment(),
                member.getMotto(),
                member.getFile_name());
    }
}