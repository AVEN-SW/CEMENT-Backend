package com.cns.cement.domain.InstaTaged.controller;

import com.cns.cement.domain.InstaTaged.service.InstaTagedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;


@Controller
@RequiredArgsConstructor
public class InstaTagedController {

    private final InstaTagedService instaTAgedService;
}
