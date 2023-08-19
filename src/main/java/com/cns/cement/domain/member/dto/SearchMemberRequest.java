package com.cns.cement.domain.member.dto;

public record SearchMemberRequest(
        String filter,
        String value
) {
}
