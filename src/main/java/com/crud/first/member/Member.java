package com.crud.first.member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String displayName; //유저 이름

  @Column(nullable = false, unique = true)
  private String username; // 유저 아이디

  @Column(nullable = false)
  private String password; // 비밀번호

  @Column(nullable = false)
  private String petName; // 동물이름

  public Member(String displayName, String username, String password, String petName) {
    this.displayName = displayName;
    this.username = username;
    this.password = password;
    this.petName = petName;
  }
}
