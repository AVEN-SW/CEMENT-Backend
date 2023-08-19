package com.cns.cement.domain.member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String email;
    private String name;
    private String phone;
    private String department;
    private String motto;
    @Enumerated(EnumType.STRING)
    private Authority role;
    //    private String img;
    @Enumerated(EnumType.STRING)
    private MemberStatus status;
    @CreatedDate
    private LocalDateTime createAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;



    private Member(String email, String name, String phone, String department, String motto) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.department = department;
        this.motto = motto;
        this.role = Authority.ROLE_MEMBER;
        this.status = MemberStatus.Suspended;
    }

    @Builder
    public static Member of(String email,
                            String name,
                            String phone,
                            String department,
                            String motto) {
        return new Member(email, name, phone, department, motto);
    }
}
