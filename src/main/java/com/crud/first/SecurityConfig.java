package com.crud.first;

import com.crud.first.board.Board;
import com.crud.first.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final BoardRepository boardRepository;

  public Board getFirstBoardNum() {
    return boardRepository.findAll().getFirst();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf((csrf) -> csrf.disable());

    http.authorizeHttpRequests((authorize) -> authorize.requestMatchers("/**").permitAll());

    http.formLogin((formLogin) -> formLogin.loginPage("/login").defaultSuccessUrl("/board/"+getFirstBoardNum().getBoardId()));

    http.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/"));

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
