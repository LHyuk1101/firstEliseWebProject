package com.crud.first.member;


import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
public class MemberDto {

  private Long id;
  private String displayName; //유저 이름

  @NotEmpty(message = "이름을 입력해주세요")
  private String username; // 유저 아이디

  @NotEmpty(message = "비밀번호를 입력해주세요")
  private String password; // 비밀번호

  @NotEmpty(message = "반려동물의 이름을 입력해주세요")
  private String petName; // 동물이름

  public Member toEntity() {
    return new Member(displayName, username, password, petName);
  }
}
