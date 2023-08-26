package com.cns.cement.domain.apply.service;

import com.cns.cement.domain.apply.dto.ApplyMemberRequest;
import com.cns.cement.domain.apply.dto.ApplyMemberResponse;
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

import java.util.List;
import java.util.Optional;

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
            body.append("<html> <body><h1>CEMENT " + request.getUsername() + "님의 멤버 계정 신청이 정상적으로 진행되었습니다. </h1>");
            body.append("<hr>");
            body.append("<h2>신청 정보</h2><br>");
            body.append("<h3>");
            if (!emailValid) {
                // 중복된 경우
                body.append("<div style='color: red'>이메일: " + request.getEmail() + " [중복된 이메일입니다. 다시 신청해주세요.]</div>");
            } else {
                // 중복 X
                body.append("이메일: " + request.getEmail());
            }
            body.append("<br>이름: " + request.getUsername());
            body.append("<br>전화번호: " + request.getPhone());
            body.append("<br>성별: " + request.getGender());
            body.append("<br>나이: " + request.getAge().toString());
            body.append("</h3>");
            body.append("<hr>");
            body.append("<h4>계정이 승인되면 다시 메일로 알려드리겠습니다. 감사합니다!</h4></body></html>");

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

        ApplyMember applyMember = applyMemberRepository.save(request.toEntity());
        return ApplyMemberResponse.builder()
                .email(applyMember.getEmail())
                .username(applyMember.getName())
                .phone(applyMember.getPhone())
                .gender(applyMember.getGender())
                .age(applyMember.getAge())
                .build();
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
}
