package com.cns.cement.domain.member.dto;

import com.cns.cement.domain.member.entity.Member;

import java.io.Serializable;

/**
 * DTO for {@link com.cns.cement.domain.member.entity.Member}
 */
public record MemberSessionDataResponse(
        Long id,
        String email,
        String name,
        String file_name
) {

    public static MemberSessionDataResponse of(Member member) {
        return new MemberSessionDataResponse(member.getId(),
                                                member.getEmail(),
                                                member.getName(),
                                                member.getFile_name());
    }
}