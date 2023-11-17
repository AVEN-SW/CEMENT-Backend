package com.cns.cement.domain.InstaTaged.service;

import com.cns.cement.domain.InstaTaged.dto.InstaTagedFeedResponse;
import com.cns.cement.domain.InstaTaged.entity.InstaTaged;
import com.cns.cement.domain.InstaTaged.repository.InstaTagedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstaTagedService {

    private final InstaTagedRepository instaTagedRepository;

    public List<InstaTaged> findiAllnstaTagedFeed() {
        return instaTagedRepository.findAll();
    }
}