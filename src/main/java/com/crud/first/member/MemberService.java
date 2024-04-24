package com.crud.first.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;

  public void saveMember(MemberDto memberDto){
    memberDto.setPassword(new BCryptPasswordEncoder().encode(memberDto.getPassword()));
    memberRepository.save(memberDto.toEntity());
  }
}
