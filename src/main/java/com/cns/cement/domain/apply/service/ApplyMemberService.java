package com.cns.cement.domain.apply.service;

import com.cns.cement.domain.apply.dto.ApplyMemberRequest;
import com.cns.cement.domain.apply.dto.ApplyMemberResponse;
import com.cns.cement.domain.apply.entity.ApplyMember;
import com.cns.cement.domain.apply.repository.ApplyMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ApplyMemberService {

    private final ApplyMemberRepository applyMemberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public ApplyMemberResponse addApplyMember(ApplyMemberRequest request) {
        // 비밀번호 암호화 후 저장
        request.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));

        ApplyMember applyMember = applyMemberRepository.save(request.toEntity());
        return ApplyMemberResponse.builder()
                .email(applyMember.getEmail())
                .username(applyMember.getName())
                .phone(applyMember.getPhone())
                .gender(applyMember.getGender())
                .age(applyMember.getAge())
                .build();
    }
}
