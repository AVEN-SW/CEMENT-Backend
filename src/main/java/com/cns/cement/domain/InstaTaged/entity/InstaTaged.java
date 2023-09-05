package com.cns.cement.domain.InstaTaged.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "insta_taged")
public class InstaTaged {

    @Id
    @GeneratedValue
    @Column(name = "feed_id")
    private Long feed_id;

    private String writer;
    private String created_At;
    private String file_url;
    private String feed_url;
    private String thumb_url;
    private String data_type;

    @Builder
    public InstaTaged(String writer, String file_url, String created_At,
                      String feed_url, String thumb_url,String data_type){
        this.writer = writer;
        this.file_url = file_url;
        this.created_At = created_At;
        this.feed_url = feed_url;
        this.thumb_url = thumb_url;
        this.data_type = data_type;
    }

}
