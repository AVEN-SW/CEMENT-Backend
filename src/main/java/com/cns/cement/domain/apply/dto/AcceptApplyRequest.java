package com.cns.cement.domain.apply.dto;

/**
 * DTO for {@link com.cns.cement.domain.apply.entity.ApplyMember}
 */
public record AcceptApplyRequest(Long id,
                                 String department) {
}