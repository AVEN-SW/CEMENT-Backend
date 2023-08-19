package com.cns.cement.domain.member.service;

import com.cns.cement.domain.member.dto.CreateMemberRequest;
import com.cns.cement.domain.member.dto.DeleteMemberRequest;
import com.cns.cement.domain.member.dto.MemberResponse;
import com.cns.cement.domain.member.dto.ModifyMemberRequest;
import com.cns.cement.domain.member.entity.Member;
import com.cns.cement.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.impl.FileUploadIOException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public void imgSave(MultipartFile imgFile) throws IOException {
        String saveFileName = UUID.randomUUID() + "_" + imgFile.getOriginalFilename();
        String saveUrl = System.getProperty("user.dir") + "/src/main/resources/profile_img";

        final File file = new File(saveUrl, saveFileName);
        imgFile.transferTo(file);
    }

    public MemberResponse addMember(CreateMemberRequest request) {

        Member saveMember = memberRepository.save(request.toEntity());

        return new MemberResponse(saveMember.getEmail(),
                                        saveMember.getName(),
                                        saveMember.getPhone(),
                                        saveMember.getDepartment(),
                                        saveMember.getMotto());
    }

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

    public List<MemberResponse> memberList() {
        return memberRepository.findAll().stream()
                .map(MemberResponse::of)
                .toList();
    }

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

    public void deleteMember(DeleteMemberRequest request) {
        memberRepository.deleteById(request.id());
    }
}
