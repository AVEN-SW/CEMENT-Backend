package com.cns.cement.domain.member.controller;

import com.cns.cement.domain.apply.entity.ApplyMember;
import com.cns.cement.domain.apply.service.ApplyMemberService;
import com.cns.cement.domain.member.dto.MemberResponse;
import com.cns.cement.domain.member.dto.MemberSessionDataResponse;
import com.cns.cement.domain.member.entity.Member;
import com.cns.cement.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@RestController
public class MemberViewController {

    private final ApplyMemberService applyMemberService;
    private final MemberService memberService;

    // Root Page -> 로그인 중인 멤버 데이터 보내주기
//    @GetMapping("/")
//    public String indexPage(Model model, Authentication authentication) {
//        Member member = (Member) authentication.getPrincipal();
//        List<Member> memberList = memberService.memberDomainList();
//
//        model.addAttribute("member", member);
//        model.addAttribute("memberList", memberList);
//        return "index";
//    }



//    // 전체 멤버 리스트 반환해주기
//    @GetMapping("/member-list")
//    public String memberList(Model model, Authentication authentication) {
//        Member member = (Member) authentication.getPrincipal();
//        model.addAttribute("member", member);
//        return "member-list";
//    }

    // 계정 신청 멤버 리스트 반환해주기
//    @GetMapping("/apply-member")
//    public String applyMember(Model model, Authentication authentication) {
//        Member member = (Member) authentication.getPrincipal();
//        List<ApplyMember> applyMembers = applyMemberService.applyMemberList();
//        model.addAttribute("member", member);
//        model.addAttribute("applyMembers", applyMembers);
//
//        return "apply-member";
//    }

    // Login Page 반환이라 Deprecate
//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }
//
//    @GetMapping("/register")
//    public String register() {
//        return "register";
//    }

}
