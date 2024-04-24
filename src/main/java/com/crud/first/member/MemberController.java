package com.crud.first.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MemberController {
  private final MemberRepository memberRepository;
  private final MemberService memberService;

  @GetMapping("/register")
  public String register() {
    return "member/register.html";
  }

  @PostMapping("/member")
  public String addMember(MemberDto memberDto) {
    memberService.saveMember(memberDto);
    return "redirect:/";
  }
}
