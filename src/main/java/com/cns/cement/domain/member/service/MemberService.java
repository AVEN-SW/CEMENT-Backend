package com.cns.cement.domain.member.service;

import com.cns.cement.domain.member.dto.CreateMemberRequest;
import com.cns.cement.domain.member.dto.CreateMemberResponse;
import com.cns.cement.domain.member.entity.Member;
import com.cns.cement.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public CreateMemberResponse addMember(CreateMemberRequest request) {
        Member saveMember = memberRepository.save(request.toEntity());
        return new CreateMemberResponse(saveMember.getEmail(),
                                        saveMember.getName(),
                                        saveMember.getPhone(),
                                        saveMember.getDepartment(),
                                        saveMember.getMotto());
    }
}
