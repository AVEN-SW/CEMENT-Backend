package com.cns.cement.domain.InstaTaged.service;

import com.cns.cement.domain.InstaTaged.entity.InstaTaged;
import com.cns.cement.domain.InstaTaged.repository.InstaTagedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InstaTagedService {

    private final InstaTagedRepository instaTagedRepository;


}
