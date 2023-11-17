package com.cns.cement.domain.apply.dto;

import com.cns.cement.domain.apply.entity.ApplyMember;

import java.time.LocalDateTime;

/**
 * DTO for {@link com.cns.cement.domain.apply.entity.ApplyMember}
 */
public record ApplyMemberResponse(
        String email,
        String name,
        String phone,
        String gender,
        Integer age,
        String file_name,
        LocalDateTime applyAt
) {
    public static ApplyMemberResponse of(ApplyMember applyMember) {
        return new ApplyMemberResponse(
                applyMember.getEmail(),
                applyMember.getName(),
                applyMember.getPhone(),
                applyMember.getGender(),
                applyMember.getAge(),
                applyMember.getFile_name(),
                applyMember.getApplyAt());
    }
}