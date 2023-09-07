package com.cns.cement.domain.apply.controller;

import com.cns.cement.domain.apply.dto.AcceptApplyRequest;
import com.cns.cement.domain.apply.dto.ApplyMemberRequest;
import com.cns.cement.domain.apply.dto.ApplyMemberResponse;
import com.cns.cement.domain.apply.dto.RefuseApplyRequest;
import com.cns.cement.domain.apply.entity.ApplyMember;
import com.cns.cement.domain.apply.service.ApplyMemberService;
import com.cns.cement.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;

// TODO: REST API 로 변경 해야함
// TODO: API Spec. 논의는 프론트 팀과 협의 필요
@RequiredArgsConstructor
@Controller
public class ApplyMemberController {

    private final ApplyMemberService applyMemberService;

    // 멤버 계정 신청 리스트 반환
    @GetMapping("/apply-member")
    public List<ApplyMemberResponse> applyMemberList() {
        return applyMemberService.applyMemberList();
    }

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
        return "redirect:/apply-member";
    }

    // 계정 거절
    @DeleteMapping("/apply-member")
    public String refuseApply(@RequestBody RefuseApplyRequest request) {
        applyMemberService.refuseApply(request);

        return "redirect:/apply-member";
    }
}
