package com.cns.cement.domain.member.controller;

import com.cns.cement.domain.member.dto.*;
import com.cns.cement.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    // 멤버 생성
    @PostMapping("/members")
    public MemberResponse addMember(@RequestBody CreateMemberRequest request) {
        return memberService.addMember(request);
    }

    // 멤버 전체 검색
    @GetMapping("/members")
    public List<MemberResponse> memberList() {
        return memberService.memberList();
    }

    // 멤버 검색 필터링 (email, name, phone, department)
    @GetMapping("/members/search")
    public List<MemberResponse> searchMember(@RequestBody SearchMemberRequest request) {
        return memberService.searchFilterMember(request.filter(), request.value());
    }

    // 멤버 정보 수정
    @PutMapping("/members")
    public MemberResponse modifyMember(@RequestBody ModifyMemberRequest request) {
        return memberService.modifyMember(request);
    }

    // 멤버 정보 삭제
    @DeleteMapping("/members")
    public void deleteMember(@RequestBody DeleteMemberRequest request) {
        memberService.deleteMember(request);
    }
}
