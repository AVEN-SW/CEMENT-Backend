package com.cns.cement.domain.InstaTaged.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "Insta_Taged")
public class InstaTaged {

    @Id
    @GeneratedValue
    @Column(name = "feed_id")
    private Long feed_id;
    
}
