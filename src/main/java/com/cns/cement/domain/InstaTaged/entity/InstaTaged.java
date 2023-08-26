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


    @Builder
    public InstaTaged(String writer){
        this.writer = writer;
    }

}
