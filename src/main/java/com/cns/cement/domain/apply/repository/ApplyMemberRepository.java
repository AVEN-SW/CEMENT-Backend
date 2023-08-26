package com.cns.cement.domain.apply.repository;

import com.cns.cement.domain.apply.entity.ApplyMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplyMemberRepository extends JpaRepository<ApplyMember, Long> {
}
