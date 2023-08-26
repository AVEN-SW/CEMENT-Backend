package com.cns.cement.domain.apply.entity;

import com.cns.cement.domain.member.entity.Authority;
import com.cns.cement.domain.member.entity.Member;
import com.cns.cement.domain.member.entity.MemberStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "apply_member")
@Entity
public class ApplyMember {

    @Id
    @GeneratedValue
    @Column(name = "apply_member_id")
    private Long id;

    @Column(unique = true)
    private String email;
    private String name;
    private String password;
    private String phone;
    private String gender;
    private Integer age;
    private String file_name;

    @Enumerated(EnumType.STRING)
    private ApplyStatus status;
    @CreatedDate
    private LocalDateTime applyAt;



    private ApplyMember(String email,
                        String name,
                        String password,
                        String phone,
                        String gender,
                        Integer age,
                        String file_name) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.gender = gender;
        this.age = age;
        this.file_name = file_name;
        this.status = ApplyStatus.PENDING;
        this.applyAt = LocalDateTime.now();
    }

    @Builder
    public static ApplyMember of(String email,
                                 String name,
                                 String password,
                                 String phone,
                                 String gender,
                                 Integer age,
                                 String file_name) {
        return new ApplyMember(email, name, password, phone, gender, age, file_name);
    }
}
