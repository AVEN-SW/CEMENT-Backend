package com.cns.cement.domain.apply.controller;

import com.cns.cement.domain.apply.dto.ApplyMemberRequest;
import com.cns.cement.domain.apply.service.ApplyMemberService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.File;
import java.io.UnsupportedEncodingException;

@RequiredArgsConstructor
@Controller
public class ApplyMemberController {

    private final ApplyMemberService applyMemberService;
    private final JavaMailSender javaMailSender;

    // form으로 데이터를 묶어서 들어오는 경우 @RequestBody 어노테이션이 없어야 정상 작동함
    @PostMapping("/register")
    public String applyMember(ApplyMemberRequest request) throws MessagingException, UnsupportedEncodingException {

        try {
            String to = request.getEmail();
            String from = "cement.apply@gmail.com";
            String subject = "[CEMENT] " + request.getUsername() + "님의 멤버 계정 신청";

            StringBuilder body = new StringBuilder();
            body.append("<html> <body><h1>CEMENT " + request.getUsername() + "님의 멤버 계정 신청이 정상적으로 진행되었습니다. </h1>");
            body.append("<div></div>계정이 승인되면 다시 메일로 알려드리겠습니다. 감사합니다!</body></html>");

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true, "UTF-8");

            mimeMessageHelper.setFrom(from,"cement.apply");
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body.toString(), true);

    //        FileSystemResource fileSystemResource = new FileSystemResource(new File("C:/Users/HOME/Desktop/test.txt"));
    //        mimeMessageHelper.addAttachment("또르르.txt", fileSystemResource);

    //        FileSystemResource file = new FileSystemResource(new File("C:/Users/HOME/Desktop/flower.jpg"));
    //        mimeMessageHelper.addInline("flower.jpg", file);

            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        applyMemberService.addApplyMember(request);
        return "redirect:/login";
    }
}
