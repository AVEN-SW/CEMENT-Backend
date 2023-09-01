package com.cns.cement.domain.InstaTaged.controller;

import com.cns.cement.domain.InstaTaged.entity.InstaTaged;
import com.cns.cement.domain.InstaTaged.service.InstaTagedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/insta")
public class InstaTagedController {

    private final InstaTagedService instaTAgedService;

    @GetMapping
    public List<InstaTaged> searchInsta(){
        return null;
    }
}
