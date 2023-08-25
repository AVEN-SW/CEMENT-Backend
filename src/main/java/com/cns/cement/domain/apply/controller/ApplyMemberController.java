package com.cns.cement.domain.apply.controller;

import com.cns.cement.domain.apply.dto.ApplyMemberRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplyMemberController {

    // form으로 데이터를 묶어서 들어오는 경우 @RequestBody 어노테이션이 없어야 정상 작동함
    @PostMapping("/register")
    public void applyMember(ApplyMemberRequest request) {
        System.out.println(request.toString());
    }
}
