package com.cns.cement.domain.member.service;

import com.cns.cement.domain.member.dto.CreateMemberRequest;
import com.cns.cement.domain.member.dto.DeleteMemberRequest;
import com.cns.cement.domain.member.dto.MemberResponse;
import com.cns.cement.domain.member.dto.ModifyMemberRequest;
import com.cns.cement.domain.member.entity.Member;
import com.cns.cement.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.impl.FileUploadIOException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    // image 저장 메서드 - 저장 위치 resources/static/profile_img
    public String imgSave(MultipartFile imgFile) throws IOException {
        String saveFileName = UUID.randomUUID() + "_" + imgFile.getOriginalFilename();
        String saveUrl = System.getProperty("user.dir") + "/src/main/resources/static/profile_img";

        final File file = new File(saveUrl, saveFileName);
        imgFile.transferTo(file);
        return saveFileName;
    }

    // 계정 신청 승인 후 멤버 등록
    public MemberResponse addMember(CreateMemberRequest request, MultipartFile file) throws IOException {
        String file_name = imgSave(file);
        request.setFile_name(file_name);
        Member saveMember = memberRepository.save(request.toEntity());

        return new MemberResponse(saveMember.getEmail(),
                                        saveMember.getName(),
                                        saveMember.getPhone(),
                                        saveMember.getDepartment(),
                                        saveMember.getMotto(),
                                        saveMember.getFile_name());
    }

    // search filter
    public List<MemberResponse> searchFilterMember(String filter, String value) {
        return switch (filter) {
            case "email" -> memberRepository.findByEmailContaining(value).stream()
                    .map(MemberResponse::of)
                    .toList();
            case "name" -> memberRepository.findByNameContaining(value).stream()
                    .map(MemberResponse::of)
                    .toList();
            case "phone" -> memberRepository.findByPhoneContaining(value).stream()
                    .map(MemberResponse::of)
                    .toList();
            case "department" -> memberRepository.findByDepartmentContaining(value).stream()
                    .map(MemberResponse::of)
                    .toList();
            default -> null;
        };
    }

    // 가입된 Member List 반환
    public List<MemberResponse> memberList() {
        return memberRepository.findAll().stream()
                .map(MemberResponse::of)
                .toList();
    }

    // Member 정보 수정
    public MemberResponse modifyMember(ModifyMemberRequest request) {
        Member findMember = memberRepository.findById(request.getId())
                .orElseThrow(() -> new IllegalArgumentException("Not Found MemeberId"));

        if (request.getName().isEmpty()) {
            findMember.setName(request.getName());
        }
        if (request.getPhone().isEmpty()) {
            findMember.setPhone(request.getPhone());
        }
        if (request.getDepartment().isEmpty()) {
            findMember.setDepartment(request.getDepartment());
        }
        if (request.getMotto().isEmpty()) {
            findMember.setMotto(request.getMotto());
        }

        return MemberResponse.of(memberRepository.save(findMember));
    }

    // Member 삭제
    public void deleteMember(DeleteMemberRequest request) {
        memberRepository.deleteById(request.id());
    }

    // 멤버 이미지 수정
    public void imgModify(Long id, MultipartFile file) {
        try {
            Member member = memberRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Not Found Member"));
            member.setFile_name(imgSave(file));
            memberRepository.save(member);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 멤버 이미지 조회
    public ResponseEntity<Resource> imgSearch(String img) throws IOException {


        Path path = Paths.get(System.getProperty("user.dir") + "/src/main/resources/static/profile_img/" + img);
        String contentType = Files.probeContentType(path);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(
                ContentDisposition.builder("attachment")
                        .filename( img + ".jpg", StandardCharsets.UTF_8)
                        .build());
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);

        Resource resource = new InputStreamResource(Files.newInputStream(path));


        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}
