package com.cns.cement.domain.member.controller;

import com.cns.cement.domain.member.dto.*;
import com.cns.cement.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/members")
@RestController
public class MemberController {

    private final MemberService memberService;

    // 멤버 생성
    @PostMapping(consumes = {"multipart/form-data"})
    public MemberResponse addMember(@RequestPart("request") CreateMemberRequest request,
                                    @RequestPart("image") MultipartFile file) throws IllegalStateException, IOException {
        memberService.imgSave(file);
        return memberService.addMember(request);
    }

    // 멤버 전체 검색
    @GetMapping
    public List<MemberResponse> memberList() {
        return memberService.memberList();
    }

    // 멤버 검색 필터링 (email, name, phone, department)
    @GetMapping("/search")
    public List<MemberResponse> searchMember(@RequestBody SearchMemberRequest request) {
        return memberService.searchFilterMember(request.filter(), request.value());
    }

    // 멤버 정보 수정
    @PutMapping
    public MemberResponse modifyMember(@RequestBody ModifyMemberRequest request) {
        return memberService.modifyMember(request);
    }

    // 멤버 정보 삭제
    @DeleteMapping
    public void deleteMember(@RequestBody DeleteMemberRequest request) {
        memberService.deleteMember(request);
    }
}
