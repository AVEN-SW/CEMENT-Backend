package com.cns.cement.domain.member.controller;

import com.cns.cement.domain.member.dto.*;
import com.cns.cement.domain.member.entity.Member;
import com.cns.cement.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import org.springframework.http.HttpHeaders;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/member")
@RestController
public class MemberController {

    private final MemberService memberService;

    // 멤버 생성
    @PostMapping(consumes = {"multipart/form-data"})
    public MemberResponse addMember(@RequestPart("request") CreateMemberRequest request,
                                    @RequestPart("image") MultipartFile file) throws IllegalStateException, IOException {
        return memberService.addMember(request, file);
    }

    // 이미지 조회
    // Reactor: Controller 영역에 Business Logic 이 많이 들어가 있음 Service 코드로 이동
    @GetMapping("/img")
    public ResponseEntity<Resource> imgSearch(@RequestParam("img") String img) throws IOException {
        return memberService.imgSearch(img);
    }

    // 멤버 전체 검색
    @GetMapping("/list")
    public List<MemberResponse> memberList() {
        return memberService.memberList();
    }

    // Login Session Data Return
    @GetMapping
    public MemberSessionDataResponse sessionMemberData(Authentication authentication) {
        Member member = (Member) authentication.getPrincipal();
        return MemberSessionDataResponse.of(member);
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

    // 이미지 수정
    @PutMapping("/img/{id}")
    public void imgModify(@PathVariable("id")Long id,
                          @RequestBody MultipartFile file) {
        memberService.imgModify(id, file);
    }

}
