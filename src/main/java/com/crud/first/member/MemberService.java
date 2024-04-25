package com.crud.first.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;

  public void saveMember(MemberDto memberDto){
    memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
    memberRepository.save(memberDto.toEntity());
  }

  public String getUserName(Authentication auth){
    CustomUser customUser = (CustomUser) auth.getPrincipal();
    return customUser.getUsername();
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Optional<Member> result = memberRepository.findByUsername(username);

    if (result.isEmpty()){
      throw new UsernameNotFoundException("그런 아이디 없는데요?");
    }

    Member member = result.get();

    List<GrantedAuthority> authorities = new ArrayList<>();

    if (member.getUsername().equals("hyuk3852")){
      authorities.add(new SimpleGrantedAuthority("관리자"));
    }else {
      authorities.add(new SimpleGrantedAuthority("일반유저"));
    }

    CustomUser customUser = new CustomUser(member.getUsername(), member.getPassword(), authorities);
    customUser.setDisplayName(member.getDisplayName());
    return customUser;
  }
}
