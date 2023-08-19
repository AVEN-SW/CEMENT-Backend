package com.cns.cement.domain.member.repository;

import com.cns.cement.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByEmail(String email);
    List<Member> findByEmailContaining(String email);
    List<Member> findByName(String name);
    List<Member> findByNameContaining(String name);
    List<Member> findByPhone(String phone);
    List<Member> findByPhoneContaining(String phone);
    List<Member> findByDepartment(String department);
    List<Member> findByDepartmentContaining(String department);
}
