package com.cns.cement.domain.InstaTaged.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class InstaTagedFeedResponse {

    private Long feed_id;
    private String writer;
    private String created_At;
    private String file_url;
    private String feed_url;
    private String thumb_url;
    private String data_type;
}
