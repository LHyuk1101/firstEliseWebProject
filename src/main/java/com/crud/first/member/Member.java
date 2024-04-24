package com.crud.first.member;

import jakarta.persistence.*;

@Entity
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String displayName; //유저 이름

  @Column(nullable = false, unique = true)
  private String userId; // 유저 아이디

  @Column(nullable = false)
  private String password; // 비밀번호

  @Column(nullable = false)
  private String petName; // 동물이름

  public Member(String displayName, String userId, String password, String petName) {
    this.displayName = displayName;
    this.userId = userId;
    this.password = password;
    this.petName = petName;
  }
}
