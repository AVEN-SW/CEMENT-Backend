package com.cns.cement.domain.member.controller;

import com.cns.cement.domain.member.entity.Member;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class MemberViewController {

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/member-list")
    public String memberList() {
        return "member-list";
    }

    @GetMapping("/apply-member")
    public String applyMember() {
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
