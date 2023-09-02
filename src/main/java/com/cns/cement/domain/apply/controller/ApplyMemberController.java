package com.cns.cement.domain.apply.controller;

import com.cns.cement.domain.apply.dto.AcceptApplyRequest;
import com.cns.cement.domain.apply.dto.ApplyMemberRequest;
import com.cns.cement.domain.apply.dto.RefuseApplyRequest;
import com.cns.cement.domain.apply.service.ApplyMemberService;
import com.cns.cement.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;

@RequiredArgsConstructor
@Controller
public class ApplyMemberController {

    private final ApplyMemberService applyMemberService;

    // form으로 데이터를 묶어서 들어오는 경우 @RequestBody 어노테이션이 없어야 정상 작동함
    @PostMapping(value = "/register", consumes = {"multipart/form-data"})
    public String applyMember(ApplyMemberRequest request) {

        applyMemberService.addApplyMember(request);
        return "redirect:/login";
    }

    // 계정 승인
    @PostMapping("/apply-member")
    public String acceptApply(@RequestBody AcceptApplyRequest request) {
        // ApplyMember 도메인에서 PK만 받아서 정보 가져오기
        // Service에서 Member 도메인으로 Cascading 후에 부서 변경
        applyMemberService.acceptApply(request);
        // TODO: /apply-member api 로 redirect 됐을때 현재 세션에 로그인 되어있는 멤버 정보가 넘어가는지 확인 필요
        return "redirect:/apply-member";
    }

    // 계정 거절
    @DeleteMapping("/apply-member")
    public String refuseApply(@RequestBody RefuseApplyRequest request) {
        applyMemberService.refuseApply(request);

        return "redirect:/apply-member";
    }
}
