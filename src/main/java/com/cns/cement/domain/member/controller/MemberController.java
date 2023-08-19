package com.cns.cement.domain.member.controller;

import com.cns.cement.domain.member.dto.CreateMemberRequest;
import com.cns.cement.domain.member.dto.CreateMemberResponse;
import com.cns.cement.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member")
    public CreateMemberResponse addMember(@RequestBody CreateMemberRequest request) {
        return memberService.addMember(request);
    }
}
