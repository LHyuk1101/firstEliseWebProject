package com.crud.first.member;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
public class MemberDto {

  private Long id;
  private String displayName; //유저 이름
  private String userId; // 유저 아이디
  private String password; // 비밀번호
  private String petName; // 동물이름

  public Member toEntity() {
    return new Member(displayName, userId, password, petName);
  }
}
