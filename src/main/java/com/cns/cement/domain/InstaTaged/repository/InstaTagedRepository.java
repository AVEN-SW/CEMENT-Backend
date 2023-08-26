package com.cns.cement.domain.InstaTaged.repository;

import com.cns.cement.domain.InstaTaged.entity.InstaTaged;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstaTagedRepository extends JpaRepository<InstaTaged, Long> {

}
