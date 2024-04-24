package com.crud.first.home;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@PreAuthorize("isAnonymous()")
@Controller
public class HomeController {

  @GetMapping("/")
  public String home() {
    return "home.html";
  }



}
