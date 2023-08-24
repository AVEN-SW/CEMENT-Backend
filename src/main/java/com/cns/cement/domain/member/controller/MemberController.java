package com.cns.cement.domain.member.controller;

import com.cns.cement.domain.member.dto.*;
import com.cns.cement.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @GetMapping("/img")
    public ResponseEntity<Resource> imgSearch() throws IOException {
        Path path = Paths.get(System.getProperty("user.dir") + "/src/main/resources/profile_img/" + "df55b025-dfbb-4c7d-a72d-a326b94bd796_00721279bfe3d8893fc011083b9eb1ca.jpg");
        String contentType = Files.probeContentType(path);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(
                ContentDisposition.builder("attachment")
                        .filename("df55b025-dfbb-4c7d-a72d-a326b94bd796_00721279bfe3d8893fc011083b9eb1ca.jpg", StandardCharsets.UTF_8)
                        .build());
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);

        Resource resource = new InputStreamResource(Files.newInputStream(path));

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
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
