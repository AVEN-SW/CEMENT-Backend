package com.cns.cement.domain.member.controller;

import com.cns.cement.domain.member.dto.CreateMemberRequest;
import com.cns.cement.domain.member.dto.MemberResponse;
import com.cns.cement.domain.member.dto.SearchMemberRequest;
import com.cns.cement.domain.member.entity.Member;
import com.cns.cement.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member")
    public MemberResponse addMember(@RequestBody CreateMemberRequest request) {
        return memberService.addMember(request);
    }

    @GetMapping("/member")
    public List<MemberResponse> memberList() {
        return memberService.memberList();
    }

    @GetMapping("/member/search")
    public List<MemberResponse> searchMember(@RequestBody SearchMemberRequest request) {
        return memberService.searchFilterMember(request.filter(), request.value());
    }
}
