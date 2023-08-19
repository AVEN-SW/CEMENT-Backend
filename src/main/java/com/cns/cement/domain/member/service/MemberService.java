package com.cns.cement.domain.member.service;

import com.cns.cement.domain.member.dto.CreateMemberRequest;
import com.cns.cement.domain.member.dto.MemberResponse;
import com.cns.cement.domain.member.dto.ModifyMemberRequest;
import com.cns.cement.domain.member.entity.Member;
import com.cns.cement.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResponse addMember(CreateMemberRequest request) {
        Member saveMember = memberRepository.save(request.toEntity());
        return new MemberResponse(saveMember.getEmail(),
                                        saveMember.getName(),
                                        saveMember.getPhone(),
                                        saveMember.getDepartment(),
                                        saveMember.getMotto());
    }

    public List<MemberResponse> searchFilterMember(String filter, String value) {
        if (filter.equals("email")) {
            return memberRepository.findByEmailContaining(value).stream()
                    .map(MemberResponse::of)
                    .toList();
        } else if (filter.equals("name")) {
            return memberRepository.findByNameContaining(value).stream()
                    .map(MemberResponse::of)
                    .toList();
        } else if (filter.equals("phone")) {
            return memberRepository.findByPhoneContaining(value).stream()
                    .map(MemberResponse::of)
                    .toList();
        } else if (filter.equals("department")) {
            return memberRepository.findByDepartmentContaining(value).stream()
                    .map(MemberResponse::of)
                    .toList();
        } else {
            return null;
        }
    }

    public List<MemberResponse> memberList() {
        return memberRepository.findAll().stream()
                .map(MemberResponse::of)
                .toList();
    }

    public MemberResponse modifyMember(ModifyMemberRequest request) {
        Member findMember = memberRepository.findById(request.getId())
                .orElseThrow(() -> new IllegalArgumentException("Not Found MemeberId"));

        if (request.getName().isEmpty()) {
            findMember.setName(request.getName());
        }
        if (request.getPhone().isEmpty()) {
            findMember.setPhone(request.getPhone());
        }
        if (request.getDepartment().isEmpty()) {
            findMember.setDepartment(request.getDepartment());
        }
        if (request.getMotto().isEmpty()) {
            findMember.setMotto(request.getMotto());
        }

        return MemberResponse.of(memberRepository.save(findMember));
    }
}
