package com.cns.cement.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
@Entity
public class Member implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String email;
    private String password;
    @Setter
    private String name;
    @Setter
    private String phone;
    @Setter
    private String department;
    @Setter
    private String motto;

    private String file_name;

    @Enumerated(EnumType.STRING)
    private Authority role;
    //    private String img;
    @Enumerated(EnumType.STRING)
    private MemberStatus status;
    @CreatedDate
    private LocalDateTime createAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;



    private Member(String email, String password, String name, String phone, String department, String motto, String file_name) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.department = department;
        this.motto = motto;
        this.file_name = file_name;
        this.role = Authority.ROLE_MEMBER;
        this.status = MemberStatus.Suspended;
    }

    @Builder
    public static Member of(String email,
                            String password,
                            String name,
                            String phone,
                            String department,
                            String motto,
                            String file_name) {
        return new Member(email, password, name, phone, department, motto, file_name);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.toString()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
