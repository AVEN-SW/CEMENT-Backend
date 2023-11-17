package com.cns.cement.domain.apply.service;

import com.cns.cement.domain.apply.dto.AcceptApplyRequest;
import com.cns.cement.domain.apply.dto.ApplyMemberRequest;
import com.cns.cement.domain.apply.dto.ApplyMemberResponse;
import com.cns.cement.domain.apply.dto.RefuseApplyRequest;
import com.cns.cement.domain.apply.entity.ApplyMember;
import com.cns.cement.domain.apply.repository.ApplyMemberRepository;
import com.cns.cement.domain.member.entity.Member;
import com.cns.cement.domain.member.repository.MemberRepository;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ApplyMemberService {

    private final ApplyMemberRepository applyMemberRepository;
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JavaMailSender javaMailSender;

    public ApplyMemberResponse addApplyMember(ApplyMemberRequest request) {
        boolean emailValid = emailValidation(request.getEmail());

        try {
            String to = request.getEmail();
            String from = "cement.apply@gmail.com";
            String subject = "[CEMENT] " + request.getUsername() + "님의 멤버 계정 신청";

            StringBuilder body = new StringBuilder();
            if (emailValid) {
                // 중복 X
                body.append("<html> <body><h1>CEMENT " + request.getUsername() + "님의 멤버 계정 신청이 정상적으로 진행되었습니다. </h1>");
            } else {
                // 중복된 경우
                body.append("<html> <body><h1>CEMENT " + request.getUsername() + "님의 멤버 계정 신청이 거부되었습니다. </h1>");
            }
            body.append("<hr>");
            body.append("<h2>신청 정보</h2><br>");
            body.append("<h3>");
            if (emailValid) {
                // 중복 X
                body.append("이메일: " + request.getEmail());
            } else {
                // 중복된 경우
                body.append("<div style='color: red'>이메일: " + request.getEmail() + " [중복된 이메일입니다]</div>");
            }
            body.append("<br>이름: " + request.getUsername());
            body.append("<br>전화번호: " + request.getPhone());
            body.append("<br>성별: " + request.getGender());
            body.append("<br>나이: " + request.getAge().toString());
            body.append("</h3>");
            body.append("<hr>");
            if (emailValid) {
                body.append("<h4>계정이 승인되면 다시 메일로 알려드리겠습니다. 감사합니다!</h4></body></html>");
            } else {
                body.append("<h4 style='color: red'>이미 신청된 이메일이거나 사용중인 이메일입니다. 다른 이메일로 다시 신청해주세요.</h4></body></html>");
            }

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true, "UTF-8");

            mimeMessageHelper.setFrom(from,"cement.apply");
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body.toString(), true);

            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (!emailValid) {
            return null;
        }
        // 비밀번호 암호화 후 저장
        request.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));

        // 이미지 저장
        try {
            String saveFileName = UUID.randomUUID() + "_" + request.getProfile_image().getOriginalFilename();
            String saveUrl = System.getProperty("user.dir") + "/src/main/resources/static/profile_img";

            final File file = new File(saveUrl, saveFileName);
            request.getProfile_image().transferTo(file);
            request.setFile_name(saveFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }


        ApplyMember applyMember = applyMemberRepository.save(request.toEntity());
        return ApplyMemberResponse.of(applyMember);
    }

    // 이메일 중복 확인
    private boolean emailValidation(String email) {

        // 지원자 목록 중에서 중복 확인
        Optional<ApplyMember> applyMember = applyMemberRepository.findByEmail(email);
        if (applyMember.isPresent()) {
            return false;
        }

        // 등록된 멤버 중에서 중복 확인
        Optional<Member> registeredMember = memberRepository.findByEmail(email);
        if (registeredMember.isPresent()) {
            return false;
        }

        return true;
    }

    public List<ApplyMemberResponse> applyMemberList() {
        List<ApplyMember> applyMemberList = applyMemberRepository.findAll();
        return applyMemberList.stream()
                .map(ApplyMemberResponse::of)
                .toList();
    }

    // 계정 승인
    public Member acceptApply(AcceptApplyRequest request) {
        // ID로 지원자 정보확인
        ApplyMember applyMember = applyMemberRepository.findById(request.id())
                .orElseThrow(() -> new IllegalArgumentException("Not Found ApplyMember"));

        // Member 도메인으로 변환 후  부서 정보 추가
        Member member = applyMember.toEntity();
        member.setDepartment(request.department());
        // Member 저장
        Member saveMember = memberRepository.save(member);


        // 지원자 정보에서 삭제
        // TODO: 지원자 데이터에서 승인, 삭제 기준으로 delete column 추가 예정
        applyMemberRepository.deleteById(request.id());

        // 계정 승인 메일 발송
        try {
            String to = member.getEmail();
            String from = "cement.apply@gmail.com";
            String subject = "[CEMENT] " + member.getName() + "님의 멤버 계정 신청 승인";

            StringBuilder body = new StringBuilder();
            body.append("<hr>");
            body.append("<h2>계정 정보</h2><br>");
            body.append("<h3>");
            body.append("이메일: " + member.getEmail());
            body.append("<br>이름: " + member.getName());
            body.append("<br>전화번호: " + member.getPhone());
            body.append("<br>부서: " + member.getDepartment());
            body.append("<br>성별: " + applyMember.getGender());
            body.append("<br>나이: " + applyMember.getAge().toString());
            body.append("</h3>");
            body.append("<hr>");
            body.append("<h4 style='color: green'>계정이 승인됐습니다! 가입하신 계정으로 로그인 후 이용가능합니다. 감사합니다!</h4></body></html>");

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true, "UTF-8");

            mimeMessageHelper.setFrom(from,"cement.apply");
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body.toString(), true);

            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return saveMember;
    }

    public void refuseApply(RefuseApplyRequest request) {
        ApplyMember applyMember = applyMemberRepository.findById(request.id())
                .orElseThrow(() -> new IllegalArgumentException("Not Found ApplyMember"));

        applyMemberRepository.delete(applyMember);

        // 계정 승인 메일 발송
        try {
            String to = applyMember.getEmail();
            String from = "cement.apply@gmail.com";
            String subject = "[CEMENT] " + applyMember.getName() + "님의 멤버 계정 신청 거절";

            StringBuilder body = new StringBuilder();
            body.append("<hr>");
            body.append("<h2>신청 정보</h2><br>");
            body.append("<h3>");
            body.append("이메일: " + applyMember.getEmail());
            body.append("<br>이름: " + applyMember.getName());
            body.append("<br>전화번호: " + applyMember.getPhone());
            body.append("<br>성별: " + applyMember.getGender());
            body.append("<br>나이: " + applyMember.getAge().toString());
            body.append("</h3>");
            body.append("<hr>");
            body.append("<h4 style='color: red'>계정이 승인이 거부됐습니다.</h4></body></html>");

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true, "UTF-8");

            mimeMessageHelper.setFrom(from,"cement.apply");
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body.toString(), true);

            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
