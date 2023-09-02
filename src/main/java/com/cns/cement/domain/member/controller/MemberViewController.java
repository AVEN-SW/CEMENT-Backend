package com.cns.cement.domain.member.controller;

import com.cns.cement.domain.apply.entity.ApplyMember;
import com.cns.cement.domain.apply.service.ApplyMemberService;
import com.cns.cement.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Controller
public class MemberViewController {

    private final ApplyMemberService applyMemberService;

    @GetMapping("/")
    public String indexPage(Model model, Authentication authentication) {
        Member member = (Member) authentication.getPrincipal();
        model.addAttribute("member", member);
        return "index";
    }

    @GetMapping("/member-list")
    public String memberList(Model model, Authentication authentication) {
        Member member = (Member) authentication.getPrincipal();
        model.addAttribute("member", member);
        return "member-list";
    }

    @GetMapping("/apply-member")
    public String applyMember(Model model, Authentication authentication) {
        Member member = (Member) authentication.getPrincipal();
        List<ApplyMember> applyMembers = applyMemberService.applyMemberList();
        model.addAttribute("member", member);
        model.addAttribute("applyMembers", applyMembers);

        return "apply-member";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

}
