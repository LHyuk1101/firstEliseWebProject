package com.crud.first.member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
  boolean existsByUserId(String userId);
}
