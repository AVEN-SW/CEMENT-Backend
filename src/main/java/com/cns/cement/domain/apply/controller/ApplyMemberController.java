package com.cns.cement.domain.apply.controller;

import com.cns.cement.domain.apply.dto.AcceptApplyRequest;
import com.cns.cement.domain.apply.dto.ApplyMemberRequest;
import com.cns.cement.domain.apply.dto.ApplyMemberResponse;

import com.cns.cement.domain.apply.service.ApplyMemberService;
import com.cns.cement.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.Media;
import java.util.List;

// TODO: REST API 로 변경 해야함
// TODO: API Spec. 논의는 프론트 팀과 협의 필요
@RequiredArgsConstructor
@RestController
public class ApplyMemberController {

    private final ApplyMemberService applyMemberService;

    // 멤버 계정 신청 리스트 반환
    @GetMapping("/apply-member")
    public List<ApplyMemberResponse> applyMemberList() {
        return applyMemberService.applyMemberList();
    }

    // form으로 데이터를 묶어서 들어오는 경우 @RequestBody 어노테이션이 없어야 정상 작동함
    @PostMapping(value = "/register", consumes = {"multipart/form-data"})
    public ApplyMemberResponse applyMember(ApplyMemberRequest request) {

        return applyMemberService.addApplyMember(request);
    }
}
